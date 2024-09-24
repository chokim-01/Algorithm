import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    static final int MAX = 2000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        int[] nums = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] exist = new int[MAX];

        List<Integer> primes = era();

        int ans = 0;

        for (int p : primes) {
            for (int n : nums)
                ans = Math.max(ans, ++exist[n % p]);
            for (int n : nums)
                exist[n % p]--;
        }
        System.out.println(ans);
    }

    static List<Integer> era() {
        boolean[] p = new boolean[MAX];
        Arrays.fill(p, true);
        p[0] = p[1] = false;
        for (int i = 2; i <= Math.sqrt(MAX); i++)
            if (p[i])
                for (int j = i * i; j < MAX; j += i)
                    p[j] = false;

        ArrayList<Integer> ret = new ArrayList<>();
        for (int i = 2; i < MAX; i++)
            if (p[i])
                ret.add(i);

        return ret;
    }
}
