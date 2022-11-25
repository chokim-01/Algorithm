import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	static HashMap<String, Integer> map;
	static int[] parent;
	static int[] count;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			int m = Integer.parseInt(br.readLine());
			int cnt = 1;
			count = new int[200001];
			parent = new int[200001];
			Arrays.fill(count, 1);
			for (int i = 1; i <= 200000; i++)
				parent[i] = i;

			map = new HashMap<>();
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				String a = st.nextToken();
				if (!map.containsKey(a))
					map.put(a, cnt++);

				String b = st.nextToken();
				if (!map.containsKey(b))
					map.put(b, cnt++);
				union(map.get(a), map.get(b));
				bw.write(count[find(map.get(b))]+"\n");
			}
		}
		bw.flush();
		bw.close();
	}

	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if (x == y)
			return;
		if (x < y) {
			parent[y] = x;
			count[x] += count[y];
			count[y] = 0;
		} else {
			parent[x] = y;
			count[y] += count[x];
			count[x] = 0;
		}
	}

	static int find(int x) {
		if (x == parent[x])
			return x;
		return parent[x] = find(parent[x]);
	}
}