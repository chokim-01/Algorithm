import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.stream.Stream;

class Main {
	static int T, N, K;
	static int[] city, parent;
	static long[] population;
	static ArrayList<Integer>[] link;
	static long count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			input(br);
			long l = Arrays.stream(city).max().getAsInt();
			long r = Arrays.stream(city).mapToLong(v -> (long) v).sum();
			long mid = 0;
			while (l < r) {
				count = 0;
				mid = (l + r) >> 1;
				dfs(1, new boolean[N + 1], mid);
//				System.out.println(mid + " " + count);
				if (K < count)
					l = mid + 1;
				else
					r = mid;
			}
			sb.append(r).append("\n");
		}
		System.out.println(sb);
	}

	static long dfs(int now, boolean[] v, long c) {
		v[now] = true;
		population[now] = city[now];
		ArrayList<Long> list = new ArrayList<>();
		for (int next : link[now]) {
			if (v[next])
				continue;
			long nextV = dfs(next, v, c);
			list.add(nextV);
			population[now] += nextV;
		}
		Collections.sort(list, Collections.reverseOrder());
		for (long l : list) {
			if (population[now] <= c)
				break;
			population[now] -= l;
			count++;
		}
		return population[now];
	}

	static void input(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		population = new long[N + 1];
		city = Stream.of(("0 " + br.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();

		link = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			link[i] = new ArrayList<>();
		for (int i = 1, a, b; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			link[a].add(b);
			link[b].add(a);
		}
	}
}
