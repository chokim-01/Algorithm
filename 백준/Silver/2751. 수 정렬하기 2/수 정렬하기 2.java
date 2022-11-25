import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(br.readLine());
			min = Math.min(min, nums[i]);
			max = Math.max(max, nums[i]);
		}
		int size = max - min;
		// 카운트정렬
		int[] counted = new int[size + 1];
		for (int c : nums)
			counted[c - min]++;

		for (int i = 1; i <= size; i++)
			counted[i] += counted[i - 1];

		int[] ans = new int[N];

		for (int num : nums) {
			int x = counted[num-min];
			ans[x - 1] = num;
			counted[num-min]--;
		}

		for (int i = 0; i < N; i++)
			bw.write(ans[i] + "\n");
		bw.flush();
		bw.close();
	}
}