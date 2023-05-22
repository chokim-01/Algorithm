import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static int N;
	static int[] in;
	static int[] out;
	static int[] colors;
	static int[] numbers;
	static ArrayList<Integer>[] tree;
	static ArrayList<Integer>[] link;
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		Reader sc = new Reader();
		StringBuilder sb = new StringBuilder();
		ArrayList<Integer> ff = new ArrayList<>();
		N = sc.nextInt();
		visit = new boolean[N + 1];
		link = new ArrayList[N + 1];
		tree = new ArrayList[N * 2];
		colors = new int[N];
		numbers = new int[N];
		in = new int[N + 1];
		out = new int[N + 1];
		for (int i = 0; i < N; i++)
			numbers[i] = sc.nextInt();

		for (int i = 1; i <= N; i++)
			link[i] = new ArrayList<>();

		for (int i = 0, a; i < N - 1; i++) {
			a = sc.nextInt();
			link[a].add(i + 2);
		}

		visit[1] = true;
		dfs(1);
		relocation();
		initMergeTree();
		for (int i = 1; i <= N; i++) {
			long q = query(i);

			sb.append(q).append("\n");
		}
		System.out.println(sb);
	}

	static void relocation() {
		colors = new int[N];
		for (int i = 0; i < N; i++)
			colors[in[i + 1] - 1] = numbers[i];
	}

	static int cnt = 0;

	static void dfs(int now) { // oiler
		in[now] = ++cnt;
		for (int next : link[now]) {
			if (visit[next])
				continue;
			visit[next] = true;
			dfs(next);
		}
		out[now] = cnt;
	}

	static void initMergeTree() {
		for (int i = 0; i < tree.length; i++)
			tree[i] = new ArrayList<>();
		for (int i = N; i < N << 1; i++) {
			int n = i;
			do
				tree[n].add(colors[i - N]);
			while ((n /= 2) != 0);
		}
		for (int i = 0; i < N; i++)
			Collections.sort(tree[i]);
	}

	static int query(int le) {
		int l = in[le] + N - 1;
		int r = out[le] + N;
		int res = 0;
		for (; l < r; l >>= 1, r >>= 1) {
			if ((l & 1) == 1) {
				res += tree[l].size() - upperBound(tree[l], numbers[le - 1]);
				l++;
			}

			if ((r & 1) == 1) {
				--r;
				res += tree[r].size() - upperBound(tree[r], numbers[le - 1]);
			}

		}
		return res;
	}

	public static int upperBound(ArrayList<Integer> arr, int key) {
		int mid;
		int front = 0;
		int rear = arr.size();
		while (front < rear) {
			mid = (front + rear) / 2;
			if (arr.get(mid) <= key)
				front = mid + 1;
			else
				rear = mid;
		}
		return rear;
	}

	static class Reader {
		final private int BUFFER_SIZE = 1 << 16;
		private DataInputStream din;
		private byte[] buffer;
		private int bufferPointer, bytesRead;

		public Reader() {
			din = new DataInputStream(System.in);
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public Reader(String file_name) throws IOException {
			din = new DataInputStream(new FileInputStream(file_name));
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public String readLine() throws IOException {
			byte[] buf = new byte[64]; // line length
			int cnt = 0, c;
			while ((c = read()) != -1) {
				if (c == '\n')
					break;
				buf[cnt++] = (byte) c;
			}
			return new String(buf, 0, cnt);
		}

		public int nextInt() throws IOException {
			int ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');

			if (neg)
				return -ret;
			return ret;
		}

		public long nextLong() throws IOException {
			long ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');
			if (neg)
				return -ret;
			return ret;
		}

		public double nextDouble() throws IOException {
			double ret = 0, div = 1;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();

			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');

			if (c == '.') {
				while ((c = read()) >= '0' && c <= '9') {
					ret += (c - '0') / (div *= 10);
				}
			}

			if (neg)
				return -ret;
			return ret;
		}

		private void fillBuffer() throws IOException {
			bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
			if (bytesRead == -1)
				buffer[0] = -1;
		}

		private byte read() throws IOException {
			if (bufferPointer == bytesRead)
				fillBuffer();
			return buffer[bufferPointer++];
		}

		public void close() throws IOException {
			if (din == null)
				return;
			din.close();
		}
	}

}
