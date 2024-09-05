import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    static int N, M;
    static Boolean[] vision;
    static ArrayList<Node>[] link;

    static class Node implements Comparable<Node> {
        int x;
        long cost;

        Node(int x, long c) {
            this.x = x;
            this.cost = c;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.cost, o.cost);
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input(br);

        System.out.println(go());
    }

    static long go() {
        long[] v = new long[N];
        Arrays.fill(v, Long.MAX_VALUE);
        v[0] = 0;

        Queue<Node> q = new PriorityQueue<>();
        q.add(new Node(0, 0));

        while (!q.isEmpty()) {
            Node now = q.poll();

            if(now.cost > v[now.x])
                continue;

            for (Node next : link[now.x]) {
                if ((next.x != N - 1 && vision[next.x]) || now.cost + next.cost >= v[next.x])
                    continue;
                q.add(new Node(next.x, v[next.x] = now.cost + next.cost));
            }
        }

        return v[N - 1] == Long.MAX_VALUE ? -1 : v[N - 1];
    }

    static void input(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        vision = Stream.of(br.readLine().split(" ")).map(x -> Integer.parseInt(x) == 1).toArray(Boolean[]::new);

        link = new ArrayList[N];
        for (int i = 0; i < N; i++)
            link[i] = new ArrayList<>();

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if ((a != N - 1 && vision[a]) || b != N - 1 && vision[b])
                continue;

            link[a].add(new Node(b, c));
            link[b].add(new Node(a, c));
        }
    }
}
