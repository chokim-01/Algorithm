import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int N, M;

	static Stack<Integer> stack;
	static int[] scc;
	static boolean[] visit;
	static ArrayList<Integer>[] link;
	static ArrayList<Integer>[] rLink;

	public static void main(String[] args) throws IOException {

		/*
		 * 간단하게 생각하면. dfs를 통해 끝 부분 부터 스택에 저장한다 stack top 부터 rDfs로 번호를 부여하고 scc에저장.
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			input(br);
			for (int i = 1; i <= N; i++) {
				if (visit[i])
					continue;
				dfs(i);
			}
			visit = new boolean[N + 1];
			int ans = 0;
			int number = 0;
			while (!stack.isEmpty()) {
				int n = stack.pop();
				if (visit[n])
					continue;
				rDfs(n, ++number);
				ans++;
			}

			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}

	static void dfs(int now) {
		visit[now] = true;
		for (int next : link[now]) {
			if (visit[next])
				continue;
			dfs(next);
		}
		stack.add(now);
	}

	static void rDfs(int now, int number) {
		visit[now] = true;
		scc[now] = number;
		for (int next : link[now]) {
			if (visit[next])
				continue;
			rDfs(next, number);
		}
	}

	static void input(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		scc = new int[N + 1];
		visit = new boolean[N + 1];
		link = new ArrayList[N + 1];
		rLink = new ArrayList[N + 1];
		stack = new Stack<>();
		for (int i = 1; i <= N; i++) {
			link[i] = new ArrayList<>();
			rLink[i] = new ArrayList<>();
		}

		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			link[a].add(b);
			rLink[b].add(a);
		}
	}
}
