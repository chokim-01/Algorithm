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

		StringBuilder input = new StringBuilder();
		String s = sc.readLine();
		for (int i = 0; i < s.length(); i++) {
			input.append("#");
			input.append(s.charAt(i));
		}
		input.append("#");

		s = input.toString();
		manachers(s);

	}

	static void manachers(String s) {
		N = s.length();
		int r = 0, p = 0, max = 0;
		int dp[] = new int[N];
		for (int i = 0; i < N; i++) {
			// 대칭점
			if (i <= r)
				dp[i] = Math.min(dp[2 * p - i], r - i);
			else
				dp[i] = 0;
			// 증가
			while (i - dp[i] - 1 >= 0 && i + dp[i] + 1 < N && s.charAt(i - dp[i] - 1) == s.charAt(i + dp[i] + 1)) {
				dp[i]++;
			}
			if (r < i + dp[i]) {
				r = i + dp[i];
				p = i;
			}
			max = max < dp[i] ? dp[i] : max;
		}
		System.out.println(max);
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
			byte[] buf = new byte[100000]; // line length
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
