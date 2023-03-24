import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
	static class Node implements Comparable<Node> {
		int num, index;

		public Node(int num, int index) {
			// TODO Auto-generated constructor stub
			this.num = num;
			this.index = index;
		}

		@Override
		public int compareTo(Node o) {
			return o.num - this.num;
		}

		@Override
		public String toString() {
			return this.num + " ";
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		boolean[] flag = new boolean[N];
		Node[] arr = new Node[N];
		int[] arrC = new int[N];
		PriorityQueue<Node> pq = new PriorityQueue<>();

		String[] s = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(s[i]);
			Node node = new Node(n, i);
			arr[i] = node;
			pq.add(node);
		}
		s = br.readLine().split(" ");
		int count = 0;
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(s[i]);
			if (arr[i].num == n) {
				flag[i] = true;
				count += 1;
			}
			arrC[i] = n;
		}

		for (int i = 0; i < N; i++) {
			// find max
			int num = pq.poll().index;
			if (num >= N - i - 1)
				continue;

			if (arr[num].num == arrC[N - i - 1])
				count += 1;
			if (arr[N - i - 1].num == arrC[num])
				count += 1;
			if (count == N)
				break;
			int iTmp = arr[num].index;
			arr[num].index = arr[N - i - 1].index;
			arr[N - i - 1].index = iTmp;

			Node tmp = arr[num];
			arr[num] = arr[N - i - 1];
			arr[N - i - 1] = tmp;
		}
		System.out.println(count == N ? 1 : 0);
	}
}
