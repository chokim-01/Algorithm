import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    static int N, Q;
    static ArrayList<Node> list;
    static Queue<Node> query;

    static class Kruskal {
        int[] parent;

        Kruskal() {
            this.parent = IntStream.rangeClosed(0, N).toArray();
        }

        void union(int a, int b) {
            a = find(a);
            b = find(b);
            if (a == b)
                return;
            if (a < b)
                this.parent[b] = a;
            else
                this.parent[a] = b;

        }

        int find(int a) {
            if (a == this.parent[a])
                return a;
            return this.parent[a] = find(this.parent[a]);
        }
    }

    static class Node implements Comparable<Node> {
        int num, x, x2;

        Node(int x, int x2) {
            this.x = x;
            this.x2 = x2;
        }

        Node(int num, int x, int x2) {
            this.num = num;
            this.x = x;
            this.x2 = x2;
        }

        @Override
        public int compareTo(Node o) {
            return this.x == o.x ? this.x2 - o.x2 : this.x - o.x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input(br);

        StringBuilder result = solve();
        System.out.println(result);
    }

    static StringBuilder solve() {
        StringBuilder sb = new StringBuilder();

        Kruskal kruskal = new Kruskal();
        Collections.sort(list);

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++)
                if (list.get(i).x2 >= list.get(j).x)
                    kruskal.union(list.get(i).num, list.get(j).num);
                else
                    break;
        }
        while (!query.isEmpty()) {
            Node q = query.poll();
            if (kruskal.find(q.x) == kruskal.find(q.x2))
                sb.append(1);
            else
                sb.append(0);
            sb.append("\n");
        }


        return sb;
    }


    static void input(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        query = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            int[] inp = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            list.add(new Node(i, inp[0], inp[1]));
        }
        for (int i = 0; i < Q; i++) {
            int[] inp = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            query.add(new Node(inp[0], inp[1]));
        }
    }
}
