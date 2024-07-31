import java.io.IOException;
import java.util.Scanner;

class Main {
    static final int MAX = (int) 10e7;
    static boolean[] prime;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        long left = sc.nextLong();
        long right = sc.nextLong();

        System.out.println(solve(left, right));
    }

    static int solve(long l, long r) {
        int ret = 0;

        era();
        for (int i = 2; i <= Math.sqrt(r); i++) {
            if (!prime[i]) {
                long v = i;
                while (v <= r / i) {
                    if (v * i >= l)
                        ret++;
                    v *= i;
                }
            }
        }
        return ret;
    }

    static void era() {
        prime = new boolean[MAX + 1];
        for (int i = 2; i * i <= MAX; i++)
            if (!prime[i])
                for (int j = i * i; j <= MAX; j += i)
                    prime[j] = true;

        prime[0] = prime[1] = true;
    }
}