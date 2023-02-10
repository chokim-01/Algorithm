import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Scanner;

class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// -4000 ~ 4000
		int[] nums = new int[8001];
		// 산술평균, 중앙값, 최빈값, 범위
		int[] ans = new int[4];

		int N = Integer.parseInt(br.readLine());

		double sum = 0;
		int numCnt = 0;
		int max = -4001;
		int min = 4001;

		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			sum += num;
			max = Math.max(num, max);
			min = Math.min(num, min);
			
			nums[num + 4000]++;
			numCnt = Math.max(numCnt, nums[num+4000]);
		}
		ans[0] = (int) Math.round(sum / N);
		ans[3] = max - min;

		int count = 0;
		int flag = 0;
		for (int i = min+4000; i <=max+4000; i++) {
			if (nums[i] == 0)
				continue;
			if (count < (N + 1) / 2) {
				count+=nums[i];
				ans[1] = i - 4000;
			}
			
			if(numCnt == nums[i] && flag == 0) {
				ans[2] = i-4000;
				flag = 1;
			}
			else if(flag == 1 && numCnt == nums[i]) {
				ans[2] = i-4000;
				flag = -1;
			}	
		}
		for (int i = 0; i < 4; i++)
			System.out.println(ans[i]);
	}
}