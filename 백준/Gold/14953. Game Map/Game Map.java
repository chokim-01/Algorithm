import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static ArrayList<Integer>[] link;
    static int[] neighborCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input(br);

        int[] dp = new int[N];
        Arrays.fill(dp, 1);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(x -> neighborCount[x]));

        for (int i = 0; i < N; i++)
            pq.add(i);

        int ans = 1;
        while (!pq.isEmpty()) {
            int now = pq.poll();
            for (int next : link[now]) {
                if (neighborCount[next] <= neighborCount[now])
                    continue;
                ans = Math.max(ans, dp[next] = Math.max(dp[next], dp[now] + 1));
            }
        }

        System.out.println(ans);
    }

    static void input(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        link = new ArrayList[N];
        for (int i = 0; i < N; i++)
            link[i] = new ArrayList<>();

        neighborCount = new int[N];

        for (int i = 0, a, b; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            link[a].add(b);
            link[b].add(a);
            neighborCount[a]++;
            neighborCount[b]++;
        }
    }
}
