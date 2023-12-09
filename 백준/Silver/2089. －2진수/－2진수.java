import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder ans = new StringBuilder();
		while (N != 0) {
			ans.append(N % -2 != 0 ? --N - N + 1 : 0);
			N /= -2;
		}
		System.out.println(ans.isEmpty() ? 0 : ans.reverse());
	}
}