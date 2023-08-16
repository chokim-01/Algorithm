import java.util.ArrayList;
import java.util.Scanner;

class UF {
	int[] parent, rank;

	public UF(int n) {
		parent = new int[n + 1];
		rank = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			parent[i] = i;
			rank[i] = 1;
		}
	}

	public int find(int x) {
		if (parent[x] != x) {
			parent[x] = find(parent[x]);
		}
		return parent[x];
	}

	public void union(int x, int y) {
		int rootX = find(x);
		int rootY = find(y);

		if (rootX == rootY) {
			return;
		}

		if (rank[rootX] > rank[rootY]) {
			parent[rootY] = rootX;
		} else if (rank[rootX] < rank[rootY]) {
			parent[rootX] = rootY;
		} else {
			parent[rootY] = rootX;
			rank[rootX]++;
		}
	}
}

public class Main {
	static final int mod = 998244353;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		UF uf = new UF(N);

		ArrayList<Integer> nums[] = new ArrayList[2];
		for (int i = 0; i < 2; i++)
			nums[i] = new ArrayList<>();
		long ans = 1, cnt = 0;
		int nm = 1;
		int n = 2;
		while ((nm = n * n++) < 2 * N)
			nums[nm % 2].add(nm);

		outer: for (int diff = 1, a, b; diff < N; diff++) {
			for (int i = 0, half; i < nums[diff % 2].size(); i++) {
				half = nums[diff % 2].get(i) >> 1;
				a = half - diff / 2;
				b = half + diff / 2 + (diff % 2 == 1 ? 1 : 0);
				if (a <= 0 || b > N)
					continue;
				if (uf.find(a) != uf.find(b)) {
					uf.union(a, b);
					ans = (ans * diff) % mod;
					cnt++;
				}
				if (cnt == N - 1)
					break outer;
			}
		}
		System.out.println(cnt == N - 1 ? ans : -1);
	}
}
