import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] ar = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			ar[i] = Integer.parseInt(st.nextToken());
		}
		int[] ans = rec(ar);
		System.out.println(ans[0] + " " + ans[1]);
	}

	static int[] rec(int[] ar) {
		int[] ans = new int[2];
		int min = Integer.MAX_VALUE;
		int left = 0;
		int right = ar.length - 1;
		while (left < right) {
			int val = ar[left] + ar[right];
			if (Math.abs(val) < min) {
				min = Math.abs(val);
				ans[0] = ar[left];
				ans[1] = ar[right];
			}
			if (val < 0) {
				left++;
			} else {
				right--;
			}
		}
		return ans;
	}
}