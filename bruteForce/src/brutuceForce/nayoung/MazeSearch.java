package brutuceForce.nayoung;

import java.util.LinkedList;
import java.util.Scanner;


/**
 * @author hny48 boj 2178 1,1에서 출발하여 주어진 N,M까지 이동할 때 최소 거리 구하기
 * 조건
 * 1) 1은 이동할 수 있고 0은 이동할 수 없다.
 * 2) 거리를 셀 때 시작 위치와 도착 위치도 포함한다.
 * 
 * 풀이과정
 * 1) 1,1 부터 시작하기 위해 큐에 x, y distance =1를 추가
 * 2) visited[0][0] = 1; (0, 0이 1,1이므로)
 * 3) 현재 위치가 N-1, M-1이면 큐에 값을 넣지 않고 최소거리인지 비교. (큐에 넣으면 계속 체크하므로)
 */
public class MazeSearch {

	public static int[] dx = { -1, 1, 0, 0 };
	public static int[] dy = { 0, 0, -1, 1 };
	public static int N = 0;
	public static int M = 0;
	public static int[][] map;
	public static int resultDistance = Integer.MAX_VALUE;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		int[][] visited = new int[N][M];
		
		for (int i = 0; i < map.length; i++) {
			String tempNext = sc.next();
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = tempNext.charAt(j) - '0';
			}
		}
		
		LinkedList<String> next = new LinkedList<String>();
		next.add(0 + "," + 0 + "," + 1);
		visited[0][0] = 1;
		bfs(next, visited);
		System.out.println(resultDistance);
	}

	/**
	 * 
	 * @param next x, y, distance를 담을 큐
	 * @param visited 현재 위치를 방문했는 지를 나타내는 이차원 배열
	 */
	public static void bfs(LinkedList<String> next, int[][] visited) {
		while (!next.isEmpty()) {
			String[] temp = next.poll().split(",");
			int x = Integer.parseInt(temp[0]);
			int y = Integer.parseInt(temp[1]);
			int distance = Integer.parseInt(temp[2]);

			for (int i = 0; i < 4; i++) {
				int tempX = dx[i] + x;
				int tempY = dy[i] + y;

				if (tempX < 0 || tempY < 0 || tempX > N - 1 || tempY > M - 1) {
					continue;
				}

				if (visited[tempX][tempY] == 1 || map[tempX][tempY] == 0) {
					continue;
				}

				if (tempX == N - 1 && tempY == M - 1) {
					distance += 1;
					if (resultDistance > distance) {
						resultDistance = distance;
					}
					break;
				}
				visited[tempX][tempY] = 1;
				next.add(tempX + "," + tempY + "," + (distance + 1));
			}
		}
	}
}
