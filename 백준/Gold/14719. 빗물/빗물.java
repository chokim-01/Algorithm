import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Main {
	static class Node {
		int x, cnt;

		public Node(int x, int cnt) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sc.nextInt();
		int M = sc.nextInt();

		int[] arr = new int[M];
		for (int i = 0; i < M; i++)
			arr[i] = sc.nextInt();

		int ans = 0;
		for (int i = 1; i < M - 1; i++) {
			// 왼쪽 최대
			int left = 0;
			for (int a = i - 1; a >= 0; a--)
				left = left > arr[a] ? left : arr[a];
			int right = 0;
			// 오른쪽 최대
			for (int a = i + 1; a < M; a++)
				right = right > arr[a] ? right : arr[a];

			left = left < right ? left : right;

			if (arr[i] < left)
				ans += left-arr[i];
		}
		System.out.println(ans);

	}
}
