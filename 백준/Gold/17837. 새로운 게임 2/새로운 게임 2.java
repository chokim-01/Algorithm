import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {

	static int N, K;
	static int[][] dxy = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };

	static class Node {
		int id, x, y, dir;

		public Node(int id, int x, int y, int dir) {
			// TODO Auto-generated constructor stub
			this.id = id;
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 체스판 크기와 말의 개수
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		// 체스판 각 단위의 색
		// 0 : 흰색, 1 : 빨간색, 2 : 파란색
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		// 순서가 빠를 수록 아래에 있음.
		Queue<Integer> pieceMap[][] = new LinkedList[N][N];
		// 순서를 저장할 맵
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				pieceMap[i][j] = new LinkedList<>();

		// 체스말 행, 열, 이동방향
		List<Node> chessPieces = new ArrayList<>();
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken()) - 1;
			Node n = new Node(i, x, y, dir);
			chessPieces.add(n);
			pieceMap[x][y].add(i);
		}
		int time = 0;
		outer : while (++time <= 1000) {
			// 항상 위에 올려져 있는 말도 함께 이동한다.
			for (Node n : chessPieces) {
				int nx = n.x + dxy[n.dir][0];
				int ny = n.y + dxy[n.dir][1];
				// 이동하려는 칸이 파란색 / 맵 밖인 경우
				if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == 2) {
					// 이동 방향을 반대로 한다.

					n.dir = n.dir / 2 * 2 + (n.dir % 2 == 0 ? 1 : 0);
					// 바꾼 후 이동하려는 칸이 파란색인 경우는 이동하지 않는다.
					nx = n.x + dxy[n.dir][0];
					ny = n.y + dxy[n.dir][1];
				}
				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;
				// 파란색에서 방향이 바뀌었으므로 그대로 진행
				if (map[nx][ny] == 0) {
					// 이동하려는 칸이 흰색인 경우
					// 모든 말이 이동한다.
					int tmpX = n.x;
					int tmpY = n.y;
					boolean flag = false;
					int qsize = pieceMap[tmpX][tmpY].size();
					for (int i = 0; i < qsize; i++) {
						int index = pieceMap[tmpX][tmpY].poll();
						if (index == n.id)
							flag = true;
						if (!flag)
							pieceMap[tmpX][tmpY].add(index);
						else {
							Node nn = chessPieces.get(index);
							nn.x = nx;
							nn.y = ny;
							pieceMap[nx][ny].add(index);
						}
					}
				}
				if (map[nx][ny] == 1) {
					// 이동하려는 칸이 빨간색인 경우
					boolean flag = false;
					Stack<Integer> tmp = new Stack<>();

					int tmpX = n.x;
					int tmpY = n.y;
					int qsize = pieceMap[n.x][n.y].size();
					for (int i = 0; i < qsize; i++) {
						int index = pieceMap[tmpX][tmpY].poll();
						if (index == n.id)
							flag = true;
						if (!flag)
							pieceMap[tmpX][tmpY].add(index);
						else {
							Node nn = chessPieces.get(index);
							nn.x = nx;
							nn.y = ny;
							tmp.add(index);
						}
					}
					while (!tmp.isEmpty()) 
						pieceMap[nx][ny].add(tmp.pop());
				}
				if (endFlag(pieceMap))
					break outer;
			}
		}

		// 출력 : 게임이 종료되는 턴의 번호 ( 1000 초과 : -1 )
		System.out.println(time > 1000 ? -1 : time);
	}

	static void print(Queue<Integer> pieceMap[][]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(pieceMap[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	static boolean endFlag(Queue<Integer> pieceMap[][]) {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (pieceMap[i][j].size() >= 4)
					return true;
		return false;

	}

}
