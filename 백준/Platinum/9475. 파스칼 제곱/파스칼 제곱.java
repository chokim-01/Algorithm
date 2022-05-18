import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node> {
		int tc, p, r, c;

		public Node(int tc, int p, int r, int c) {
			// TODO Auto-generated constructor stub
			this.tc = tc;
			this.p = p;
			this.c = c;
			this.r = r;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return (this.r - this.c) - (o.r - o.c);
		}
	}

	static class Ans implements Comparable<Ans> {
		int tc;
		BigInteger res;

		public Ans(int tc, BigInteger res) {
			// TODO Auto-generated constructor stub
			this.tc = tc;
			this.res = res;
		}

		@Override
		public int compareTo(Ans o) {
			// TODO Auto-generated method stub
			return this.tc - o.tc;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int K = Integer.parseInt(br.readLine());

		BigInteger[] mul = new BigInteger[100000];
		Arrays.fill(mul, new BigInteger("1"));
		PriorityQueue<Node> q = new PriorityQueue<>();
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int tc = Integer.parseInt(st.nextToken());
			int P = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			q.add(new Node(tc, P, R, C));
		}
		PriorityQueue<Ans> rq = new PriorityQueue<>();

		int cnt = 0;
		while (!q.isEmpty()) {
			Node n = q.poll();
			while (cnt < n.r - n.c) {
				for (int i = 1; i < 100000; i++) {
					if (mul[i].bitLength() > 64)
						break;
					mul[i] = mul[i].add(mul[i - 1]);
				}
				cnt++;
			}
			BigInteger answer = new BigInteger("1");
			if (n.r == n.c)
				answer = new BigInteger("1");
			else if (n.r < n.c)
				answer = new BigInteger("0");
			else
				answer = new BigInteger(String.valueOf(n.p)).pow(n.r - n.c).multiply(mul[n.c]);

			rq.add(new Ans(n.tc, answer));
		}
		while (!rq.isEmpty()) {
			Ans n = rq.poll();
			bw.write(n.tc + " " + n.res);
			bw.newLine();
		}

		bw.flush();
		bw.close();
	}
}