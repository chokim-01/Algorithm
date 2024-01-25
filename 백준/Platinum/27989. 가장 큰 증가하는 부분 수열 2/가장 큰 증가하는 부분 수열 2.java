import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Stream;

public class Main {
	static int N;
	static long[] numbers, zipNumbers, tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		numbers = Stream.of(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
		setUnit();
		for (long n : numbers)
			update((int) n, query(0, (int) n) + zipNumbers[(int) n]);
		System.out.println(query(0, N));
	}

	static void update(int index, long num) {
		index += N;
		tree[index] = num;
		while ((index >>= 1) != 0)
			tree[index] = Math.max(tree[index << 1], tree[index << 1 | 1]);
	}

	static long query(int l, int r) {
		l += N;
		r += N;
		long res = 0;
		for (; l < r; l >>= 1, r >>= 1) {
			if ((l & 1) == 1)
				res = Math.max(res, tree[l++]);
			if ((r & 1) == 1)
				res = Math.max(res, tree[--r]);
		}
		return res;
	}

	static void setUnit() {
		zipNumbers = new long[300001];
		long[] cNums = numbers.clone();
		Arrays.sort(cNums);
		HashMap<Long, Integer> m = new HashMap<>();
		int cnt = 0;
		for (long n : cNums) {
			if (!m.containsKey(n))
				m.put(n, ++cnt);
			zipNumbers[m.get(n)] = n;
		}
		int max = 0;
		for (int i = 0; i < N; i++)
			max = (int) Math.max(max, numbers[i] = m.get(numbers[i]));
		N = (int) (max + 1);
		tree = new long[N << 1];
	}
}