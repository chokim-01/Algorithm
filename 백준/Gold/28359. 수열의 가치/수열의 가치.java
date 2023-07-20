import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	static int N;
	static long K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		TreeMap<Integer, Integer> map = new TreeMap<>();
		int sum = 0;
		int[] arr = new int[N];
		for (int i = 0, n; i < N; i++) {
			n = Integer.parseInt(st.nextToken());
			sum += arr[i] = n;
			if (!map.containsKey(n))
				map.put(n, 0);
			map.put(n, map.get(n) + 1);
		}
		int max = 0;
		for (Entry<Integer, Integer> e : map.entrySet()) {
			int c = e.getKey() * e.getValue();
			max = max < c ? c : max;
		}
		Arrays.sort(arr);

		StringBuilder sb = new StringBuilder();
		sb.append(sum + max).append("\n");
		for (int a : arr)
			sb.append(a).append(" ");
		System.out.println(sb);
	}
}