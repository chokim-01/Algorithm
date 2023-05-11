import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			sb.append(N).append(" ");
			int[] potatos = new int[10];
			int index = 0;
			while (st.hasMoreTokens())
				potatos[index++] = Integer.parseInt(st.nextToken());

			String answer = "NO";
			outer: for (int num = 1; num < 1 << 10; num++) {
				int sum = 0;
				for (int i = 0; i < 10; i++) {
					if ((num & 1 << i) == 1 << i)
						sum += potatos[i];
					if (sum == M) {
						answer = "YES";
						break outer;
					}
					if (sum > M)
						continue outer;
				}
			}
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}

}