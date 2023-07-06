import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] distance;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()) + 1;
		M = Integer.parseInt(st.nextToken());

		ArrayList<int[]> list = new ArrayList<>();
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			list.add(new int[] { a - 1, b, c });
			list.add(new int[] { b, a - 1, -c });
		}
		for (int i = 1; i < N; i++) {
			list.add(new int[] { i - 1, i, 1 });
			list.add(new int[] { i, i - 1, 0 });
		}
		StringBuilder answer = new StringBuilder();
		if (!belman(list))
			answer.append("NONE");
		else {
			for (int i = 1; i < N; i++)
				if (distance[i] != distance[i - 1])
					answer.append("#");
				else
					answer.append("-");
		}
		System.out.println(answer);

	}

	static boolean belman(ArrayList<int[]> list) {
		distance = new int[N];
		Arrays.fill(distance, 123456789);
		distance[0] = 0;
		for (int i = 0; i < N; i++) {
			for (int[] l : list) {
				if (distance[l[1]] > distance[l[0]] + l[2]) {
					distance[l[1]] = distance[l[0]] + l[2];
					if (i == N - 1)
						return false;
				}
			}
		}
		return true;
	}
}
