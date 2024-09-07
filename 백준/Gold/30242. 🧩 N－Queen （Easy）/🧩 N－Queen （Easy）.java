import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    static int N;
    static int[] piece;
    static boolean[][] v;

    static int[] ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        piece = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        v = new boolean[3][];
        for (int i = 0; i < 3; i++) {
            v[0] = new boolean[N + 1];
            v[1] = new boolean[N << 1 | 1];
            v[2] = new boolean[N << 1 | 1];
        }

        for (int i = 0; i < N; i++) {
            if (piece[i] == 0)
                continue;
            v[0][piece[i]] = true;
            v[1][N + i - piece[i]] = true;
            v[2][piece[i] + i] = true;
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
                if (v[0][i] || v[1][N + index - i] || v[2][index + i])
                    continue;

                v[0][i] = true;
                v[1][N + index - i] = true;
                v[2][index + i] = true;
                choice[index] = i;

                if (dfs(index + 1, choice))
                    return true;

                v[0][i] = false;
                v[1][N + index - i] = false;
                v[2][index + i] = false;
                choice[index] = 0;
            }
        }

        return false;
    }
}
