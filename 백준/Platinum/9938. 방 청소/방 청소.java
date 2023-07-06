import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, L;
	static int[] parent, size;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		parent = new int[L + 1];
		size = new int[L + 1];
		Arrays.fill(size, 1);
		for (int i = 1; i <= L; i++)
			parent[i] = i;

		StringBuilder answer = new StringBuilder();
		for (int i = 1, a, b; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());

			if (size[find(a)] > 0 || size[find(b)] > 0) {
				size[find(a)]--;
				answer.append("LADICA");
				union(a, b);
			} else
				answer.append("SMECE");
			answer.append("\n");
		}
		System.out.println(answer);
	}

	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a == b)
			return;

		if (a < b) {
			int tmp = a;
			a = b;
			b = tmp;
		}
		parent[a] = b;
		size[b] += size[a];
		size[a] = 0;
		return;
	}

	static int find(int x) {
		if (x == parent[x])
			return x;
		return parent[x] = find(parent[x]);
	}
}