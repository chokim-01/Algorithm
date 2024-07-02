import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

class Main {
    static int N, S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        long[] nums = Stream.of(("0 " + br.readLine()).split(" ")).mapToLong(Long::parseLong).toArray();

        for (int i = 1; i <= N; i++)
            nums[i] += nums[i - 1];

        int ans = N + 2;
        int left = 1;
        int right = 1;

        while (right <= N) {
            if (nums[right] - nums[left - 1] < S)
                right++;
            else
                ans = Math.min(ans, right - left++ + 1);
        }
        System.out.println(ans == N + 2 ? 0 : ans);
    }
}
//10
//