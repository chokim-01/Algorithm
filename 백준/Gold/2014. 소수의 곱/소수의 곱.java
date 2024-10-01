import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        long[] nums = new long[N];
        PriorityQueue<Long> pq = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            pq.add(nums[i] = Integer.parseInt(st.nextToken()));

        while (K-- > 1) {
            for (long num : nums) {
                if (pq.peek() * num >= Integer.MAX_VALUE)
                    break;
                pq.add(pq.peek() * num);
                if (pq.peek() % num == 0)
                    break;
            }
            pq.poll();
        }

        System.out.println(pq.poll());
    }
}
