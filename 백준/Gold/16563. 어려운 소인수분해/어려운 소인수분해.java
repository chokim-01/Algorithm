import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static final int MAX = 5000000;

    static int[] prime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        era();

        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        while (N-- > 0) {
            int num = Integer.parseInt(st.nextToken());
            while (num != 1) {
                sb.append(prime[num]).append(" ");
                num /= prime[num];
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static void era() {
        prime = new int[MAX + 1];
        for (int i = 2; i <= MAX; i++)
            prime[i] = i;

        for (int p = 2; (long) p * p <= MAX; p++) {
            if (prime[p] != p)
                continue;
            for (int i = p * p; i <= MAX; i += p)
                prime[i] = Math.min(prime[i], p);
        }
    }
}
