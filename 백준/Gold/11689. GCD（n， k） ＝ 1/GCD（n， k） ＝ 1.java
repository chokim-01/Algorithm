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
		long N = Long.parseLong(br.readLine());
		if (N == 1) {
			System.out.println(N);
			return;
		}
		ans = N;
		ArrayList<Long> list = calc(N);
		for (long l : list)
			ans = ans * ((l - 1) / (double) l);

		sb.append(String.format("%.0f", ans == N ? ans - 1 : ans)).append("\n");
		System.out.println(sb);
	}

	static ArrayList<Long> calc(long N) {
		long save = N;
		Set<Long> set = new HashSet<>();
		for (long i = 2; i * i <= N; i++) {
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
