import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {

	static boolean[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		boolean[] width = new boolean[N + 1];
		boolean[] height = new boolean[M + 1];
		width[N] = true;
		height[M] = true;

		int K = Integer.parseInt(br.readLine());
		while (K-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (a == 0) {
				height[b] = true;
			} else {
				width[b] = true;
			}
		}
		int heightMax = 0;
		int widthMax = 0;
		int widthCount = 0;
		int heightCount = 0;
		for (int i = 0; i < N + 1; i++) {
			if (width[i])
				widthCount = 0;
			widthCount++;
			widthMax = widthCount < widthMax ? widthMax : widthCount;
		}
		for (int i = 0; i < M + 1; i++) {
			if (height[i])
				heightCount = 0;

			heightCount++;
			heightMax = heightCount < heightMax ? heightMax : heightCount;
		}
		System.out.println(widthMax * heightMax);

	}

}