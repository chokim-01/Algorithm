import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static int N, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int cnt = 0;
		String[] s = new String[N];
		while (N-- > 0)
			s[N] = br.readLine();
		for (int j = 0; j < K; j++) {
			Map<Character, Integer> map = new HashMap<>();
			int max = 0;
			for (int i = 0; i < s.length; i++) {
				char c = s[i].charAt(j);
				map.put(c, map.getOrDefault(c, 0) + 1);
				max = Math.max(max, map.get(c));
			}

			char c = rChar(max, map);
			cnt += s.length - max;
			sb.append(c);
		}
		System.out.println(sb);
		System.out.println(cnt);
	}

	static char rChar(int max, Map<Character, Integer> map) {
		if (map.getOrDefault('A', 0) == max)
			return 'A';
		if (map.getOrDefault('C', 0) == max)
			return 'C';
		if (map.getOrDefault('G', 0) == max)
			return 'G';
		return 'T';
	}
}