import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			int max = -1;
			ArrayList<Integer> arr = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			while(st.hasMoreTokens())
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
			bw.write("#"+t+" "+max+"\n");
		}
		bw.flush();
		bw.close();
	}

	static boolean Chk(int x) {
		String s = String.valueOf(x);
		if (s.charAt(s.length() - 1) == '0')
			return false;
		for (int i = 1; i < s.length(); i++)
			if (s.charAt(i - 1) > s.charAt(i))
				return false;
		return true;
	}
}