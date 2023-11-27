import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] lst = new int[N];
		for (int i = 0; i < N; i++) {
			int a = Integer.parseInt(br.readLine());
			lst[i] = a;
		}
		StringBuilder ans = new StringBuilder();
		Arrays.sort(lst);
		for (int n : lst)
			ans.append(n).append("\n");
		System.out.println(ans);
	}
}