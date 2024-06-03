import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {

    static int N, M, K;
    static boolean[] infected;
    static int[][] send;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        infected = new boolean[N + 1];
        for (String s : br.readLine().split(" "))
            infected[Integer.parseInt(s)] = true;

        HashSet<String> result = new HashSet<>();
        result.add(Arrays.toString(infected));

        int[][] send = new int[M][];
        for (int i = 0; i < M; i++)
            send[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(send, Comparator.comparingInt(x -> x[0]));

        outer:
        for (int i = 1; i <= N; i++) {
            if (!infected[i])
                continue;
            boolean[] now = new boolean[N + 1];
            now[i] = true;

            for (int[] se : send) {
                if (!now[se[1]])
                    continue;
                if (!infected[se[2]])
                    continue outer;
                now[se[2]] = true;
            }
            if (!result.contains(Arrays.toString(now)))
                continue;
            System.out.println(i);
            break;
        }
    }
}