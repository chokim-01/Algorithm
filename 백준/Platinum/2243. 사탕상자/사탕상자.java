import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		N = 1048576;
		M = Integer.parseInt(br.readLine());

		tree = new int[N << 1];

		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (a == 1) { // 꺼내주기
				int index = choose(b);
				sb.append(index - N).append("\n");
				update(index, -1);
			} else { // 넣고 빼기
				int c = Integer.parseInt(st.nextToken());
				update(b + N, c);
			}
		}
		System.out.println(sb);
	}

	static int choose(int cnt) {
		int node = 1;
		while (node < N) {
			if (tree[node << 1] >= cnt) {
				// 왼쪽
				node = node << 1;
			} else {
				// 오른쪽
				cnt -= tree[node << 1];
				node = node << 1 | 1;
			}
		}

		return node;

	}

	static long get(int left, int right) {
		long ans = 0;
		while (left < right) {
			if ((left & 1) == 1) {
				ans += tree[left++];
			}
			if ((right & 1) == 1) {
				right--;
				ans += tree[--right];
			}
			left /= 2;
			right /= 2;
		}
		return ans;
	}

	static void update(int index, int val) {
		do
			tree[index] += val;
		while ((index = index / 2) != 0);
	}
}