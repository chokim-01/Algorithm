import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int[] s1 = Stream.of(br.readLine().split(":")).mapToInt(Integer::parseInt).toArray();
		int[] s2 = Stream.of(br.readLine().split(":")).mapToInt(Integer::parseInt).toArray();
		s2[0] += 24;
		int[] ans = new int[3];
		ans[0] = s2[0] - s1[0];
		ans[1] = s2[1] - s1[1];
		if (ans[1] < 0) {
			ans[0] -= 1;
			ans[1] += 60;
		}
		ans[2] = s2[2] - s1[2];
		if (ans[2] < 0) {
			ans[1] -= 1;
			ans[2] += 60;
		}
		if (ans[1] < 0) {
			ans[0] -= 1;
			ans[1] += 60;
		}
		if (ans[0] >= 24) {
			if (ans[1] != 0 || ans[2] != 0)
				ans[0] -= 24;
		}

		int cnt = 0;
		for (int a : ans) {
			System.out.printf("%02d", a);
			if (cnt++ < 2)
				System.out.print(":");
		}
	}
}