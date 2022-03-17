import java.io.IOException;
import java.util.Scanner;

public class Main {
	static int[] dp;
	static int ans;
	static int N;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		// 양수 : 11 이하
		dp = new int[11];
		// 1, 2, 3의 합으로 나타내는 방법
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;

		while (T-- > 0) {
			N = sc.nextInt();
			ans = 0;
			
			getAns(0);
			System.out.println(ans);
		}
		

	}
	static void getAns(int x) {
		if(x == N) {
			ans +=1;
			return;
		} if(x > N)
			return;
		for(int i = 1;i<=3;i++)
			getAns(x+i);
			
	}
	// true true 02 03 04
	// 10 true 12 13 14
	// 20 21 true 23 24
	// 30 31 32 true 34
	// 40 41 42 43 true
	// x 부터 y까지

}