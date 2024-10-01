import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long[] gcd(long a, long b) {
        if (a == 0)
            return new long[]{0, 1};

        long[] r = gcd(b % a, a);
        long x = r[1] - (b / a) * r[0];
        long y = r[0];

        return new long[]{x, y};
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            long b = Long.parseLong(st.nextToken());
            long a = Long.parseLong(st.nextToken());

            if (b == 1) {
                if (a == 1) {
                    sb.append(2).append("\n");
                    continue;
                }
                sb.append(1).append("\n");
                continue;
            }
            if (a == 1) {
                if (b >= (long)10e8)
                    sb.append("IMPOSSIBLE");
                else
                    sb.append(b + 1);
                sb.append("\n");
                continue;
            }

            long[] r = gcd(a, b);
            long x = r[0];
            long y = r[1];

            if (x < 0) {
                long k = (-x + b - 1) / b;
                x += k * b;
                y -= k * a;
            }

            boolean gcdValue = a * x + b * y == 1;
            if (gcdValue) {
                sb.append(x).append("\n");
            } else {
                sb.append("IMPOSSIBLE\n");
            }
        }

        System.out.print(sb);
    }
}
