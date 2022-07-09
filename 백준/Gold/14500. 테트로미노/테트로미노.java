import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int[][] arr;
	static int N, M;
	static int countOfArea;
	static int ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		countOfArea = 4;

		ans = 0;

		arr = new int[N][M + 1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N; i++)
			for (int j = 1; j <= M; j++)
				dfs(i, j, j, countOfArea, 0);

		System.out.println(ans);
	}

	static void dfs(int x, int beforeMinY, int beforeMaxY, int count, int sum) {
		if (count == 0) {
			ans = ans < sum ? sum : ans;
			return;
		}
		if (x >= N)
			return;

		// 선택하면 count가 줄어듦.

		// 이번 x에서 c개를 선택
		for (int c = 1; c <= count; c++) {
			// beforeX보다 작지 않으면서 beforeY보다 크지 않게.
			// 현재를 선택하고. beforeMinY, beforeMaxY를 세팅 후 전달
			int now = beforeMinY - (c - 1) < 1 ? 1 : beforeMinY - (c - 1); // 스타트지점 부터 c개를 선택

			for (; now <= beforeMaxY; now++) {

				if (now + (c - 1) > M) // 오른쪽 초과
					break;

				int nowSum = 0;

				for (int i = now; i <= now + c - 1; i++)
					nowSum += arr[x][i];

				dfs(x + 1, now, now + c - 1, count - c, sum + nowSum);
			}
		}
	}
}
