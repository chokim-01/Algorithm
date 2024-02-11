import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

public class Main {

	static class UF {
		int[] par;

		public UF() {
			// TODO Auto-generated constructor stub
			this.par = IntStream.rangeClosed(0, G).map(x -> x).toArray();
		}

		void union(int a, int b) {
			a = find(a);
			b = find(b);
			if (a < b)
				par[b] = a;
			else
				par[a] = b;
		}

		int find(int x) {
			if (x == par[x])
				return x;
			return par[x] = find(par[x]);
		}
	}

	static int G, P;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		G = Integer.parseInt(br.readLine());
		P = Integer.parseInt(br.readLine());

		int ans = 0;
		UF uf = new UF();
		while (P-- > 0) {
			int n = Integer.parseInt(br.readLine());
			n = uf.find(n);
			if (n == 0)
				break;
			ans++;
			uf.union(n, n - 1);
		}
		System.out.println(ans);

	}
}