import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int min = Integer.MAX_VALUE;

		Boolean[] map = br.readLine().chars().mapToObj((c) -> c == 'R').toArray(Boolean[]::new);

		// calc
		min = Math.min(min, calc(map));

		// other
		for (int i = 0; i < N; i++)
			map[i] = !map[i];
		min = Math.min(min, calc(map));

		// reverse
		List<Boolean> lst = Arrays.asList(map);
		Collections.reverse(lst);
		map = lst.toArray(map);
		min = Math.min(min, calc(map));

		// reverse other
		for (int i = 0; i < N; i++)
			map[i] = !map[i];
		min = Math.min(min, calc(map));
		System.out.println(min);
	}

	static int calc(Boolean[] map) {
		int c = 0; // true
		boolean flag = true;

		for (int i = N - 1; i >= 0; i--) {
			if (map[i]) {
				if (flag)
					continue;
				else
					c++;
			} else
				flag = false;
		}
		return c;
	}
}