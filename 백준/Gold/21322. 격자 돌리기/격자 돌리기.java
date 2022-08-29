import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static ArrayList<Integer>[] beltList;
	static int[][] dxy = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static int[][] d = { { 0, 0 }, { 0, 1 }, { 1, 1 }, { 1, 0 } };
	static long[] moveBelts;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		// map to list
		mapToList(map);

		moveBelts = new long[N / 2];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int order = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken());

			// 2와 3 해당되는 것만 변경.
			switch (order) {
			case 1:
				moveBelts[a] = moveBelts[a] + b;
				break;
			case 2:
				rotateSquare(a, b - 1);
				break;
			case 3:
				int[] xy = calcXY(a, b - 1);
				if (moveBelts[xy[0]] != 0)
					moveBelt(xy[0], moveBelts[xy[0]]);

				bw.write(getAns(xy[0], xy[1])+"\n");
				break;
			}
		}
		bw.flush();
		bw.close();
	}

	static void mapToList(int[][] map) {
		int cntOfRound = N / 2;

		beltList = new ArrayList[cntOfRound];
		for (int i = 0; i < cntOfRound; i++)
			beltList[i] = new ArrayList<>();

		for (int roundNum = 0; roundNum < cntOfRound; roundNum++) {
			int roundCnt = N - 2 * roundNum;
			int x = roundNum;
			int y = roundNum;

			for (int d = 0; d < 4; d++) {
				for (int rCnt = 1; rCnt < roundCnt; rCnt++) {
					beltList[roundNum].add(map[x][y]);
					x += dxy[d][0];
					y += dxy[d][1];
				}
			}
		}
	}

	static void moveBelt(int a, long b) {
		int bSize = beltList[a].size();
		int c = (int) (b % bSize);
		
		ArrayList<Integer> tmp = new ArrayList<Integer>(beltList[a].subList(bSize - c, bSize));
		
		while (c-- > 0)
			beltList[a].remove(beltList[a].size() - 1);
		beltList[a].addAll(0, tmp);
		
		moveBelts[a] = 0;
	}

	static int[] calcXY(int x, int y) {
		int[] arr = new int[] { x, y, N - 1 - x, N - 1 - y };
		Arrays.sort(arr);
		int a = arr[0];
		int b = 0;
		if (x <= y)
			b = x - a + y - a;
		else {
			int maxXY = (N - 1) - a;
			b = 4 * maxXY - x - y - 2 * a;
		}
		return new int[] { a, b };
	}

	static void rotateSquare(int a, int b) {
		int[][] arr = new int[4][2];
		
		for (int i = 0; i < 4; i++)
			arr[i] = calcXY(a + d[i][0], b + d[i][1]);

		for (int i = 0; i < 4; i++) {
			if (moveBelts[arr[i][0]] == 0)
				continue;
			moveBelt(arr[i][0], moveBelts[arr[i][0]]);
		}
		
		int tmp = beltList[arr[3][0]].get(arr[3][1]);
		
		for (int i = 3; i > 0; i--)
			beltList[arr[i][0]].set(arr[i][1], beltList[arr[i - 1][0]].get(arr[i - 1][1]));
		beltList[arr[0][0]].set(arr[0][1], tmp);
	}

	static int getAns(int a, int b) {
		return beltList[a].get(b);
	}
}
