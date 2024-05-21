import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
    static int N, S;
    static int[] nums;
    static HashMap<Integer, Long> saveMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        nums = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        saveMap = new HashMap<>();
        saveMap.put(Integer.MIN_VALUE, 0L);

        dfs(0, 0);
        long ans = dfs2(N >> 1, 0) - (S == 0 ? 1 : 0);
        System.out.println(ans);

    }

    static void dfs(int index, int sum) {
        if (index == N >> 1) {
            saveMap.put(sum, saveMap.getOrDefault(sum, Long.MIN_VALUE) + 1);
            return;
        }
        dfs(index + 1, sum);
        dfs(index + 1, sum + nums[index]);
    }

    static long dfs2(int index, int sum) {
        if (index == N)
            return saveMap.getOrDefault(S - sum, Long.MIN_VALUE);
        return dfs2(index + 1, sum) + (long) dfs2(index + 1, sum + nums[index]);
    }
}