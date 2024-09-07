import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    static int N;
    static int[] piece;
    static HashSet[] v;

    static int[] ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        piece = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        v = new HashSet[3];
        for (int i = 0; i < 3; i++)
            v[i] = new HashSet<>();

        for (int i = 0; i < N; i++) {
            if (piece[i] == 0)
                continue;
            v[0].add(piece[i]);
            v[1].add(N + i - piece[i]);
            v[2].add(piece[i] + i);
        }

        dfs(0, new int[N]);

        StringBuilder sb = new StringBuilder();
        if (ans == null)
            sb.append(-1);
        else
            for (int a : ans)
                sb.append(a).append(" ");
        System.out.println(sb);
    }

    // j, N+i-j, i+j
    static boolean dfs(int index, int[] choice) {
        if (index == N) {
            ans = choice.clone();
            return true;
        }

        if (piece[index] != 0) {
            choice[index] = piece[index];
            if (dfs(index + 1, choice))
                return true;
            choice[index] = 0;
        } else {
            for (int i = 1; i <= N; i++) {
                if (v[0].contains(i) || v[1].contains(N + index - i) || v[2].contains(index + i))
                    continue;

                v[0].add(i);
                v[1].add(N + index - i);
                v[2].add(index + i);
                choice[index] = i;

                if (dfs(index + 1, choice))
                    return true;

                v[0].remove(i);
                v[1].remove(N + index - i);
                v[2].remove(index + i);
                choice[index] = 0;
            }
        }

        return false;
    }


}
