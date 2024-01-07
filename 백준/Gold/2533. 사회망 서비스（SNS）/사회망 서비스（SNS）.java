import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] count;
	static boolean[] v;
	static ArrayList<Integer>[] link;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input(br);
		dfs(1);
		System.out.println(Math.min(count[1][0], count[1][1]));
	}

	static void dfs(int now) {
		v[now] = true;
		count[now][0] = 1;
		for (int next : link[now]) {
			if (v[next])
				continue;
			dfs(next);
			count[now][0] += Math.min(count[next][0], count[next][1]);
			count[now][1] += count[next][0];
		}
	}

	static void input(BufferedReader br) throws IOException {
		N = Integer.parseInt(br.readLine());
		v = new boolean[N + 1];
		count = new int[N + 1][2];
		link = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			link[i] = new ArrayList<>();
		for (int i = 1; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			link[a].add(b);
			link[b].add(a);
		}
	}
}