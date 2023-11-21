import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int T;

	static class Tree {
		int[] arr;
		int size;

		public Tree() {
			// TODO Auto-generated constructor stub
			arr = new int[100001];
			Arrays.fill(arr, Integer.MAX_VALUE);
			this.size = 0;
		}

		void add(int num) {
			arr[++this.size] = num;
			int now = this.size;
			// compare parent
			while (now != 1 && arr[now >> 1] > arr[now]) {
				int tmp = arr[now];
				arr[now] = arr[now >> 1];
				arr[now >> 1] = tmp;
				now >>= 1;
			}
		}

		int del() {
			int ret = arr[1];
			arr[1] = arr[this.size];
			arr[this.size--] = Integer.MAX_VALUE;
			int now = 1;
			while ((now << 1) <= this.size) {
				int left = now << 1;
				int right = now << 1 | 1;
				if (arr[left] >= arr[now] && arr[right] >= arr[now])
					break;
				if (arr[left] < arr[right]) {
					int tmp = arr[now];
					arr[now] = arr[left];
					arr[left] = tmp;
					now = left;
				} else {
					int tmp = arr[now];
					arr[now] = arr[right];
					arr[right] = tmp;
					now = right;
				}
			}
			return ret;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		Tree tr = new Tree();
		StringBuilder ans = new StringBuilder();

		while (T-- > 0) {
			int num = Integer.parseInt(br.readLine());
			if (num == 0) {
				if (tr.size == 0)
					ans.append(0);
				else
					ans.append(tr.del());
				ans.append("\n");
			} else
				tr.add(num);
		}
		System.out.println(ans);
	}
}
