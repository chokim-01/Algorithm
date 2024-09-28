import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int T, N, M, K;
    static int[][] time;
    static ArrayList<int[]>[] link;

    static class Node implements Comparable<Node> {
        int now, val, time;

        public Node(int now, int val, int time) {
            // TODO Auto-generated constructor stub
            this.now = now;
            this.val = val;
            this.time = time;
        }

        @Override
        public int compareTo(Main.Node o) {
            // TODO Auto-generated method stub
            return this.time - o.time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        PriorityQueue<Node> q;
        while (T-- > 0) {
            input(br);
            init();

            q = new PriorityQueue<>();
            q.add(new Node(0, 0, 0));
            time[0][0] = 0;

            while (!q.isEmpty()) {
                Node n = q.poll();
                if (n.time > time[n.now][n.val])
                    continue;
                if (n.now == N)
                    break;

                for (int i = 0; i < link[n.now].size(); i++) {
                    int next = link[n.now].get(i)[0];
                    int nVal = n.val + link[n.now].get(i)[1];
                    int nTime = n.time + link[n.now].get(i)[2];
                    if (nVal > M)
                        continue;
                    if (time[next][nVal] <= nTime)
                        continue;
                    for (int j = nVal + 1; j <= M; j++)
                        if (time[next][j] > nTime)
                            time[next][j] = nTime;
                        else
                            break;
                    time[next][nVal] = nTime;
                    q.add(new Node(next, nVal, nTime));
                }
            }
            int min = Integer.MAX_VALUE;
            for (int i = 0; i <= 10000; i++)
                min = time[N - 1][i] < min ? time[N - 1][i] : min;

            answer.append(min == Integer.MAX_VALUE ? "Poor KCM" : min).append("\n");
        }
        System.out.println(answer);
    }

    static void input(BufferedReader br) throws IOException {
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        link = new ArrayList[N];
        for (int i = 0; i < N; i++)
            link[i] = new ArrayList<>();

        for (int i = 0, a, b, c, d; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken()) - 1;
            b = Integer.parseInt(st.nextToken()) - 1;
            c = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            link[a].add(new int[]{b, c, d});
        }
        for (int i = 0; i < N; i++)
            Collections.sort(link[i], Comparator.comparingInt((int[] x) -> x[1]).thenComparing(x -> x[2]));
    }


    static void init() {
        time = new int[N][10001];
        for (int i = 0; i < N; i++)
            Arrays.fill(time[i], Integer.MAX_VALUE);
    }
}
