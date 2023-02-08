import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static LinkedList<Node> line[];
	static int start;
	static int ans;

	static class Node {
		int y, cnt;

		public Node(int y, int cnt) {
			this.y = y;
			this.cnt = cnt;
			// TODO Auto-generated constructor stub
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		line = new LinkedList[N];

		for (int i = 0; i < N; i++)
			line[i] = new LinkedList<Node>();

		for (int i = 0; i < N; i++) {
			int cnt = 0;

			st = new StringTokenizer(br.readLine());
			int start = 0;

			while (true) {
				if (cnt++ == 0)
					start = Integer.parseInt(st.nextToken())-1;

				int a = Integer.parseInt(st.nextToken()) - 1;
				if (a == -2) {
					break;
				}
				int b = Integer.parseInt(st.nextToken());

				line[start].add(new Node(a, b));
			}

		}

		dfs(0, 0, new boolean[N]);

		ans = 0;
		dfs(start, 0, new boolean[N]);

		System.out.println(ans);

	}

	static void dfs(int x, int cnt, boolean[] visit) {
		visit[x] = true;
		if (ans < cnt) {
			ans = cnt;
			start = x;
		}
		for (Node l : line[x]) {
			if (visit[l.y])
				continue;
			visit[l.y] = true;
			dfs(l.y, cnt + l.cnt, visit);
		}

	}

}
