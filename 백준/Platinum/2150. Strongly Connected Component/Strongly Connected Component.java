import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
		input();

		/*
		 * 간단하게 생각하면.
		 * dfs를 통해 끝 부분 부터 스택에 저장한다
		 * stack top 부터 rDfs로 번호를 부여하고 scc에저장.
		 */
		// 끝 저장
		for (int i = 1; i <= N; i++) {
			if (visit[i])
				continue;
			dfs(i);
		}

		Arrays.fill(visit, false);
		int count = 0;
		while (!stack.isEmpty()) {
			int now = stack.pop();
			if (visit[now])
				continue;
			rDfs(now, ++count); // 번호 부여
		}
		StringBuilder sb = new StringBuilder();
		sb.append(count).append("\n");

		HashMap<Integer, ArrayList<Integer>> answer = new HashMap<>();
		for (int i = 1; i <= N; i++) {
			if (!answer.containsKey(scc[i]))
				answer.put(scc[i], new ArrayList<>());
			answer.get(scc[i]).add(i);
		}
		ArrayList<Integer> keyList = new ArrayList<>(answer.keySet());
		keyList.sort((a, b) -> answer.get(a).get(0) - answer.get(b).get(0));
		for (int k : keyList) {
			answer.get(k).forEach(c -> {
				sb.append(c + " ");
			});
			sb.append(-1).append("\n");
		}

		System.out.println(sb);
	}

	// 끝부터.stack
	static void dfs(int now) {
		visit[now] = true;
		for (int next : link[now]) {
			if (visit[next])
				continue;
			dfs(next);
		}
		stack.add(now);
	}

	static void rDfs(int now, int num) {
		visit[now] = true;
		scc[now] = num;

		for (int next : rLink[now]) {
			if (visit[next])
				continue;
			rDfs(next, num);
		}
	}

	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
