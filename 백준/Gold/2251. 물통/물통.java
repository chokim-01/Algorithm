import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

public class Main {
	static boolean[][][] v;
	static int[] beakers;
	static ArrayList<Integer> lst;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		beakers = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		lst = new ArrayList<>();
		v = new boolean[201][201][201];
		v[beakers[0]][beakers[1]][beakers[2]] = true;
		dfs(0, 0, beakers[2]);

		Collections.sort(lst);
		for (int a : lst)
			ans.append(a).append(" ");
		System.out.println(ans);
	}

	static void dfs(int a, int b, int c) {
		v[a][b][c] = true;
		if (a == 0)
			lst.add(c);
		int na = 0;
		int nb = 0;
		int nc = 0;
		if (c != 0) {
			na = a;
			nb = b;
			nc = c;
			// c to a
			if (c + a > beakers[0]) {
				nc = a + c - beakers[0];
				na = beakers[0];

				if (!v[na][nb][nc])
					dfs(na, nb, nc);
			} else {
				nc = 0;
				na = a + c;
				if (!v[na][nb][nc])
					dfs(na, nb, nc);
			}
			na = a;
			nb = b;
			nc = c;
			// c to b
			if (c + b > beakers[1]) {
				nc = b + c - beakers[1];
				nb = beakers[1];
				if (!v[na][nb][nc])
					dfs(na, nb, nc);
			} else {
				nc = 0;
				nb = b + c;
				if (!v[na][nb][nc])
					dfs(na, nb, nc);
			}
		}
		if (a != 0) {
			na = a;
			nb = b;
			nc = c;
			// a to b
			if (a + b > beakers[1]) {
				na = a + b - beakers[1];
				nb = beakers[1];
				if (!v[na][nb][nc])
					dfs(na, nb, nc);
			} else {
				na = 0;
				nb = a + b;
				if (!v[na][nb][nc])
					dfs(na, nb, nc);
			}
			na = a;
			nb = b;
			nc = c;
			// a to c
			if (a + c > beakers[2]) {
				na = a + c - beakers[2];
				nc = beakers[2];
				if (!v[na][nb][nc])
					dfs(na, nb, nc);
			} else {
				na = 0;
				nc = a + c;
				if (!v[na][nb][nc])
					dfs(na, nb, nc);
			}
		}
		if (b != 0) {
			na = a;
			nb = b;
			nc = c;
			// b to a
			if (b + a > beakers[0]) {
				nb = a + b - beakers[0];
				na = beakers[0];

				if (!v[na][nb][nc])
					dfs(na, nb, nc);
			} else {
				nb = 0;
				na = a + b;
				if (!v[na][nb][nc])
					dfs(na, nb, nc);
			}
			na = a;
			nb = b;
			nc = c;
			// b to c
			if (b + c > beakers[2]) {
				nb = b + c - beakers[2];
				nc = beakers[2];

				if (!v[na][nb][nc])
					dfs(na, nb, nc);
			} else {
				nb = 0;
				nc = b + c;
				if (!v[na][nb][nc])
					dfs(na, nb, nc);
			}
		}

	}
}