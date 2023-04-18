import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int color, size;

		public Node(int color, int size) {
			// TODO Auto-generated constructor stub
			this.color = color;
			this.size = size;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[][] arr = new int[N][3];
		for (int i = 0, c, s; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			c = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			arr[i][0] = c;
			arr[i][1] = s;
			arr[i][2] = i;
		}
		Arrays.sort(arr, (o1, o2) -> o1[1] == o2[1] ? o1[0] - o2[0] : o1[1] - o2[1]);

		int[] color = new int[N + 1];
		int[] ans = new int[N];

		int sum = 0;
		int index = 0;
		// 0 : c , 1 : s , 2 : i
		for (int i = 0; i < N; i++) {
			while (arr[index][1] < arr[i][1]) {
				sum += arr[index][1];
				color[arr[index][0]] += arr[index++][1];
			}
			ans[arr[i][2]] = sum - color[arr[i][0]];
		}
		StringBuilder sb = new StringBuilder();
		for (int a : ans)
			sb.append(a).append("\n");
		System.out.println(sb);

	}
}