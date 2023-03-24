import java.util.Arrays;
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

		int ans1 = -1;
		int ans2 = -1;
		int count = 0;
		for (int i = 0; i < N; i++) {
			// find max
			int num = pq.poll().index;
			if (num >= N - i - 1)
				continue;
			count++;
			if (count == K) {
				ans1 = arr[num].num;
				ans2 = arr[N - i - 1].num;
				break;
			}
			int iTmp = arr[num].index;
			arr[num].index = arr[N - i - 1].index;
			arr[N - i - 1].index = iTmp;

			Node tmp = arr[num];
			arr[num] = arr[N - i - 1];
			arr[N - i - 1] = tmp;
		}
		if (ans1 > ans2) {
			int tmp = ans1;
			ans1 = ans2;
			ans2 = tmp;
		}
		System.out.println(ans1 == -1 ? -1 : (ans1 + " " + ans2));
	}
}
