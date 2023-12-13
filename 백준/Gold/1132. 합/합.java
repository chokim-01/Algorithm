import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		HashSet<Character> front = new HashSet<>();
		HashSet<Character> use = new HashSet<>();
		long[] arr = new long[10];
		while (N-- > 0) {
			String s = br.readLine();
			front.add(s.charAt(0));
			long m = 1;
			for (int i = s.length() - 1; i >= 0; i--) {
				char c = s.charAt(i);
				arr[c - 'A'] += m;
				use.add(c);
				m *= 10;
			}
		}
		if (use.size() == 10) {
			int minA = 0;
			long min = Long.MAX_VALUE;
			for (int i = 0; i < 10; i++) {
				if (front.contains((char) (i + 'A')))
					continue;
				if (min > arr[i]) {
					min = arr[i];
					minA = i;
				}
			}
			arr[minA] = 0L;
		}
		Arrays.sort(arr);
		long ans = 0;
		int m = 9;
		for (int i = 9; i >= 0; i--)
			ans += arr[i] * m--;
		System.out.println(ans);
	}
}