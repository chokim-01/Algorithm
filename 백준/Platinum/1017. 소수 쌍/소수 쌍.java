import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static boolean[] visit;
	static boolean[] prime;
	static int[] nums;
	static int[] links;
	static ArrayList<Integer> con[];
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		input();

		makePrime();

		setCon();

		TreeSet<Integer> answer = new TreeSet<>();
		for (int fixed : con[0]) {
			Arrays.fill(links, -1);
			links[fixed] = 0;

			int count = 1;
			for (int i = 1; i < N; i++) {
				Arrays.fill(visit, false);
				visit[0] = true;
				if (dfs(i))
					count++;
			}
			if (count == N)
				answer.add(nums[fixed]);

		}
		if (answer.isEmpty())
			System.out.println(-1);
		else
			answer.forEach(s -> sb.append(s + " "));

		System.out.println(sb);
//		System.out.println(Arrays.toString(prime));
	}

	static boolean dfs(int now) {
		if (visit[now])
			return false;
		visit[now] = true;
		for (int next : con[now]) {
			if (prime[nums[now] + nums[next]])
				continue;
			if (links[next] == -1 || dfs(links[next])) {
				links[next] = now;
				return true;
			}
		}
		return false;
	}

	static void setCon() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == j)
					continue;
				if (prime[nums[i] + nums[j]])
					continue;
				con[i].add(j);
			}
		}
	}

	static void makePrime() {
		prime = new boolean[2001];

		prime[0] = true;
		prime[1] = true;
		for (int i = 2; i < prime.length; i++) {
			if (prime[i])
				continue;
			for (int j = 2 * i; j < prime.length; j += i)
				prime[j] = true;
		}
	}

	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		links = new int[N];
		nums = new int[N];
		visit = new boolean[N];
		con = new ArrayList[N];

		st = new StringTokenizer(br.readLine());

		for (int i = 0, n; i < N; i++) {
			n = Integer.parseInt(st.nextToken());
			nums[i] = n;
			con[i] = new ArrayList<>();
		}
	}
}