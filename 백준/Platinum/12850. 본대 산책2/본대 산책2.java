import java.util.*;

public class Main {

    static final int MOD = 1000000007;
    static int[][] dist = {
            // 정보, 전산, 미래, 신양, 한경직, 진리, 학생, 형남
            // 정보
            {0, 1, 1, 0, 0, 0, 0, 0},
            // 전산
            {1, 0, 1, 1, 0, 0, 0, 0},
            // 미래
            {1, 1, 0, 1, 1, 0, 0, 0},
            // 신양
            {0, 1, 1, 0, 1, 1, 0, 0},
            // 한경직
            {0, 0, 1, 1, 0, 1, 0, 1},
            // 진리
            {0, 0, 0, 1, 1, 0, 1, 0},
            // 학생
            {0, 0, 0, 0, 0, 1, 0, 1},
            // 형남
            {0, 0, 0, 0, 1, 0, 1, 0}};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int D = sc.nextInt();

        int[][] ans = new int[8][8];
        for (int i = 0; i < 8; i++)
            ans[i][i] = 1;

        while (D > 0) {
            if (D % 2 == 1)
                ans = mul(ans, dist);
            dist = mul(dist, dist);
            D >>= 1;
        }
        System.out.println(ans[0][0]);

    }

    static int[][] mul(int[][] a, int[][] b) {
        int[][] ret = new int[8][8];
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                for (int k = 0; k < 8; k++)
                    ret[i][j] = ((int) ((a[i][k] * (long) b[k][j]) % MOD) + ret[i][j]) % MOD;
        return ret;
    }
}
