package brutuceForce.nayoung;

import java.util.Scanner;

/**
 * @author hny48
 * boj 4963 섬의 개수를 구하는 문제 <pre>
 * 조건: 상, 하, 좌, 우, 대각선으로 땅이 있다면 이동할 수 있다. -> 이동할 수 있으면 같은 섬.
 * 입력: 1이면 땅, 0이면 바다
 */
public class IslandCount {
	
	// 대각선 방향 4개, 상, 하, 좌, 우
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

			// 방문했으면 1, 아니면 0
			visited = new int[h][w];
			for (int i = 0; i < visited.length; i++) {
				for (int j = 0; j < visited[0].length; j++) {
					if (map[i][j] == 1) {
						if (visited[i][j] == 0) {
							// 땅인데 방문한 적이 없으면
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
	 * @param beforeX 현재 x좌표
	 * @param beforeY 현재 y좌표
	 * @param afterX 이동할 x좌표
	 * @param afterY 이동할 y좌표
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
	 * @param map 땅과 바다의 위치를 담은 배열
	 * @param beforeX 현재 x좌표
	 * @param beforeY 현재 Y좌표
	 * @param h x의 최대 값
	 * @param w y의 최대 값
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

			// afterX, afterX가 beforeX, afterY의 
			// 상, 하, 좌, 우, 대각선에 해당하는 곳이면
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
