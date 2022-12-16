import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] ground = new int[N];
		int[] save = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		int index = 0;
		while (st.hasMoreTokens())
			ground[index++] = Integer.parseInt(st.nextToken());

		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int k = Integer.parseInt(st.nextToken());

			save[a] += k;
			save[b + 1] += -k;
		}
		StringBuffer sb = new StringBuffer();
		long weight = 0;
		for (int i = 0; i < N; i++) {
			weight += save[i];
			sb.append((weight + ground[i]) + " ");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();

	}
}