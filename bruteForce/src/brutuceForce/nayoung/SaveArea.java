package brutuceForce.nayoung;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;


/**
 * @author hny48 boj 2583 직사각형의 넓이를 제외한 부분의 넓이와 개수 구하기
 * 조건
 * 1) 처음에 주어지는 좌표는 왼쪽 아래 꼭지점, 두번 쨰 주어지는 좌표는 오른 쪽 위 꼭지점
 * 2) 영역의 넓이는 오름차순으로 출력
 * 
 * 풀이과정
 * 1) 입력으로 주어지는 값은 좌표이고 활용할 것은 이차원 배열, 하지만 0,0과 M,N의 위치만 다르고 영역의 넓이는 똑같음.
 * -> 실제로 그려보면 상하 반전
 * 2) 처음에 주어지는 좌표는 왼쪽 아래 꼭지점이므로 X좌표와 Y좌표만 교환, 두 번째는 오른 쪽 위 꼭지점이므로 X좌표 Y좌표 교환 및 -1씩
 * 3) 중간에 직사각형이 있으면 DFS로 탐색이 안될 수도 있을것같아서 BFS만 사용.
 * 4) boj 4963 섬의 개수 구하기처럼 관련된 것들 끼리 count
 * ex) 1111, 2222, 3333  
 */
public class SaveArea {
	public static int[][] map;
	// 처음에 111, 222, 3333 으로 영역을 구분하기 위한 방문 체크 여부 배열	
	public static int[][] visited;
	// 각 영역의 넓이를 구하기 위한 방문 체크 여부 배열
	public static int[][] countVisited;
	public static int[] dx = { -1, 1, 0, 0 };
	public static int[] dy = { 0, 0, -1, 1 };
	public static int N = 0;
	public static int M = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		M = sc.nextInt();
		N = sc.nextInt();
		int K = sc.nextInt();
		map = new int[M][N];
		visited = new int[M][N];

		// 첫 번째로 받은 좌표는 X Y 교환
		// 두 번째로 받은 좌표는 X Y 교환 및 -1 씩
		for (int k = 0; k < K; k++) {
			int y1 = sc.nextInt();
			int x1 = sc.nextInt();
			int y2 = sc.nextInt() - 1;
			int x2 = sc.nextInt() - 1;
			makeRectangular(x1, y1, x2, y2);
		}

		int count = 0;
		// 영역의 넓이를 담을 리스트		
		ArrayList<Integer> area = new ArrayList<Integer>();
		LinkedList<String> list = new LinkedList<String>();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] == 1) {
					continue;
				}

				if (visited[i][j] == 0) {
					visited[i][j] = ++count;
					list.add(i + "," + j);
					bfs(list, i, j);
				}
			}
		}

		LinkedList<String> countList = new LinkedList<String>();
		countVisited = new int[M][N];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] == 1 || countVisited[i][j] == 1) {
					continue;
				}
				countVisited[i][j] = 1;
				countList.add(i + "," + j);
				area.add(countBfs(countList));
			}
		}
		
		Collections.sort(area);
		System.out.println(area.size());
		for (int nextArea : area) {
			System.out.print(nextArea + " ");
		}
	}

	/**
	 * 
	 * @param list 영역을 구분 짓기 위해 x,y좌표를 담은 리스트
	 * @param startX main의 for문에서 처음 좌표 값으로 칠할 때 사용하기 위한 x좌표
	 * @param startY main의 for문에서 처음 좌표 값으로 칠할 때 사용하기 위한 y좌표
	 * ex) visited 배열
	 * 1 1 1 1 0 0 2
	 * 1 0 1 1 0 0 2
	 * 0 0 0 0 2 2 2
	 * 0 0 0 0 2 2 2
	 * 3 0 2 2 2 2 2
	 */
	public static void bfs(LinkedList<String> list, int startX, int startY) {
		while (!list.isEmpty()) {
			String[] temp = list.poll().split(",");
			int x = Integer.parseInt(temp[0]);
			int y = Integer.parseInt(temp[1]);

			for (int i = 0; i < 4; i++) {
				int tempX = dx[i] + x;
				int tempY = dy[i] + y;

				if (tempX < 0 || tempY < 0 || tempX > M - 1 || tempY > N - 1) {
					continue;
				}

				if (map[tempX][tempY] == 1 || visited[tempX][tempY] != 0) {
					continue;
				}

				visited[tempX][tempY] = visited[startX][startY];
				list.add(tempX + "," + tempY);
			}
		}
	}

	/**
	 * 
	 * @param countList 영역이 넓이를 구하기 위한 x, y좌표가 담겨있는 리스트
	 * @return 영역의 넓이
	 * ex) countVisited 배열
	 * 1 1 1 1 0 0 1
	 * 1 0 1 1 0 0 1
	 * 0 0 0 0 1 1 1
	 * 0 0 0 0 1 1 1
	 * 1 0 1 1 1 1 1
	 */
	public static int countBfs(LinkedList<String> countList) {
		// countList에 처음으로 담겨 있는 i,j 좌표를 방문한 상태로 시작하니까
		int count = 1;
		while (!countList.isEmpty()) {
			String[] temp = countList.poll().split(",");
			int x = Integer.parseInt(temp[0]);
			int y = Integer.parseInt(temp[1]);

			for (int i = 0; i < 4; i++) {
				int tempX = dx[i] + x;
				int tempY = dy[i] + y;

				if (tempX < 0 || tempY < 0 || tempX > M - 1 || tempY > N - 1) {
					continue;
				}

				if (map[tempX][tempY] == 1 || countVisited[tempX][tempY] == 1) {
					continue;
				}

				count++;
				countVisited[tempX][tempY] = 1;
				countList.add(tempX + "," + tempY);
			}
		}
		return count;
	}

	public static void makeRectangular(int x1, int y1, int x2, int y2) {
		for (int tempX = x1; tempX <= x2; tempX++) {
			for (int tempY = y1; tempY <= y2; tempY++) {
				map[tempX][tempY] = 1;
			}
		}
	}
}
