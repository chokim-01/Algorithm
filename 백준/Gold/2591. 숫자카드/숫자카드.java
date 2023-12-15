import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] s = ("0" + br.readLine()).chars().map(x -> x - '0').toArray();
		int[] d = new int[s.length];
		d[0] = d[1] = 1;
		for (int i = 2; i < d.length; i++) {
			d[i] = d[i - 1];
			if (s[i] == 0) {
				d[i] = d[i - 2];
				continue;
			}
			if (s[i - 1] != 0 && s[i - 1] * 10 + s[i] <= 34)
				d[i] += d[i - 2];
		}
		System.out.println(d[d.length - 1]);
	}
}