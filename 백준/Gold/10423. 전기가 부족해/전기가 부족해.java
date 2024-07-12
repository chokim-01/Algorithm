import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

class Main {
    static int N, M, K;
    static boolean[] plant;

    static ArrayList<Node> link;

    static class Kruskal {
        int[] parent;

        Kruskal() {
            this.parent = IntStream.rangeClosed(0, N).map(x -> plant[x] ? 0 : x).toArray();
        }

        void union(int x, int y) {
            x = find(x);
            y = find(y);
            if (x < y)
                parent[y] = x;
            else
                parent[x] = y;
        }

        int find(int x) {
            if (x == parent[x])
                return x;
            return parent[x] = find(parent[x]);
        }
    }

    static class Node implements Comparable<Node> {
        int x, y, cost;

        Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input(br);

        int ans = solve();
        System.out.println(ans);

    }

    static int solve() {
        int ret = 0;
        Kruskal kruskal = new Kruskal();

        for (Node n : link) {
            if (kruskal.find(n.x) == kruskal.find(n.y))
                continue;
            ret += n.cost;
            kruskal.union(n.x, n.y);
        }

        return ret;
    }

    static void input(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        plant = new boolean[N + 1];
        for (int i = 0; i < K; i++)
            plant[Integer.parseInt(st.nextToken())] = true;

        link = new ArrayList<>();

        for (int i = 0, a, b, c; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            link.add(new Node(a, b, c));
        }
        Collections.sort(link);
    }
}
