import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

class Main {
	static double ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {
			int N = Integer.parseInt(br.readLine());
			if (N == 0)
				break;
			ans = N;

			ArrayList<Integer> list = calc(N);
			for (int l : list)
				ans = ans * ((l - 1) / (double) l);

			sb.append(String.format("%.0f", ans == N ? ans - 1 : ans)).append("\n");
		}
		System.out.println(sb);
	}

	static ArrayList<Integer> calc(int N) {
		int save = N;
		Set<Integer> set = new HashSet<>();
		for (int i = 2; i * i <= N; i++) {
			while (N % i == 0) {
				set.add(i);
				N /= i;
			}
		}
		if (N != 1 && save != N)
			set.add(N);
		return new ArrayList<>(set);
	}
}
