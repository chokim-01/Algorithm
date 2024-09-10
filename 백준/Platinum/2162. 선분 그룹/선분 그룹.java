import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    static int N;

    static Node[][] line;

    static class Node implements Comparable<Node> {
        int x, y;

        Node() {
        }

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Node o) {
            return this.x < o.x ? -1 : (this.x == o.x ? this.y - o.y : 1);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    static class UF {
        int[] parent;
        int[] count;

        UF() {
            this.parent = IntStream.range(0, N).toArray();
            this.count = IntStream.range(0, N).map(x -> 1).toArray();
        }

        void union(int a, int b) {
            a = find(a);
            b = find(b);
            if (a == b)
                return;
            if (a < b) {
                parent[a] = b;
                count[b] += count[a];
            } else {
                parent[b] = a;
                count[a] += count[b];
            }
        }

        int find(int x) {
            if (x == parent[x])
                return x;
            return parent[x] = find(parent[x]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input(br);

        UF uf = new UF();

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int ccw1 = ccw(line[i][0], line[i][1], line[j][0]) * ccw(line[i][0], line[i][1], line[j][1]);
                int ccw2 = ccw(line[j][0], line[j][1], line[i][0]) * ccw(line[j][0], line[j][1], line[i][1]);

                if (ccw1 == 0 && ccw2 == 0) {
                    compare(i, line[i][0], line[i][1]);
                    compare(j, line[j][0], line[j][1]);
                    // 1 2 3 4
                    // 1 3 2 4
                    if (line[j][0].compareTo(line[i][1]) <= 0 && line[i][0].compareTo(line[j][1]) <= 0)
                        uf.union(i, j);

                } else if (ccw1 <= 0 && ccw2 <= 0)
                    uf.union(i, j);
            }
        }

        HashSet<Integer> set = new HashSet<>();
        for (int p : uf.parent)
            set.add(uf.find(p));
        int count = 0;
        for (int c : uf.count)
            count = Math.max(c, count);

        System.out.println(set.size());
        System.out.println(count);
    }

    static void compare(int index, Node a, Node b) {
        if (a.compareTo(b) > 0) {
            line[index][0] = new Node(b.x, b.y);
            line[index][1] = new Node(a.x, a.y);
        }
    }

    static int ccw(Node a, Node b, Node c) {
        return Integer.signum((b.x - a.x) * (c.y - a.y) - (c.x - a.x) * (b.y - a.y));
    }

    static void input(BufferedReader br) throws IOException {
        N = Integer.parseInt(br.readLine());
        line = new Node[N][2];

        for (int i = 0; i < N; i++) {
            int[] ar = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int c = 0;
            for (int j = 0; j < 2; j++)
                line[i][j] = new Node(ar[c++], ar[c++]);
        }
    }
}
