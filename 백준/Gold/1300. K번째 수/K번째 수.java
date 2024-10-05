import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Integer.parseInt(br.readLine());
        long K = Integer.parseInt(br.readLine());

        long l = 1;
        long r = N * N;

        long ans = 0;
        while (l <= r) {
            long mid = (l + r) >> 1;
            if (countLessEqual(N, mid) < K)
                l = mid + 1;
            else
                r = (ans = mid) - 1;
        }

        System.out.println(ans);
    }

    private static long countLessEqual(long N, long x) {
        long ret = 0;

        for (int i = 1; i <= N; i++)
            ret += Math.min(N, x / i);

        return ret;
    }
}
