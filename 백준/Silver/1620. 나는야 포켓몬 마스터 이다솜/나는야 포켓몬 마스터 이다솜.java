import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		HashMap<String, String> map = new HashMap<>();
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			String num = String.valueOf(i + 1);
			map.put(num, s);
			map.put(s, num);
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < M; i++) {
			sb.append(map.get(br.readLine()));
			sb.append("\n");
		}
		System.out.println(sb.toString());

	}
}