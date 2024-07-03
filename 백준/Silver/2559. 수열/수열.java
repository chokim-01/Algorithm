import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Main {
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] nums = Stream.of(("0 " + br.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();

        IntStream.rangeClosed(1, N).forEach(i -> nums[i] += nums[i - 1]);

        System.out.println(
                IntStream.rangeClosed(K, N).map(i -> nums[i] - nums[i - K]).max().orElse(0)
        );
    }
}
//10
//