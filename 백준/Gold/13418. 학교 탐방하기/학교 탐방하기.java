import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static int N, M;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken()) + 1;

		parent = new int[N + 1];
		for (int i = 1; i < parent.length; i++)
			parent[i] = i;

		int[][] link = new int[M][2];

		while (M-- > 0)
			link[M] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		System.out.println(getAns(Stream.of(link).sorted(Comparator.comparingInt(l -> l[2])).toArray(int[][]::new))
				- getAns(Stream.of(link).sorted(Comparator.comparingInt(l -> -l[2])).toArray(int[][]::new)));
	}

	static long getAns(int[][] link) {
		for (int i = 0; i < parent.length; i++)
			parent[i] = i;
		
		long ans = 0;
		for (int[] l : link) {
			if (find(l[0]) != find(l[1]))
				ans += l[2] ^ 1;

			union(l[0], l[1]);
		}
		return ans * ans;
	}

	static void union(int a, int b) {
		int fa = find(a);
		int fb = find(b);

		if (fa == fb)
			return;
		parent[fb] = fa;
	}

	static int find(int a) {
		if (a == parent[a])
			return a;
		int fa = find(parent[a]);

		return parent[a] = fa;
	}
}
