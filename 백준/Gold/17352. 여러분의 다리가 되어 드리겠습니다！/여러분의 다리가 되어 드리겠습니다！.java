import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		parent = new int[N + 1];
		for (int i = 1; i <= N; i++)
			parent[i] = i;

		for (int t = 0; t < N - 2; t++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			union(a, b);
		}
		int x1 = find(1);
		int x2 = -1;
		for (int i = 1; i <= N; i++) {
			int f = find(i);
			if(f != x1) {
				x2 = f;
				break;
			}
		}
		System.out.println(x1 + " " + x2);
	}

	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if (x == y)
			return;
		if (x < y)
			parent[y] = x;
		else
			parent[x] = y;
	}

	static int find(int x) {
		if (x == parent[x])
			return x;

		return parent[x] = find(parent[x]);
	}
}