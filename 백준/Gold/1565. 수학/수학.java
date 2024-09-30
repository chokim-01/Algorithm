import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.stream.Stream;

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
        br.readLine();
        int[] a = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] b = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        long c = a[0];
        for (int num : a)
            c = lcm(c, num);

        long d = b[0];
        for (int num : b)
            d = gcd(d, num);

        HashSet<Long> set = new HashSet<>();
        for (long i = 1; i * i <= d; i++) {
            if (d % i != 0)
                continue;
            set.add(i);
            set.add(d / i);
        }

        int cnt = 0;
        for (long num : set)
            if (num % c == 0)
                cnt++;
        System.out.println(cnt);

    }
}