import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] list = new int[N];
		int index = 0;
		while (st.hasMoreTokens()) {
			list[index++] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(list);

		int ans = Integer.MAX_VALUE;
		int ans1 = 0;
		int ans2 = 0;
		int left = 0;
		int right = N - 1;
		
		while (left < right) {
			int sum = list[left] + list[right];
			if(ans > Math.abs(sum)) {
				ans1 = list[left];
				ans2 = list[right];
				ans = Math.abs(sum);
			}
			if(sum < 0)
				left++;
			else
				right--;
		}

		if (ans1 > ans2)
			System.out.println(ans2 + " " + ans1);
		else
			System.out.println(ans1 + " " + ans2);
	}
}
