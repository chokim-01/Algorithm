import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.stream.Stream;

public class Main {
	static int N;
	static int[] numbers;
	static int[] tree;

	public static void main(String[] args) throws IOException {
		input();
		tree = new int[N << 1];

		System.out.println(solve());
	}

	static long solve() {
		long ret = 0;
		for (int i = numbers.length - 1; i >= 0; i--) {

			ret += query(numbers[i]);
			update(numbers[i]);
		}

		return ret;
	}

	static void update(int id) {
		id += N;
		do
			tree[id] += 1;
		while ((id >>= 1) != 0);
	}

	static int query(int r) {
		int ret = 0;
		int l = N;
		r += N;
		for (; l < r; l >>= 1, r >>= 1) {
			if ((l & 1) == 1) {
				ret += tree[l];
				l++;
			}
			if ((r & 1) == 1) {
				r--;
				ret += tree[r];
			}
		}
		return ret;
	}

	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br.readLine();
		N = 1000001;
		numbers = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
	}

}