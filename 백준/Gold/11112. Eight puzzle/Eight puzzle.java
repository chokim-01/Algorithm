import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public class Main {
	static class Node {
		int x, y, c;
		StringBuilder num;

		public Node(int x, int y, int c, StringBuilder num) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
			this.c = c;
			this.num = num;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String end = "123456780";
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(2, 2, 0, new StringBuilder(end)));
		HashMap<String, Integer> v = new HashMap<>();
		v.put(end, 0);

		while (!q.isEmpty()) {
			Node n = q.poll();

			for (int d = 0; d < 4; d++) {
				int nx = n.x + dxy[d][0];
				int ny = n.y + dxy[d][1];
				if (!can.apply(nx, ny))
					continue;
				int r = nx * 3 + ny;
				int t = n.x * 3 + n.y;
				char tmp = n.num.charAt(r);
				StringBuilder next = new StringBuilder(n.num);
				next.replace(r, r + 1, String.valueOf(n.num.charAt(t)));
				next.replace(t, t + 1, String.valueOf(tmp));
				if (v.containsKey(next.toString()))
					continue;
				v.put(next.toString(), n.c + 1);
				q.add(new Node(nx, ny, n.c + 1, next));
			}
		}

		StringBuilder ans = new StringBuilder();

		int[][] map = new int[3][3];
		int tc = Integer.parseInt(br.readLine());
		while (tc-- > 0) {
			br.readLine();
			for (int i = 0; i < 3; i++)
				map[i] = Stream.of(br.readLine().split("")).mapToInt(x -> x.charAt(0) == '#' ? 0 : x.charAt(0) - '0')
						.toArray();
			String s = toS(map);
			if (v.containsKey(s))
				ans.append(v.get(s));
			else
				ans.append("impossible");
			ans.append("\n");

		}
		System.out.println(ans);

	}

	static int[][] dxy = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static BiFunction<Integer, Integer, Boolean> can = (x, y) -> !(x < 0 || y < 0 || x >= 3 || y >= 3);

	static int parse(int x, int y) {
		return x * 3 + y;
	}

	static String toS(int[][] map) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				sb.append(map[i][j]);
		return sb.toString();
	}

	static int[] findStart(int[][] map) {
		int x = -1;
		int y = -1;
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (map[i][j] == 0) {
					x = i;
					y = j;
				}
		return new int[] { x, y };
	}
}
