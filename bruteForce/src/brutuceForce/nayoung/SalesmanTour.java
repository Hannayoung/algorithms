package brutuceForce.nayoung;

import java.util.Scanner;


/**
 * @author hny48 boj 10971 ���ǿ��� n���� ���ø� �湮�� �� ��� �ּ� ��� ���ϴ� ����
 * <pre>
 * ����
 * 1) n���� ���ø� ��� ���ľ� �Ѵ�. 
 * 2) �� �� ���� ���ô� �ٽ� ���� ���Ѵ�.(����ߴ� ���÷� ���ƿ��� ���� ����)
 * 3) ����� 0�� ��쿡�� �̵����� ���ϴ� ����̹Ƿ� ����
 * �Է�: ������ ����, �� ���÷� �̵��ϴµ� ���� ���
 * 
 * Ǯ�� ����
 * 1) 0->1->2->3, 0->1->3->2 �̷������� �ݺ��ǹǷ� �����̶� ����ϴٰ� ���� & dfs
 * 2) ����� ���� �ʹ� �������Ƿ� �湮�� ���� �ٽ� �湮���� �ʰ� ����ó�� visited[] �迭 ���.
 * 
 * �ð��� �����ɷȴ� �κ�: ����� 0�� ��쿡 sum�� ������ ���ϰ� ������, ����ߴ� ���÷� ���� ���� ���� ��츦 �������� �ʾ���.
 * ��) 0->1�� ���� ����� 0�� ��, 1->3->2->0�� ��쵵 �����ؾ� ��.
 */
public class SalesmanTour {

	private static int minCost = Integer.MAX_VALUE;
	private static int[][] cost;
	private static int cityCount = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		cityCount = sc.nextInt();
		cost = new int[cityCount][cityCount];

		for (int i = 0; i < cost.length; i++) {
			for (int j = 0; j < cost[0].length; j++) {
				cost[i][j] = sc.nextInt();
			}
		}

		for (int i = 0; i < cost.length; i++) {
			for (int j = 0; j < cost[0].length; j++) {
				if (i == j) {
					continue;
				}
				if (cost[i][j] == 0) {
					continue;
				}

				int[] visited = new int[cityCount];
				visited[i] = 1;
				visited[j] = 1;
				dfs(i, j, visited, cost[i][j]);
			}
		}

		System.out.println(minCost);
	}

	public static boolean check(int[] visited) {
		for (int i = 0; i < visited.length; i++) {
			if (visited[i] == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @param start ó���� ����ߴ� ��ġ
	 * @param y ���� ����� ��ġ
	 * @param visited �湮 ����
	 * @param sum 0->1->2->3���� �湮���� �� �� ���
	 */
	public static void dfs(int start, int y, int[] visited, int sum) {
		// ������� ������ ���� �� �湮������
		if (check(visited)) {
			// ���� y���� �������� ���� ���� ���� ���
			if (cost[y][start] == 0) {
				return;
			}

			sum += cost[y][start];
			if (sum < minCost) {
				minCost = sum; // ���� y���� �������� ���� ���� minCost�� ����
			}

			return;
		}

		for (int i = 0; i < cityCount; i++) {
			if (visited[i] == 1 || cost[y][i] == 0) {
				continue;
			}

			sum += cost[y][i];
			visited[i] = 1;
			dfs(start, i, visited, sum);
			visited[i] = 0;
			sum -= cost[y][i];
		}
	}
}