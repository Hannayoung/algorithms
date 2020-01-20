package brutuceForce.nayoung;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;


/**
 * @author hny48 boj 2583 ���簢���� ���̸� ������ �κ��� ���̿� ���� ���ϱ�
 * ����
 * 1) ó���� �־����� ��ǥ�� ���� �Ʒ� ������, �ι� �� �־����� ��ǥ�� ���� �� �� ������
 * 2) ������ ���̴� ������������ ���
 * 
 * Ǯ�̰���
 * 1) �Է����� �־����� ���� ��ǥ�̰� Ȱ���� ���� ������ �迭, ������ 0,0�� M,N�� ��ġ�� �ٸ��� ������ ���̴� �Ȱ���.
 * -> ������ �׷����� ���� ����
 * 2) ó���� �־����� ��ǥ�� ���� �Ʒ� �������̹Ƿ� X��ǥ�� Y��ǥ�� ��ȯ, �� ��°�� ���� �� �� �������̹Ƿ� X��ǥ Y��ǥ ��ȯ �� -1��
 * 3) �߰��� ���簢���� ������ DFS�� Ž���� �ȵ� ���� �����Ͱ��Ƽ� BFS�� ���.
 * 4) boj 4963 ���� ���� ���ϱ�ó�� ���õ� �͵� ���� count
 * ex) 1111, 2222, 3333  
 */
public class SaveArea {
	public static int[][] map;
	// ó���� 111, 222, 3333 ���� ������ �����ϱ� ���� �湮 üũ ���� �迭	
	public static int[][] visited;
	// �� ������ ���̸� ���ϱ� ���� �湮 üũ ���� �迭
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

		// ù ��°�� ���� ��ǥ�� X Y ��ȯ
		// �� ��°�� ���� ��ǥ�� X Y ��ȯ �� -1 ��
		for (int k = 0; k < K; k++) {
			int y1 = sc.nextInt();
			int x1 = sc.nextInt();
			int y2 = sc.nextInt() - 1;
			int x2 = sc.nextInt() - 1;
			makeRectangular(x1, y1, x2, y2);
		}

		int count = 0;
		// ������ ���̸� ���� ����Ʈ		
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
	 * @param list ������ ���� ���� ���� x,y��ǥ�� ���� ����Ʈ
	 * @param startX main�� for������ ó�� ��ǥ ������ ĥ�� �� ����ϱ� ���� x��ǥ
	 * @param startY main�� for������ ó�� ��ǥ ������ ĥ�� �� ����ϱ� ���� y��ǥ
	 * ex) visited �迭
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
	 * @param countList ������ ���̸� ���ϱ� ���� x, y��ǥ�� ����ִ� ����Ʈ
	 * @return ������ ����
	 * ex) countVisited �迭
	 * 1 1 1 1 0 0 1
	 * 1 0 1 1 0 0 1
	 * 0 0 0 0 1 1 1
	 * 0 0 0 0 1 1 1
	 * 1 0 1 1 1 1 1
	 */
	public static int countBfs(LinkedList<String> countList) {
		// countList�� ó������ ��� �ִ� i,j ��ǥ�� �湮�� ���·� �����ϴϱ�
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
