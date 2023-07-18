import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, M, K, T;
	static ArrayList<Integer> con[];
	static int[] cats;
	static boolean[] visit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		// M : dog, N : cat
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			init();

			int len = makeLink(br, st);

			int count = 0;
			for (int i = 0; i < len; i++) {
				Arrays.fill(visit, false);
				if (dfs(i))
					count++;
			}
			sb.append(K - count).append("\n");
		}
		System.out.println(sb);
	}

	static boolean dfs(int now) {
		if (visit[now])
			return false;
		visit[now] = true;
		for (int next : con[now]) {
			if (cats[next] == -1 || dfs(cats[next])) {
				cats[next] = now;
				return true;
			}
		}
		return false;
	}

	static int makeLink(BufferedReader br, StringTokenizer st) throws Exception {
		ArrayList<int[]> cat = new ArrayList<>();
		ArrayList<int[]> dog = new ArrayList<>();
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			String a = st.nextToken();
			String b = st.nextToken();
			int x = Integer.parseInt(a.substring(1)) - 1;
			int y = Integer.parseInt(b.substring(1)) - 1;

			if (a.charAt(0) == 'C')
				cat.add(new int[] { x, y });
			else
				dog.add(new int[] { x, y });

		}
		// 중복 선택
		for (int i = 0; i < cat.size(); i++)
			for (int j = 0; j < dog.size(); j++)
				if (cat.get(i)[0] == dog.get(j)[1] || cat.get(i)[1] == dog.get(j)[0])
					con[i].add(j);
		return cat.size();
	}

	static void init() {
		int max = 501;
		cats = new int[max];
		Arrays.fill(cats, -1);

		visit = new boolean[max];
		con = new ArrayList[max];
		for (int i = 0; i < max; i++)
			con[i] = new ArrayList<>();
	}
}