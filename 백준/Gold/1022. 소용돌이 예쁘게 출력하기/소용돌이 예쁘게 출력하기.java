import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.stream.Stream;

public class Main {

    static int[] start_v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] xy = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Queue<Integer> a_que = new ArrayDeque<>();

        start_v = new int[5002];
        start_v[0] = 1;
        for (int i = 1; i < start_v.length; i++) {
            int v = (i << 1) - 1;
            start_v[i] = v * v + 1;
        }

        int max = 0;
        for (int i = xy[0]; i <= xy[2]; i++) {
            for (int j = xy[1]; j <= xy[3]; j++) {
                int p_max = Math.max(Math.abs(i), Math.abs(j));
                int c = calc(i, j, p_max);
                a_que.add(c);
                max = max < c ? c : max;
            }
        }

        String format = "%" + String.valueOf(max).length() + "d ";

        StringBuilder ans = new StringBuilder();
        for (int i = xy[0]; i <= xy[2]; i++) {
            for (int j = xy[1]; j <= xy[3]; j++)
                ans.append(String.format(format, a_que.poll()));
            ans.append("\n");
        }
        System.out.println(ans);
    }

    static int calc(int x, int y, int m) {
        if (m == 0)
            return 1;
        if (y == m && x == m)
            return start_v[m + 1] - 1;

        int ret = start_v[m] - 1;
        if (y == m)
            return ret + (m - x);
        if (x == -m)
            return ret + (m - y) + 2 * m;
        if (y == -m)
            return ret + (m + x) + 4 * m;
        return ret + (m + y) + 6 * m;
    }
}