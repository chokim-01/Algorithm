import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class Main {
	static final int MAX = 1299710;
	static boolean prime[];
	static TreeSet<Integer> tSet;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		prime = new boolean[MAX];
		makePrime();
		tSet = new TreeSet<>();
		tSet.add(0);
		for (int i = 0; i < MAX; i++)
			if (!prime[i])
				tSet.add(i);

		int T = Integer.parseInt(br.readLine());
		StringBuilder ans = new StringBuilder();
		while (T-- > 0) {
			int n = Integer.parseInt(br.readLine());
			if (!prime[n])
				ans.append(0);
			else
				ans.append(tSet.higher(n) - tSet.lower(n));
			ans.append("\n");
		}
		System.out.println(ans);

	}

	static void makePrime() {
		prime[0] = true;
		prime[1] = true;
		for (int i = 2; i < MAX; i++) {
			if (prime[i])
				continue;
			for (int j = 2 * i; j < MAX; j += i)
				prime[j] = true;
		}
	}
}
