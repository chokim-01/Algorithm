import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		public Node(int o1x, int o1y, int o2x, int o2y, int o3x, int o3y, int o4x, int o4y) {
			super();
			this.o1 = new int[2];
			this.o2 = new int[2];
			this.o3 = new int[2];
			this.o4 = new int[2];
			o1[0] = o1x;
			o1[1] = o1y;
			o2[0] = o2x;
			o2[1] = o2y;
			o3[0] = o3x;
			o3[1] = o3y;
			o4[0] = o4x;
			o4[1] = o4y;
		}

		public Node() {
			// TODO Auto-generated constructor stub
			this.o1 = new int[2];
			this.o2 = new int[2];
			this.o3 = new int[2];
			this.o4 = new int[2];
		}

		int[] o1, o2, o3, o4;

		// 1 -> 3, 2-> 4, 3-> 1, 4-> 2
		public void changeUD() {
			changeLocUD();
//			changeLoc(o1, o3);
//			changeLoc(o2, o4);
		}

		// 1-> 2, 2-> 1, 3-> 4, 4-> 3
		public void changeRL() {
			changeLocRL();
//			changeLoc(o1, o2);
//			changeLoc(o3, o4);
		}

		public void pushMap(boolean flag) {
			int[][] tmp = { o1.clone(), o2.clone(), o3.clone(), o4.clone() };
			if (flag) {
				o1 = tmp[2].clone();
				o2 = tmp[0].clone();
				o3 = tmp[3].clone();
				o4 = tmp[1].clone();

			} else {
				o1 = tmp[1].clone();
				o2 = tmp[3].clone();
				o3 = tmp[0].clone();
				o4 = tmp[2].clone();
			}
		}

		public void changeLocUD() {
			int[] tmp = this.o1.clone();
			this.o1 = this.o3.clone();
			this.o3 = tmp.clone();
			tmp = this.o2.clone();
			this.o2 = this.o4.clone();
			this.o4 = tmp.clone();
		}

		public void changeLocRL() {
			int[] tmp = this.o1.clone();
			this.o1 = this.o2.clone();
			this.o2 = tmp.clone();
			tmp = this.o3.clone();
			this.o3 = this.o4.clone();
			this.o4 = tmp.clone();
		}

		@Override
		public String toString() {
			return Arrays.toString(o1) + " " + Arrays.toString(o2) + " " + Arrays.toString(o3) + " "
					+ Arrays.toString(o4);
		}

	}

	static int N, M, R;
	static int[][] map;

	static int[] mapNum = new int[4];
	static Node mapLoc;
	static int rotCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		setMap();

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int t = 0; t < R; t++) {
			int order = Integer.parseInt(st.nextToken());
			switch (order) {
			case 1:
				query_1();
				break;
			case 2:
				query_2();
				break;
			case 3:
				query_3();
				break;
			case 4:
				query_4();
				break;
			case 5:
				query_5();
				break;
			case 6:
				query_6();
				break;
			}
		}
		print();
	}

	static void print() throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		// map cut
		int[][][] newMap = new int[4][N / 2][M / 2];

		for (int i = 0; i <= N / 2 - 1; i++)
			for (int j = 0; j <= M / 2 - 1; j++)
				newMap[0][i][j] = map[i][j];
		for (int i = 0; i <= N / 2 - 1; i++)
			for (int j = M / 2; j < M; j++)
				newMap[1][i][j - M / 2] = map[i][j];
		for (int i = N / 2; i < N; i++)
			for (int j = 0; j <= M / 2 - 1; j++)
				newMap[2][i - N / 2][j] = map[i][j];
		for (int i = N / 2; i < N; i++)
			for (int j = M / 2; j < M; j++)
				newMap[3][i - N / 2][j - M / 2] = map[i][j];

		boolean flagI = false;
		boolean flagJ = false;
		if (mapLoc.o1[0] <= mapLoc.o4[0])
			flagI = true;
		else
			flagI = false;
		if (mapLoc.o1[1] <= mapLoc.o4[1])
			flagJ = true;
		else
			flagJ = false;

		// print 0, 1

		boolean flag = false;
		if(rotCnt %2 == 0)
			flag = true;

		if (flag) {
			// 1로 돌림
			for (int i = mapLoc.o1[0]; flagI ? i <= mapLoc.o4[0] : i >= mapLoc.o4[0]; i = (flagI ? i + 1 : i - 1)) {

				for (int c = 0; c < 2; c++)
					for (int j = mapLoc.o1[1]; flagJ ? j <= mapLoc.o4[1]
							: j >= mapLoc.o4[1]; j = (flagJ ? j + 1 : j - 1)) {
						bw.write(newMap[mapNum[c]][i][j] + " ");
					}
				bw.newLine();
			}
			for (int i = mapLoc.o1[0]; flagI ? i <= mapLoc.o4[0] : i >= mapLoc.o4[0]; i = (flagI ? i + 1 : i - 1)) {

				for (int c = 2; c < 4; c++)
					for (int j = mapLoc.o1[1]; flagJ ? j <= mapLoc.o4[1]
							: j >= mapLoc.o4[1]; j = (flagJ ? j + 1 : j - 1)) {
						bw.write(newMap[mapNum[c]][i][j] + " ");
					}
				bw.newLine();
			}
		} else {
			for (int j = mapLoc.o1[1]; flagJ ? j <= mapLoc.o4[1] : j >= mapLoc.o4[1]; j = (flagJ ? j + 1 : j - 1)) {
				for (int c = 0; c < 2; c++)
					for (int i = mapLoc.o1[0]; flagI ? i <= mapLoc.o4[0]
							: i >= mapLoc.o4[0]; i = (flagI ? i + 1 : i - 1)) {
						bw.write(newMap[mapNum[c]][i][j] + " ");
					}
				bw.newLine();
			}
			for (int j = mapLoc.o1[1]; flagJ ? j <= mapLoc.o4[1] : j >= mapLoc.o4[1]; j = (flagJ ? j + 1 : j - 1)) {
				for (int c = 2; c < 4; c++)
					for (int i = mapLoc.o1[0]; flagI ? i <= mapLoc.o4[0]
							: i >= mapLoc.o4[0]; i = (flagI ? i + 1 : i - 1)) {
						bw.write(newMap[mapNum[c]][i][j] + " ");
					}
				bw.newLine();
			}
		}
		bw.flush();
		bw.close();
	}

	// setting side
	static void setMap() {
		for (int i = 0; i < 4; i++)
			mapNum[i] = i;
		mapLoc = new Node(0, 0, 0, M / 2 - 1, N / 2 - 1, 0, N / 2 - 1, M / 2 - 1);
		rotCnt = 0;
		// 0. <=
//		mapLoc[0] = new Node(0, 0, 0, M / 2 - 1, N / 2 - 1, 0, N / 2 - 1, M / 2 - 1);
//		mapLoc[1] = new Node(0, M / 2, 0, M - 1, N / 2 - 1, M / 2, N / 2 - 1, M - 1);
//		mapLoc[2] = new Node(N / 2, 0, N / 2, M / 2 - 1, N - 1, 0, N - 1, M / 2 - 1);
//		mapLoc[3] = new Node(N / 2, M / 2, N / 2, M - 1, N - 1, M / 2, N - 1, M - 1);
	}

	static void query_1() {
		// 0,2 와 1,3이 바뀜
		changeMap(0, 2);
		changeMap(1, 3);

		mapLoc.changeUD();
	}

	static void query_2() {
		// 0,1 , 2,3이 바뀜
		changeMap(0, 1);
		changeMap(2, 3);

		mapLoc.changeRL();
	}

	static void query_3() {
		rotCnt++;
		pushMap(true);

		// 1,2,3,4 < 3,1,4,2
		mapLoc.pushMap(true);
	}

	static void query_4() {
		rotCnt--;
		pushMap(false);

		// 1,2,3,4 < 2,4,1,3
		mapLoc.pushMap(false);
	}

	static void query_5() {
		pushMap(true);
	}

	static void query_6() {
		pushMap(false);
	}

	static void pushMap(boolean flag) {
		// 90 degrees
		if (flag) {
			int tmp = mapNum[0];
			mapNum[0] = mapNum[2];
			mapNum[2] = mapNum[3];
			mapNum[3] = mapNum[1];
			mapNum[1] = tmp;

		} else { // -90 degrees
			int tmp = mapNum[0];
			mapNum[0] = mapNum[1];
			mapNum[1] = mapNum[3];
			mapNum[3] = mapNum[2];
			mapNum[2] = tmp;
		}
	}

	static void changeMap(int x, int y) {
		int tmp = mapNum[x];
		mapNum[x] = mapNum[y];
		mapNum[y] = tmp;
	}
}