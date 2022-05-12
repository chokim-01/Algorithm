import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int frogInterest[][];
	static ArrayList<Integer> frogLotus[];
	static ArrayList<Node> edges[];

	static class Node {
		int to, cnt;

		public Node(int to, int cnt) {
			// TODO Auto-generated constructor stub
			this.to = to;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		frogInterest = new int[N + 1][4];
		frogLotus = new ArrayList[N + 1];
		edges = new ArrayList[N + 1];

		for (int i = 0; i <= N; i++) {
			frogLotus[i] = new ArrayList<>();
			edges[i] = new ArrayList<>();
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++)
				frogInterest[i][j] = Integer.parseInt(st.nextToken());
		}
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int size = st.toString().length();
			frogLotus[i].add(Integer.parseInt(st.nextToken()));
			if (size != 1)
				frogLotus[i].add(Integer.parseInt(st.nextToken()));
		}
		for (int j = 0, a, b, c; j < M; j++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken()) - 1;
			edges[a].add(new Node(b, c));
			edges[b].add(new Node(a, c));
		}

		dfs(1, new boolean[N + 1], new int[N + 1]);

		System.out.println("NO");
	}

	// ans : 연꽃 번호 당 frog
	static void dfs(int index, boolean[] visit, int[] ans) {
		if (index == N + 1) {
			System.out.println("YES");
			for (int i = 1; i <= N; i++)
				System.out.print(ans[i] + " ");
			System.exit(0);
			return;
		}

		// 연꽃에 개구리를 놓음. index : 개구리
		for (int lotus : frogLotus[index]) { // lotus
			if (visit[lotus])
				continue;
			visit[lotus] = true;
			ans[lotus] = index;
			// 들어가기 전 통나무 검사
			boolean flag = true;
			for (Node n : edges[lotus]) {
				if (!visit[n.to])
					continue;
				if (frogInterest[ans[lotus]][n.cnt] != frogInterest[ans[n.to]][n.cnt])
					flag = false;
			}
			if (flag)
				dfs(index + 1, visit, ans);
			ans[lotus] = 0;
			visit[lotus] = false;
		}
	}
}
