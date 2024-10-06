import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
    static int N;
    static long K;
    static int[] S, D, v;
    static ArrayList<Integer> cycle;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input(br);

        int[] ans = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            if (v[i] != 0)
                continue;
            cycle = new ArrayList<>();
            dfs(i, 0);

            int size = cycle.size();
            int[] tmp = new int[size];

            for (int j = 0; j < size; j++)
                tmp[j] = D[cycle.get(j)];
            for (int j = 0; j < size; j++)
                ans[tmp[(int)((j + K) % size)]] = S[tmp[j]];
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= N; i++)
            sb.append(ans[i]).append(" ");

        System.out.println(sb);
    }

    static void dfs(int n, int idx) {
        if (v[n] != 0)
            return;
        v[n] = idx + 1;

        cycle.add(n);
        dfs(D[n], idx);
    }

    static void input(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Long.parseLong(st.nextToken());

        v = new int[N + 1];

        S = Stream.of(("0 " + br.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();
        D = Stream.of(("0 " + br.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
