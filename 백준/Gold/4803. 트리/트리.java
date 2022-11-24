import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int t = 1;
		while (true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			parent = new int[N + 1];
			for (int i = 1; i <= N; i++)
				parent[i] = i;

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				union(a, b);
			}

			HashSet<Integer> set = new HashSet<>();
		
			for (int i = 1; i <= N; i++) {
				int x = find(i);
				if(x > 0)
					set.add(x);
			}
			if (N == 0)
				break;
			bw.write("Case "+(t++)+": ");
			
			int ans = set.size();
			if(ans == 0)
				bw.write("No trees.");
			else if(ans == 1)
				bw.write("There is one tree.");
			else
				bw.write("A forest of "+ans+" trees.");
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}

	static int find(int x) {
		if (parent[x] == x)
			return x;
		return parent[x] = find(parent[x]);
	}

	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if(x <= y) {
			parent[y] = x;
			if(x == y)
				parent[x] = 0;
		}
		else
			parent[x] = y;
		return;
	}
}