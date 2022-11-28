import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int dx[][] = { { 1, -1 }, { 1, 1 }, { 2, 0 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());

		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		if (start == end) {
			System.out.println(0);
			return;
		}

		boolean[][] visit = new boolean[2][500001];

		Queue<Integer> q = new LinkedList<>();
		q.add(start);

		int time = 0;
		int dest = end;
		int count = 0;
		outer: while (!q.isEmpty()) {
			time++;
			count += time;
			dest = end + count;
			if (dest > 500000) {
				time = -1;
				break;
			}
			int qSize = q.size();
			for (int i = 0; i < qSize; i++) {
				int n = q.poll();
				for (int d = 0; d < 3; d++) {
					int nx = n * dx[d][0] + dx[d][1];
					if (!distChk(nx))
						continue;
					if (nx == dest || visit[time % 2][dest]) 
						break outer;
					if(visit[time%2][nx])
						continue;
					visit[time % 2][nx] = true;
					q.add(nx);
				}
			}
		}
		System.out.println(time);

	}

	static boolean distChk(int x) {
		if (x < 0 || x > 500000)
			return false;
		return true;
	}
}