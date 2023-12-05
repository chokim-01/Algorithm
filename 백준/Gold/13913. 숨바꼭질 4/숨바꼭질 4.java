import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int N;
	static int K;

	// 거리 * , 거리 +, 시간
	static int[][] dx = { { 2, 0, 1 }, { 1, -1, 1 }, { 1, 1, 1 } };

	static class Node {
		int x, cnt;

		public Node(int x, int cnt) {
			this.x = x;
			this.cnt = cnt;
			// TODO Auto-generated constructor stub
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		K = sc.nextInt();

		Queue<Node> q = new LinkedList<>();

		boolean[] visit = new boolean[100001];
		int[] countAll = new int[100001];
		int[] before = new int[100001];

		Arrays.fill(before, -1);
		q.add(new Node(N, 0));
		countAll[N] = Integer.MAX_VALUE;
		visit[N] = true;

		int ans = -1;
		if (N == K) {
			System.out.println(0);
			System.out.println(K);
			return;
		}

		while (!q.isEmpty()) {
			Node n = q.poll();

			for (int i = 0; i < dx.length; i++) {
				int nx = n.x * dx[i][0] + dx[i][1];
				int ncnt = n.cnt + dx[i][2];

				if (!mapChk(nx))
					continue;
				if (visit[nx] && countAll[nx] <= ncnt)
					continue;

				visit[nx] = true;
				before[nx] = n.x;
				countAll[nx] = ncnt;
				if (nx == K) {
					ans = ncnt;
					break;
				}
				q.add(new Node(nx, ncnt));
			}
		}
		System.out.println(ans);
		int[] arr = new int[ans + 1];
		int n = ans;
		arr[n--] = K;
		while (K != N)
			arr[n--] = (K = before[K]);

		for (int a : arr)
			System.out.print(a + " ");
	}

	static boolean mapChk(int x) {
		if (x < 0 || x >= 100001)
			return false;
		return true;

	}

}