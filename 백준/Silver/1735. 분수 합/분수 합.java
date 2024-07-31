import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
    static int fraction[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input(br);

        int[] ans = solve();

        System.out.println(ans[0] + " " + ans[1]);
    }

    static int[] solve() {
        int numerator = fraction[0][0] * fraction[1][1] + fraction[0][1] * fraction[1][0];
        int denominator = fraction[0][1] * fraction[1][1];

        int gcd = gcd(numerator,denominator);
        numerator /= gcd;
        denominator /= gcd;
        return new int[]{numerator, denominator};
    }

    static int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);

    }

    static void input(BufferedReader br) throws IOException {
        fraction = new int[2][2];
        for (int i = 0; i < 2; i++)
            fraction[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

    }
}