import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] go;
	static ArrayList<Integer>[] link;
	static ArrayList<Integer>[] depth;

	// No queue
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		link = new ArrayList[N + 1];
		depth = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			link[i] = new ArrayList<>();
			depth[i] = new ArrayList<>();
		}
		link[0].add(1);
		for (int i = 1; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			link[a].add(b);
			link[b].add(a);
		}
		go = new int[N + 1][2];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			go[i][0] = Integer.parseInt(st.nextToken());
			go[go[i][0]][1] = i;
		}
		for (int i = 0; i <= N; i++) {
			Collections.sort(link[i], new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					// TODO Auto-generated method stub
					return go[o2][1] - go[o1][1];
				}
			});
		}
		System.out.println(valid(go) ? 1 : 0);
	}

	static boolean valid(int[][] go) {
		Stack<Integer> s = new Stack<>();
		int idx = 1;
		s.push(1);
		int[] v = new int[N + 1];
		while (!s.isEmpty()) {
			int now = s.pop();
			if (v[now] == 0) {
				v[now] = idx++;
				s.add(now);
				for (int next : link[now])
					if (v[next] == 0)
						s.push(next);
			}
		}
		for (int i = 1; i <= N; i++)
			if (i != v[go[i][0]])
				return false;
		return true;
	}
}
