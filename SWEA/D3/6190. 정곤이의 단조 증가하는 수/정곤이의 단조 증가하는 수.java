import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			int max = -1;
			ArrayList<Integer> arr = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			while (st.hasMoreTokens())
				arr.add(Integer.parseInt(st.nextToken()));

			Collections.sort(arr, Collections.reverseOrder());

			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					int val = arr.get(i) * arr.get(j);
					if (max > val)
						break;
					if (Chk(val)) {
						max = Math.max(val, max);
						break;
					}
				}
			}
			sb.append("#").append(t).append(" ").append(max).append("\n");
		}
		System.out.println(sb);
	}

	static boolean Chk(int x) {
		int before = -1;
		while(x != 0){
			before = x % 10;
			x /= 10;
			if (x % 10 > before)
				return false;
		}
		return true;
	}
}