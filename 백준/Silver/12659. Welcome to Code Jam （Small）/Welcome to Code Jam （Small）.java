import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
	static HashMap<Character, ArrayList<Integer>> alphabets;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String str = "welcome to code jam";
		alphabets = new HashMap<>();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (!alphabets.containsKey(c))
				alphabets.put(c, new ArrayList<>());
			alphabets.get(c).add(i);
		}

		int N = Integer.parseInt(br.readLine());
		for (int i = 1; i <= N; i++) {
			sb.append("Case #").append(i).append(": ");
			int[] dp = new int[str.length()];
			String s = br.readLine();
			for (char c : s.toCharArray()) {
				if (c == 'w')
					dp[0] += 1;
				else if (alphabets.containsKey(c))
					for (int n : alphabets.get(c))
						dp[n] += dp[n - 1];
			}
			int ans = dp[18] % 10000;
			sb.append(String.format("%04d", ans));

			sb.append("\n");
		}
		System.out.println(sb);
	}

}