import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Main {
    static int N, K;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        nums = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] ans = sort();
        System.out.println(ans[N - K]);
    }

    static int[] sort() {
        int[] tmp = new int[10001];
        int[] ret = new int[N];

        IntStream.range(0, N).forEach(i -> tmp[nums[i]]++);

        IntStream.range(1, tmp.length).forEach(i -> tmp[i] += tmp[i - 1]);

        IntStream.range(0, nums.length).map(i -> N - 1 - i).mapToObj(i -> nums[i]).forEach(v -> ret[--tmp[v]] = v);

        return ret;
    }
}
//10
// 