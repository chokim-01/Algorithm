import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    static int N, RC;

    static int[][] map, ans;
    static HashSet<Integer> duplicate;
    static Queue<Integer> primes;

    static ArrayList<Integer>[] primeList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input(br);
        makePrime();
        setDuplicate();

        setMap();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < RC; i++) {
            for (int j = 0; j < RC; j++)
                sb.append(map[i][j]).append(" ");
            sb.append("\n");
        }
        System.out.println(sb);
    }
    static void setMap(){
        for (int i = 0; i < RC; i++) {
            for (int j = 0; j < RC; j++) {
                if (map[i][j] != 0)
                    continue;
                while (duplicate.contains(primes.peek()))
                    primes.poll();
                duplicate.add(map[i][j] = primes.poll());
            }
        }
    }

    static void makePrime() {
        int n = 1000000;
        boolean[] prime = new boolean[n + 1];

        for (int i = 0; i <= n; i++)
            prime[i] = i > 1;

        for (int i = 2; i * i <= n; i++)
            if (prime[i])
                for (int j = i * i; j <= n; j += i)
                    prime[j] = false;

        primes = new ArrayDeque<>();
        for (int i = 2; i <= n; i++)
            if (prime[i])
                primes.add(i);

        primeList = new ArrayList[1000001];
        for (int i = 0; i < primeList.length; i++)
            primeList[i] = new ArrayList<>();

        for (int p : primes) {
            for (int i = 1; i * p < 1000001; i++)
                primeList[i * p].add(p);
        }
    }

    static void setDuplicate() {
        for (int i = 0; i < RC; i++)
            for (int n : map[i])
                if (n != 0)
                    duplicate.addAll(primeList[n]);
    }

    static void input(BufferedReader br) throws IOException {
        N = Integer.parseInt(br.readLine());
        RC = N * N;
        map = new int[RC][RC];
        ans = new int[RC][RC];

        for (int i = 0; i < RC; i++)
            map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        duplicate = new HashSet<>();
    }
}
