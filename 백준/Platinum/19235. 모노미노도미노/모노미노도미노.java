import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static class Node implements Comparable<Node> {
		int num;
		int x, y;

		public Node(int x, int y) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
		}

		public Node(int num, int x, int y) {
			this.num = num;
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			if (this.num == o.num) {
				return -this.x + o.x;
			}
			return this.num - o.num;
		}

	}

	static int ans;
	static int blockCount;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		int[][] map = new int[10][4];
		int[][] map2 = new int[10][4]; // rotate
		ans = 0;
		blockCount = 0;
		for (int tc = 0; tc < N; tc++) {
			int t = sc.nextInt(); // 0 : (x,y)
			int x = sc.nextInt(); // 1 : (x,y),(x,y+1)
			int y = sc.nextInt(); // 2 : (x,y),(x+1,y)

			Queue<Node> q = new LinkedList<>();
			Queue<Node> q2 = new LinkedList<>();
			q.add(new Node(x, y));
			q2.add(new Node(y, 3 - x));

			if (t == 2) { // 가로
				q.add(new Node(x, y + 1));
				q2.add(new Node(y + 1, 3 - x));
			} else if (t == 3) { // 세로
				q.add(new Node(x + 1, y));
				q2.add(new Node(y, 2 - x));
			}
			blockCount++;
			// 입력받은 것 내리기
			downInput(q, map);
			downInput(q2, map2);

			clear(map);
			clear(map2);

			// 넘치면 제거하고 내리기
			downFull(map);
			downFull(map2);

			//print(map, map2);
		}
		System.out.println(ans + "\n" + tileCount(map, map2));
	}

	static int tileCount(int[][] map, int[][] map2) {
		int cnt = 0;
		for (int i = 6; i < 10; i++) {
			for (int j = 0; j < 4; j++) {
				if (map[i][j] > 0)
					cnt += 1;
				if (map2[i][j] > 0)
					cnt += 1;
			}
		}
		return cnt;
	}

	static void downInput(Queue<Node> q, int[][] map) {
		// 가로 , 세로
		while (!q.isEmpty()) {
			Node n = q.poll();
			map[n.x][n.y] = blockCount;
		}
		
		for (int i = 3; i >= 0; i--) {
			boolean flag = false;
			for (int j = 0; j < 4; j++) {
				if (map[i][j] != 0) {
					flag = true;
					break;
				}
			}
			if (!flag)
				continue;
			int[] tmp = map[i].clone();
			int tmpCnt = 0;
			for (int t : tmp)
				if (t > 0)
					tmpCnt++;

			int index = 4;
			outer: for (; index < 9; index++) {
				for (int a = 0; a < 4; a++) {
					if (tmp[a] == 0)
						continue;
					if (tmpCnt == 2) {
						if (map[index + 1][a + 1] != 0 || map[index + 1][a] != 0)
							break outer;
						break;
					}
					if (tmpCnt == 1) {
						if (map[index + 1][a] != 0)
							break outer;
					}
				}
			}

			for (int a = 0; a < 4; a++)
				if (tmp[a] != 0)
					map[index][a] = tmp[a];
			Arrays.fill(map[i], 0);

		}

	}

	static void downFull(int[][] map) {
		int count = 0;
		for (int i = 4; i < 6; i++) {
			for (int j = 0; j < 4; j++) {
				if (map[i][j] > 0) {
					count += 1;
					break;
				}
			}
		}
		// count 만큼 미루기
		if (count == 0)
			return;
		int cnt = count;
		while (cnt-- > 0) {
			for (int i = 9; i >= 4; i--)
				map[i] = map[i - 1].clone();
		}
		for (int i = 5; i > 5 - count; i--)
			Arrays.fill(map[i], 0);

	}

	static int[][] dxy = { { 0, -1 }, { -1, 0 } };

	static void down(int[][] map) {
		// 맵에있는 번호를 가지고 번호가 빠른 것 부터 내리기
		PriorityQueue<Node> q = new PriorityQueue<>();
		for (int i = 4; i < 10; i++) {
			for (int j = 0; j < 4; j++) {
				if (map[i][j] != 0)
					q.add(new Node(map[i][j], i, j));
				map[i][j] = 0;
			}
		}
		while (!q.isEmpty()) {
			Node n = q.poll();
			Node n2 = null;

			if (!q.isEmpty())
				if (n.num == q.peek().num)
					n2 = q.poll();

			while (n.x <= 9) {
				if (n.x == 9) {
					map[n.x][n.y] = n.num;
					if (n2 != null)
						map[n2.x][n2.y] = n2.num;
					break;
				} else if (map[n.x + 1][n.y] != 0 || (n2 != null && map[n2.x + 1][n2.y] != 0)) {
					if (n2 != null)
						map[n2.x][n2.y] = n.num;
					map[n.x][n.y] = n.num;
					break;
				}

				n.x += 1;
				if (n2 != null)
					n2.x += 1;
			}
		}

	}

	static void clear(int[][] map) {
		int clearCount = 0;
		for (int i = 6; i < 10; i++) {
			boolean flag = true;
			for (int j = 0; j < 4; j++) {
				if (map[i][j] == 0) {
					flag = false;
					break;
				}
			}
			if (flag) {
				Arrays.fill(map[i], 0);
				clearCount += 1;
			}
		}
		ans += clearCount;

		if (clearCount != 0) {
			down(map);
			clear(map);
		}
	}

	static void print(int[][] map, int[][] map2) {
		System.out.println("map1");
		for (int i = 0; i < 10; i++)
			System.out.println(Arrays.toString(map[i]));
		System.out.println("map2");
		for (int i = 0; i < 10; i++)
			System.out.println(Arrays.toString(map2[i]));
	}
}
