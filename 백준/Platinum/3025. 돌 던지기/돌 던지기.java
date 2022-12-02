import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static char[][] map;
	static Stack<saveNode>[] saveMap;

	static class Node {
		int depth;
		char c;

		public Node(int depth, char c) {
			// TODO Auto-generated constructor stub
			this.depth = depth;
			this.c = c;
		}

		@Override
		public String toString() {
			return "[" + this.depth + " : " + this.c + "] ";
		}
	}

	static class saveNode {
		int x, y;

		public saveNode(int x, int y) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N + 1][M];
		saveMap = new Stack[M];

		for (int i = 0; i < M; i++) {
			map[N][i] = 'X';
			saveMap[i] = new Stack<>();
		}

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++)
				map[i][j] = s.charAt(j);
		}

		int K = Integer.parseInt(br.readLine());

		while (K-- > 0) {
			int n = Integer.parseInt(br.readLine()) - 1;

			if (!saveMap[n].isEmpty()) { // 들어온 것 빼기
				saveNode sn = saveMap[n].peek();
				while (!saveMap[n].isEmpty()) {
					sn = saveMap[n].pop();
					if (map[sn.x][sn.y] == '.')
						break;
				}
				throwRock(sn.x, sn.y, n);
			} else // 해당 라인 첫 실행
				throwRock(0, n, n);
		}
		bw.write(print());
		bw.flush();
		bw.close();
	}

	static String print() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++)
				sb.append(map[i][j]);
			sb.append("\n");
		}
		return sb.toString();
	}

	static int cnt = 0;

	static void throwRock(int depth, int num, int oNum) {
		cnt++;
		// 위치 저장
		while (map[depth][num] == '.') {
			saveMap[oNum].push(new saveNode(depth, num));
			depth++;
		}

		boolean left = false;
		boolean right = false;
		if (num > 0 && depth > 0 && map[depth][num - 1] == '.' && map[depth - 1][num - 1] == '.')
			left = true;
		if (num < M - 1 && depth > 0 && map[depth][num + 1] == '.' && map[depth - 1][num + 1] == '.')
			right = true;

		if (map[depth][num] == 'X') {
			map[depth - 1][num] = 'O';
			return;
		} else {
			if (!left && !right) {
				map[depth - 1][num] = 'O';
				return;
			}
			// find left
			if (left)
				throwRock(depth, num - 1, oNum);
			// find right
			else if (right)
				throwRock(depth, num + 1, oNum);
		}
		return;
	}
}