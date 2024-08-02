import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int[] nums = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int ans = findClosed(nums, n, k);

            sb.append(ans).append("\n");
        }
        
        System.out.println(sb);
    }

    static int findClosed(int[] nums, int n, int k) {
        int l = 0;
        int r = nums.length - 1;
        int closed = Integer.MAX_VALUE;

        Arrays.sort(nums);

        int ret = 0;

        while (l < r) {
            int sum = nums[l] + nums[r];
            int nClosed = Math.abs(sum - k);

            if (nClosed < closed) {
                ret = 1;
                closed = nClosed;
            } else if (nClosed == closed)
                ret++;

            if (sum <= k)
                l++;
            if (sum >= k)
                r--;
        }

        return ret;
    }
}
