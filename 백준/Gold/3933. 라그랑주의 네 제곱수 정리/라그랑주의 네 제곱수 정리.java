import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] dp = new int[33333][6];

        for (int i = 1, j; i < 182; i++) {
            dp[j = i * i][1] = 1;
            for (; j < 33333; j++) {
                dp[j][2] += dp[j - i * i][1];
                dp[j][3] += dp[j - i * i][2];
                dp[j][4] += dp[j - i * i][3];
            }
        }
        for (int i = 0; i < 33333; i++)
            for (int j = 0; j < 5; j++)
                dp[i][5] += dp[i][j];

        StringBuilder ans = new StringBuilder();
        int num = 0;
        while ((num = Integer.parseInt(br.readLine())) != 0)
            ans.append(dp[num][5]).append("\n");

        System.out.println(ans);
    }
}