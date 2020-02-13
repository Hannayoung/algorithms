package brutuceForce.nayoung;

import java.util.Scanner;

/**
 * @author hny48 17779 게리맨더링2 인구가 가장 많은 선거구와 가장 적은 선거구의 인구 차이의 최소 값 구하기
 *
 * 조건
 * d1, d2 ≥ 1, 1 ≤ x < x+d1+d2 ≤ N, 1 ≤ y-d1 < y < y+d2 ≤ N
 * 
 * 1번 선거구: 1 ≤ r < x+d1, 1 ≤ c ≤ y
 * 2번 선거구: 1 ≤ r ≤ x+d2, y < c ≤ N
 * 3번 선거구: x+d1 ≤ r ≤ N, 1 ≤ c < y-d1+d2
 * 4번 선거구: x+d2 < r ≤ N, y-d1+d2 ≤ c ≤ N
 * 
 * 풀이과정
 * 처음에는 bfs도 생각하고 dfs도 생각하고 되게 어렵게 생각했던 듯..
 * 그래도 안풀려서 그림그렸고 출발 점 x,y를 기준으로
 * 왼쪽 대각선이 d1, 오른쪽 대각선이 d2 였다가 d2, d1으로 바뀌니까
 * 위
 * 왼쪽: d1의 길이만큼 x++, y-- 오른 쪽: d2의 길이만큼 x++, y++
 * 아래
 * 왼쪽: d2의 길이만큼  x++, y++ 오른 쪽: d1의 길이만큼 x++, y--
 * 로 구현하면 되고 위에 조건 그대로 따라가면 된다.
 */
public class GaryMenderling {
	public static int N = 0;
	public static int[][] map;
	public static int[][] visited;
	public static int[] electDistrict;

	public static int difMin = Integer.MAX_VALUE;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		for (int x = 1; x <= N; ++x) {
			for (int y = 1; y <= N; ++y) {
				for (int d1 = 1; d1 <= N; ++d1) {
					for (int d2 = 1; d2 <= N; ++d2) {
						if ((x + d1 + d2) <= N && (y - d1) >= 1 && (y - d1) < y && (y + d2) > y && (y + d2) <= N) {
							visited = new int[N + 1][N + 1];
							electDistrict = new int[6];

							setBoundary(x, y, d1, d2);
							setArea(x, y, d1, d2);
							calPopulation();
						}
					}
				}
			}
		}
		System.out.println(difMin);
	}

	public static void setBoundary(int x, int y, int d1, int d2) {
		visited[x][y] = 5;
		int boundary1 = 0;
		int boundary2 = 0;

		while (++boundary1 <= d1)
			visited[x + boundary1][y - boundary1] = 5;
		while (++boundary2 <= d2)
			visited[x + boundary2][y + boundary2] = 5;

		boundary1 = 0;
		boundary2 = 0;
		while (++boundary2 <= d2)
			visited[x + d1 + boundary2][y - d1 + boundary2] = 5;
		while (++boundary1 <= d1)
			visited[x + d2 + boundary1][y + d2 - boundary1] = 5;

		for (int i = 1; i <= N; i++) {
			int left = -1;
			int right = -1;

			int index = 1;
			while (index <= N) {
				if (visited[i][index] == 5) {
					left = index;
					break;
				}
				index++;
			}

			index = N;
			while (index >= 0) {
				if (visited[i][index] == 5) {
					right = index;
					break;
				}
				index--;
			}

			if (left != right) {
				for (int j = left; j < right; j++) {
					visited[i][j] = 5;
				}
			}
		}
	}

	public static void setArea(int x, int y, int d1, int d2) {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (visited[i][j] != 0)
					continue;

				if (i < (x + d1) && j <= y)
					visited[i][j] = 1;
				else if (i <= (x + d2) && y < j)
					visited[i][j] = 2;
				else if ((x + d1) <= i && j < (y - d1 + d2))
					visited[i][j] = 3;
				else if ((x + d2) < i && (y - d1 + d2) <= j)
					visited[i][j] = 4;
				else
					visited[i][j] = 5;
			}
		}
	}

	public static void calPopulation() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				electDistrict[visited[i][j]] += map[i][j];
			}
		}

		int min = Integer.MAX_VALUE;
		int max = -1;

		for (int i = 1; i < 6; i++) {
			max = electDistrict[i] > max ? electDistrict[i] : max;
			min = electDistrict[i] < min ? electDistrict[i] : min;
		}

		difMin = difMin > (max - min) ? (max - min) : difMin;
	}
}
