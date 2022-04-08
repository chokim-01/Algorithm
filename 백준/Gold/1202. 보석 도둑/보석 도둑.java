import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static class Jew implements Comparable<Jew> {
		int m, v;

		public Jew(int m, int v) {
			// TODO Auto-generated constructor stub
			this.m = m;
			this.v = v;
		}

		@Override
		public int compareTo(Jew o) {
			// TODO Auto-generated method stub
			if (this.m == o.m)
				return o.v - this.v;
			return this.m - o.m;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] list = new int[N][2];
		int[] knapsack = new int[K];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			list[i][0] = M;
			list[i][1] = V;
		}
		for (int i = 0; i < K; i++)
			knapsack[i] = Integer.parseInt(br.readLine());

		Arrays.sort(list, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				if (o1[0] == o2[0])
					return o2[1] - o1[1];
				return o1[0] - o2[0];
			}
		});
		Arrays.sort(knapsack);

		long ans = 0;
		// 가방 : c 기준 정렬
		// 무게순, 보석 내림차순으로 정렬했으므로 이전것들은 필요 없음.?
		PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
		int j = 0;
		for (int i = 0; i < K; i++) {
			// 보석 : m, m==v? v 기준 정렬
			for (; j < N;j++) {
				if (knapsack[i] < list[j][0]) // 선택한 knapsack을 제거해야함..
					break;
				q.add(list[j][1]); // 선택 : 지금까지 추가했던 것들 중 가장 큰것. pq써야함
				if(list[j][0] == knapsack[i]) {
					j++;
					break;
				}
			}
			if(!q.isEmpty()) // 가장 큰것 빼줌
				ans += q.poll();
		}
		System.out.println(ans);

	}

}
