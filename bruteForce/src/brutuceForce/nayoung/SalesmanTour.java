package brutuceForce.nayoung;

import java.util.Scanner;


/**
 * @author hny48 boj 10971 외판원이 n개의 도시를 방문할 때 드는 최소 비용 구하는 문제
 * <pre>
 * 조건
 * 1) n개의 도시를 모두 거쳐야 한다. 
 * 2) 한 번 갔던 도시는 다시 가지 못한다.(출발했던 도시로 돌아오는 것은 예외)
 * 3) 비용이 0인 경우에는 이동하지 못하는 경우이므로 제외
 * 입력: 도시의 개수, 각 도시로 이동하는데 들어가는 비용
 * 
 * 풀이 과정
 * 1) 0->1->2->3, 0->1->3->2 이런식으로 반복되므로 순열이랑 비슷하다고 생각 & dfs
 * 2) 경우의 수가 너무 많아지므로 방문한 곳을 다시 방문하지 않게 순열처럼 visited[] 배열 사용.
 * 
 * 시간이 오래걸렸던 부분: 비용이 0인 경우에 sum에 합하지 못하게 했으나, 출발했던 도시로 가는 길이 없을 경우를 제외하지 않았음.
 * 예) 0->1로 가는 비용이 0일 때, 1->3->2->0의 경우도 제외해야 함.
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
	 * @param start 처음에 출발했던 위치
	 * @param y 지금 출발할 위치
	 * @param visited 방문 여부
	 * @param sum 0->1->2->3으로 방문했을 때 들어간 비용
	 */
	public static void dfs(int start, int y, int[] visited, int sum) {
		// 출발점을 제외한 곳을 다 방문했으면
		if (check(visited)) {
			// 현재 y에서 원점으로 가는 길이 없을 경우
			if (cost[y][start] == 0) {
				return;
			}

			sum += cost[y][start];
			if (sum < minCost) {
				minCost = sum; // 현제 y에서 원점으로 가는 값을 minCost에 변경
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