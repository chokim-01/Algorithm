import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int min;
	static int minX, minY;
	static int[][] map;
	static boolean[][] visit;

	static class Node {
		int x, y, d;

		public Node(int x, int y, int d) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visit = new boolean[N][M];
		min = Integer.MAX_VALUE;
		minX = -1;
		minY = -1;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0, n; j < M; j++) {
				n = Integer.parseInt(st.nextToken());

				if (min > n && (i + j) % 2 == 1) {
					min = n;
					minX = i;
					minY = j;
				}
			}
		}
		boolean searchFlag = true;
		if (N % 2 == 1)
			searchFlag = false;
		else if (M % 2 == 1) {
			searchFlag = false;
		}

		// minX,minY를 제하고
		if (N % 2 == 0 && M % 2 == 1) {
			bw.write(bfs2());
		} else
			bw.write(bfs(searchFlag));
		bw.flush();
		bw.close();
	}

	// 하 우 상 우
	static int[][] detectDxy = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, 1 } };
	// 우 하 좌 하
	static int[][] startDxy = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { 1, 0 } };
	// 좌 상 우 상
	static int[][] endDxy = { { 0, 1 }, { -1, 0 }, { 0, -1 }, { -1, 0 } };

	// 하 우 상 우
	static String bfs2() {
		StringBuffer sb = new StringBuffer();
		int d = 0;
		int yRange = M;
		for (int j = 0; j < yRange; j++) {

			for (int c = 0; c < N - 1; c++) {
				String s = convertDxy(detectDxy[d][0], detectDxy[d][1], true);
				sb.append(s);
			}
			d = (d + 1) % 4;
			if (j != yRange - 1)
				sb.append(convertDxy(detectDxy[d][0], detectDxy[d][1], true));
			d = (d + 1) % 4;
		}
		return sb.toString();
	}

	static String bfs(boolean flag) {
		StringBuffer startSb = new StringBuffer();

		int d = 0;
		int xRange = N;
		if (flag)
			xRange = minX / 2 * 2;

		boolean findFlag = false;

		// 해당 되는 지점까지 진행
		for (int i = 0; i < xRange; i++) {
			findFlag = true;
			for (int c = 0; c < M - 1; c++) {
				String s = convertDxy(startDxy[d][0], startDxy[d][1], true);
				startSb.append(s);
			}
			d = (d + 1) % 4;
			if (i != xRange - 1)
				startSb.append(convertDxy(startDxy[d][0], startDxy[d][1], true));
			d = (d + 1) % 4;
		}
		StringBuffer endSb = new StringBuffer();
		d = 0;
		for (int i = N - 1; i > xRange + 1; i--) {
			for (int c = 0; c < M - 1; c++) {
				String s = convertDxy(endDxy[d][0], endDxy[d][1], false);
				endSb.append(s);
			}
			d = (d + 1) % 4;
			if (i != xRange + 2)
				endSb.append(convertDxy(endDxy[d][0], endDxy[d][1], false));
			d = (d + 1) % 4;
		}
		// 하 우 상 우로 계산 발견하면 -1.
		if (!flag)
			return startSb.toString();
		int ex = minX / 2 * 2 + 1;
		int ey = M - 1;

		if (findFlag)
			startSb.append("D");
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(minX / 2 * 2, 0, 0));
		while (!q.isEmpty()) {
			Node n = q.poll();
			int nx = n.x + detectDxy[n.d][0];
			int ny = n.y + detectDxy[n.d][1];
			if (nx == minX && ny == minY)
				n.d = (n.d + 3) % 4;

			nx = n.x + detectDxy[n.d][0];
			ny = n.y + detectDxy[n.d][1];

			startSb.append(convertDxy(detectDxy[n.d][0], detectDxy[n.d][1], true));
			if (nx == ex && ny == ey)
				break;
			q.add(new Node(nx, ny, (n.d + 1) % 4));
		}
		if (endSb.length() != 0)
			endSb.insert(0, "D");
		startSb.append(endSb);
		return startSb.toString();
	}

	static String convertDxy(int x, int y, boolean flag) {
		if (x == 1 && y == 0) {
			if (flag)
				return "D";
			else
				return "U";

		}
		if (x == 0 && y == 1) {
			if (flag)
				return "R";
			else
				return "L";
		}
		if (x == -1 && y == 0) {
			if (flag)
				return "U";
			else
				return "D";
		} else {
			if (flag)
				return "L";
			else
				return "R";
		}
	}

	static boolean mapChk(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= M)
			return false;
		return true;
	}
}