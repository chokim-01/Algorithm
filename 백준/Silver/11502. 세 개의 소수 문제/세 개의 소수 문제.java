import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
    static final int MAX = 1000;
    static boolean[] prime;
    static ArrayList<Integer> primes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        era();

        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        while (N-- > 0) {
            int num = Integer.parseInt(br.readLine());
            int[] ret = calc(num);
            if (ret == null)
                sb.append("0");
            else
                sb.append(ret[0]).append(" ").append(ret[1]).append(" ").append(ret[2]);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static int[] calc(int num) {
        for (int i = 0, a; i < primes.size(); i++) {
            a = primes.get(i);
            for (int j = i, b; j < primes.size(); j++) {
                b = primes.get(j);
                for (int k = j, c; k < primes.size(); k++) {
                    c = primes.get(k);
                    if (a + b + c > num)
                        break;
                    if (a + b + c == num)
                        return new int[]{a, b, c};
                }
            }
        }
        return null;
    }

    public static void era() {
        prime = new boolean[MAX + 1];
        for (int i = 2; i <= MAX; i++)
            prime[i] = true;

        for (int p = 2; (long) p * p <= MAX; p++) {
            if (!prime[p])
                continue;
            for (int i = p * p; i <= MAX; i += p)
                prime[i] = false;
        }

        primes = new ArrayList<>();
        for (int i = 2; i < MAX; i++)
            if (prime[i])
                primes.add(i);
    }
}
