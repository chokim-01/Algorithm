import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int N;
	static int[] tree;
	static int[] numbers;

	static class Node implements Comparable<Node> {
		int x, index;

		public Node(int x, int index) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.index = index;
		}

		@Override
		public int compareTo(Main.Node o) {
			// TODO Auto-generated method stub
			return this.x - o.x;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input(br);
		StringBuilder ans = new StringBuilder();
		for (int number : numbers) {
			ans.append(query(number, tree.length) + 1).append("\n");
			update(number);
		}
		System.out.println(ans);
	}

	static int query(int a, int b) {
		a += N;
		int res = 0;
		for (; a < b; a >>= 1, b >>= 1) {
			if ((a & 1) == 1) {
				res += tree[a];
				a++;
			}
			if ((b & 1) == 1) {
				b--;
				res += tree[b];
			}
		}
		return res;
	}

	static void update(int index) {
		index += N;
		do {
			tree[index] += 1;
		} while ((index >>= 1) != 0);

	}

	static void zipNumbers(Node[] numb) {
		numbers = new int[N];
		Arrays.sort(numb);
		int cnt = 0;
		for (Node n : numb)
			numbers[n.index] = ++cnt;
		tree = new int[(cnt + 1) << 1];
	}

	static void input(BufferedReader br) throws IOException {
		N = Integer.parseInt(br.readLine());
		Node[] numb = new Node[N];
		for (int i = 0; i < N; i++)
			numb[i] = new Node(Integer.parseInt(br.readLine()), i);
		zipNumbers(numb);
	}
}