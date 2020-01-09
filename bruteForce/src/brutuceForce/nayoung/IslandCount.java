package brutuceForce.nayoung;

import java.util.Scanner;

/**
 * @author hny48
 * boj 4963 ���� ������ ���ϴ� ���� <pre>
 * ����: ��, ��, ��, ��, �밢������ ���� �ִٸ� �̵��� �� �ִ�. -> �̵��� �� ������ ���� ��.
 * �Է�: 1�̸� ��, 0�̸� �ٴ�
 */
public class IslandCount {
	
	// �밢�� ���� 4��, ��, ��, ��, ��
	public static int[] dx = { -1, -1, 1, 1, -1, 1, 0, 0 };
	public static int[] dy = { -1, 1, -1, 1, 0, 0, -1, 1 };
	public static int[][] visited;
	public static int islandCount = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		boolean flag = true;
		while (flag) {
			int w = sc.nextInt();
			int h = sc.nextInt();

			if (w == 0) {
				flag = false;
				continue;
			}

			int[][] map = new int[h][w];

			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[0].length; j++) {
					map[i][j] = sc.nextInt();
				}
			}

			// �湮������ 1, �ƴϸ� 0
			visited = new int[h][w];
			for (int i = 0; i < visited.length; i++) {
				for (int j = 0; j < visited[0].length; j++) {
					if (map[i][j] == 1) {
						if (visited[i][j] == 0) {
							// ���ε� �湮�� ���� ������
							visited[i][j] = ++islandCount;
							dfs(map, i, j, h, w);
						}
					}
				}
			}
			System.out.println(islandCount);
			islandCount = 0;
		}
	}

	/**
	 * 
	 * @param beforeX ���� x��ǥ
	 * @param beforeY ���� y��ǥ
	 * @param afterX �̵��� x��ǥ
	 * @param afterY �̵��� y��ǥ
	 * @return
	 */
	public static boolean isNearBy(int beforeX, int beforeY, int afterX, int afterY) {
		int tempX = 0;
		int tempY = 0;

		for (int i = 0; i < 8; i++) {
			tempX = dx[i] + afterX;
			tempY = dy[i] + afterY;
			if (tempX != beforeX || tempY != beforeY) {
				continue;
			}
			return true;
		}

		return false;
	}

	/**
	 * 
	 * @param map ���� �ٴ��� ��ġ�� ���� �迭
	 * @param beforeX ���� x��ǥ
	 * @param beforeY ���� Y��ǥ
	 * @param h x�� �ִ� ��
	 * @param w y�� �ִ� ��
	 */
	public static void dfs(int[][] map, int beforeX, int beforeY, int h, int w) {
		int afterX = -1;
		int afterY = -1;
		for (int i = 0; i < 8; i++) {
			afterX = dx[i] + beforeX;
			afterY = dy[i] + beforeY;
			if (afterX > h - 1 || afterY > w - 1 || afterX < 0 || afterY < 0) {
				continue;
			}
			if (map[afterX][afterY] == 0 || visited[afterX][afterY] != 0) {
				continue;
			}

			// afterX, afterX�� beforeX, afterY�� 
			// ��, ��, ��, ��, �밢���� �ش��ϴ� ���̸�
			if (isNearBy(beforeX, beforeY, afterX, afterX)) {
				visited[afterX][afterY] = visited[beforeX][beforeY];
			} else {
				visited[afterX][afterY] = ++visited[beforeX][beforeY];
				islandCount = visited[beforeX][beforeY];
			}

			dfs(map, afterX, afterY, h, w);
		}
	}

}
