package brutuceForce.nayoung;

import java.util.LinkedList;
import java.util.Scanner;


/**
 * @author hny48 boj 2178 1,1���� ����Ͽ� �־��� N,M���� �̵��� �� �ּ� �Ÿ� ���ϱ�
 * ����
 * 1) 1�� �̵��� �� �ְ� 0�� �̵��� �� ����.
 * 2) �Ÿ��� �� �� ���� ��ġ�� ���� ��ġ�� �����Ѵ�.
 * 
 * Ǯ�̰���
 * 1) 1,1 ���� �����ϱ� ���� ť�� x, y distance =1�� �߰�
 * 2) visited[0][0] = 1; (0, 0�� 1,1�̹Ƿ�)
 * 3) ���� ��ġ�� N-1, M-1�̸� ť�� ���� ���� �ʰ� �ּҰŸ����� ��. (ť�� ������ ��� üũ�ϹǷ�)
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
	 * @param next x, y, distance�� ���� ť
	 * @param visited ���� ��ġ�� �湮�ߴ� ���� ��Ÿ���� ������ �迭
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
