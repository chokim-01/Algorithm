import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		System.out.println(dfs(0, 0, N / 2));
	}

	static int dfs(int x, int y, int c) {
		if (c == 1) {
			ArrayList<Integer> list = new ArrayList<>();
			for (int a = x; a < x + 2; a++)
				for (int b = y; b < y + 2; b++)
					list.add(map[a][b]);
			sort(list);
			return list.get(1);
		}
		int cc = c >> 1;
		ArrayList<Integer> list = new ArrayList<>();
		list.add(dfs(x, y, cc));
		list.add(dfs(x, y + c, cc));
		list.add(dfs(x + c, y, cc));
		list.add(dfs(x + c, y + c, cc));
		sort(list);
		return list.get(1);
	}

	static void sort(ArrayList<Integer> list) {
		Collections.sort(list, Collections.reverseOrder());
	}
}
