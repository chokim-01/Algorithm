import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken()) + 1;

		int[][] link = new int[M][2];

		while (M-- > 0)
			link[M] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		Arrays.sort(link, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return o1[2] - o2[2];
			}
		});
		System.out.println(getAns(link));
	}

	static long getAns(int[][] link) {
		int[][] parent = new int[2][N + 1];

		for (int i = 1; i <= N; i++) {
			parent[0][i] = i;
			parent[1][i] = i;
		}

		long a = 0;
		long b = 0;
		for (int i = 0, j; i < link.length; i++) {
			j = link.length - 1 - i;

			if (find(link[i][0], parent[0]) != find(link[i][1], parent[0]))
				a += link[i][2] ^ 1;

			if (find(link[j][0], parent[1]) != find(link[j][1], parent[1]))
				b += link[j][2] ^ 1;

			union(link[i][0], link[i][1], parent[0]);
			union(link[j][0], link[j][1], parent[1]);
		}
		return a * a - b * b;
	}

	static void union(int a, int b, int[] parent) {
		int fa = find(a, parent);
		int fb = find(b, parent);

		if (fa == fb)
			return;
		parent[fb] = fa;
	}

	static int find(int a, int[] parent) {
		if (a == parent[a])
			return a;

		return parent[a] = find(parent[a], parent);
	}
}
