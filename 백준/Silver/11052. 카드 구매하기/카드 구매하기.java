import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int[] card;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		// 카드 등급 : 8가지
		// 가격이 비싸면 높은 등급의 카드가 많이 들어있음.
		// 최대한 많이 지불해서 카드 N개를 구매
		// 배열에서 카드 N개를 갖기 위해 지불해야 하는 최대 금액
		card = new int[N + 1];
		dp = new int[N + 1];
		// dp : 구매한 갯수. 총 N장을 구매해야함.

		for (int i = 1; i <= N; i++) 
			card[i] = sc.nextInt();
		
		for (int i = 1; i <= N; i++) 
			for (int j = 1; j <= i; j++) 
				dp[i] = Math.max(card[j] + dp[i-j],dp[i]);
			
		System.out.println(dp[N]);

	}
}