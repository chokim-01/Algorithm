import java.util.PriorityQueue;
import java.util.Scanner;

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

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();

		Node[] arr = new Node[N];
		PriorityQueue<Node> pq = new PriorityQueue<>();

		for (int i = 0; i < N; i++) {
			int n = sc.nextInt();
			Node node = new Node(n, i);
			arr[i] = node;
			pq.add(node);
		}

		boolean flag = false;
		int count = 0;
		for (int i = 0; i < N; i++) {
			// find max
			int num = pq.poll().index;
			if (num >= N - i - 1)
				continue;
			int iTmp = arr[num].index;
			arr[num].index = arr[N - i - 1].index;
			arr[N - i - 1].index = iTmp;

			Node tmp = arr[num];
			arr[num] = arr[N - i - 1];
			arr[N - i - 1] = tmp;
			if (++count == K) {
				flag = true;
				break;
			}
		}

		StringBuilder sb = new StringBuilder();
		if (flag)
			for (Node n : arr)
				sb.append(n);
		else
			sb.append("-1");
		System.out.println(sb);
	}
}
