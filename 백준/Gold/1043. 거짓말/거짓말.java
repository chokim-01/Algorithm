import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static ArrayList<Integer> know;
	static ArrayList<Integer>[] con;
	static ArrayList<Integer>[] party;
	static boolean[] people;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		con = new ArrayList[N + 1];
		people = new boolean[N + 1];
		party = new ArrayList[M];

		for (int i = 0; i < N + 1; i++) {
			con[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++)
			party[i] = new ArrayList<>();
		Arrays.fill(people, true);

		st = new StringTokenizer(br.readLine());
		st.nextToken();
		know = new ArrayList<>();
		while (st.hasMoreTokens())
			know.add(Integer.parseInt(st.nextToken()));

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			int b = Integer.parseInt(st.nextToken());
			party[i].add(b);

			while (st.hasMoreTokens()) {
				int n = Integer.parseInt(st.nextToken());
				party[i].add(n);
				con[b].add(n);
				con[n].add(b);
				b = n;
			}
		}

		for (int k = 0; k < know.size(); k++) {
			int now = know.get(k);
			people[now] = false;
			Queue<Integer> q = new LinkedList<>();
			q.add(now);
			while (!q.isEmpty()) {
				now = q.poll();
				for (int next : con[now]) {
					if (!people[next])
						continue;
					q.add(next);
					people[next] = false;
				}
			}
		}
		int ans = 0;

		for (int i = 0; i < M; i++) {

			int j = 0;
			for (; j < party[i].size(); j++) {
				if (!people[party[i].get(j)])
					break;
			}
			if (j == party[i].size())
				ans++;
		}
		System.out.println(ans);
	}
}