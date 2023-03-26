import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static int N;
	static boolean[] visit;
	static int[] parents;
	static Node[] sharks;
	static ArrayList<Integer> con[];

	static class Node {
		int info[];

		public Node(int[] info) {
			this.info = info;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		sharks = new Node[N];
		parents = new int[N];
		visit = new boolean[N];
		con = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			con[i] = new ArrayList<>();
			Node n = new Node(Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray());
			sharks[i] = n;
		}
		makeCon();
		int ans = 0;
		Arrays.fill(parents, -1);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 2; j++) {
				visit = new boolean[N];
				if (dfs(i))
					ans++;
			}
		}
		System.out.println(N - ans);
	}

	static boolean dfs(int index) {
		if (visit[index])
			return false;
		visit[index] = true;

		for (int next : con[index]) {
			if (parents[next] == -1 || dfs(parents[next])) {
				parents[next] = index;
				return true;
			}
		}
		return false;
	}

	static void makeCon() {
		for (int i = 0; i < N; i++) {
			outer: for (int j = 0; j < N; j++) {
				if (i == j)
					continue;
				for (int k = 0; k < 3; k++) {
					if (sharks[i].info[k] < sharks[j].info[k])
						continue outer;
				}
				if(con[j].contains(i))
					continue;
				con[i].add(j);
			}
		}
	}
}