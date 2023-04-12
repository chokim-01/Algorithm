import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			Queue<int[]> q = new LinkedList<int[]>();
			int N = sc.nextInt();
			int M = sc.nextInt();
			// index, importance
			for (int i = 0; i < N; i++) {
				q.add(new int[] { i, sc.nextInt() });
			}
			int time = 1;
			while (true) {
				int max = q.peek()[1];
				for (int[] ar : q) {
					if (max < ar[1]) {
						max = -1;
						break;
					}
				}

				if (max == -1) {
					q.add(q.poll());
				} else {
					if (q.poll()[0] == M)
						break;
					
					time++;
				}
			}
			System.out.println(time);

		}

	}
}