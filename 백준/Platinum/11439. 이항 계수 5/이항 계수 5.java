import java.util.*;

public class Main {
    static final int MAX = 4000001;
    static int[] prime;
    static int[][] map;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int M = sc.nextInt();

        long result = solve(N, K, M);
        System.out.println(result);
    }

    public static long solve(int n, int k, int m) {
        if (n == k)
            return 1;
        getPrime();

        map = new int[2][MAX + 1];

        calc(0, n - k + 1, n);
        calc(1, 1, k);

        for (int i = 2; i <= MAX; i++)
            if (map[1][i] != 0)
                map[0][i] -= map[1][i];

        long ret = 1;
        for (int i = 2; i <= MAX; i++)
            ret = (long) ((ret * Math.pow(i, map[0][i])) % m);

        return ret;
    }

    static void getPrime() {
        int N = 4000000;

        prime = new int[N + 1];
        for (int i = 2; i <= N; i++)
            prime[i] = i;

        for (int p = 2; p <= N; p++)
            if (prime[p] == p)
                for (long multiple = (long) p * p; multiple <= N; multiple += p)
                    prime[(int) multiple] = p;
    }

    static void calc(int idx, int s, int N) {
        for (int num = s; num <= N; num++) {
            int tmp = num;
            while (tmp != 1) {
                map[idx][prime[tmp]]++;
                tmp /= prime[tmp];
            }
        }
    }
}
