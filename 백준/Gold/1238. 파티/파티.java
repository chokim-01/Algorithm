import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, X;
    static int MAX = 100000000;
    static ArrayList<Node>[] link;
    static int[][] distance;

    static class Node implements Comparable<Node> {
        int x, time;

        Node(int n, int t) {
            this.x = n;
            this.time = t;
        }

        @Override
        public int compareTo(Node o) {
            return this.time - o.time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input(br);

        calc();

        int max = 0;
        for (int i = 1; i <= N; i++) {
            if (X == N)
                continue;
            max = Math.max(max, distance[i][X] + distance[X][i]);
        }
        System.out.println(max);
    }

    static void calc() {
        for (int start = 1; start <= N; start++) {
            PriorityQueue<Node> pq = new PriorityQueue<>();
            distance[start][start] = 0;
            pq.add(new Node(start, 0));
            while (!pq.isEmpty()) {
                Node now = pq.poll();

                for (Node next : link[now.x]) {
                    int nextTime = now.time + next.time;
                    if (distance[start][next.x] <= nextTime)
                        continue;
                    distance[start][next.x] = nextTime;
                    pq.add(new Node(next.x, nextTime));
                }
            }
        }
    }

    static void input(BufferedReader br) throws IOException {
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        distance = new int[N + 1][N + 1];
        for (int i = 0; i <= N; i++)
            Arrays.fill(distance[i], MAX);

        link = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++)
            link[i] = new ArrayList<>();

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            link[a].add(new Node(b, c));
        }

    }
}
