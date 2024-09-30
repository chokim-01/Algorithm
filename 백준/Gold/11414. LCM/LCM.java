import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    public static long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        if (A > B) {
            long temp = A;
            A = B;
            B = temp;
        }

        long C = B - A;
        if (A == B) {
            System.out.println(1);
            return;
        }

        long save = Long.MAX_VALUE;
        long ans = 0;

        for (long i = 1, d, N, mul; i * i <= C; i++) {

            if (C % i == 0) {
                d = i;
                N = (d - B % d);
                mul = lcm(A + N, B + N);
                if (mul < save || (mul == save && N < ans)) {
                    save = mul;
                    ans = N;
                }

                d = C / i;
                N = (d - B % d);
                mul = lcm(A + N, B + N);
                if (mul < save || (mul == save && N < ans)) {
                    save = mul;
                    ans = N;
                }
            }
        }

        System.out.println(ans);
    }
}