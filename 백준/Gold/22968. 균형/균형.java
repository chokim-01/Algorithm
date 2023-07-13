import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] dp = new int[45];
		TreeMap<Integer, Integer> tree = new TreeMap<>();
		for (int i = 0; i < 3; i++)
			tree.put(dp[i] = i, i);
		for (int i = 3; i < dp.length; i++)
			tree.put(dp[i] = dp[i - 1] + dp[i - 2] + 1, i);

		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while (N-- > 0) {
			int n = Integer.parseInt(br.readLine());
			sb.append(tree.lowerEntry(n + 1).getValue()).append("\n");
		}
		System.out.println(sb);
	}
}