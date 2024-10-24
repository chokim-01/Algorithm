import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

public class Main {
    static int N, Q;
    static int[] trees;
    static Segment seg;
    static Queue<Order> orders;

    static class Segment {
        Node[] tree;
        int size;

        Segment() {
            size = N;
            tree = new Node[size << 2];
        }

        Node combine(Node l, Node r) {
            if (l == null)
                return new Node(r.l, r.r, r.count);
            if (r == null)
                return new Node(l.l, l.r, l.count);

            Node ret = new Node(l.l, r.r, l.count + r.count);

            if (l.r == r.l)
                ret.count--;

            return ret;
        }

        Node init(int n, int s, int e) {
            if (s == e)
                return tree[n] = new Node(trees[s], trees[s], 1);

            int mid = (s + e) >> 1;
            return tree[n] = combine(init(n << 1, s, mid), init(n << 1 | 1, mid + 1, e));
        }

        Node update(int n, int s, int e, int idx, int v) {
            if (idx < s || idx > e)
                return tree[n];
            if (s == e) {
                tree[n].l = tree[n].r = v;
                return tree[n];
            }

            int mid = (s + e) >> 1;
            return tree[n] = combine(update(n << 1, s, mid, idx, v), update(n << 1 | 1, mid + 1, e, idx, v));

        }

        Node query(int n, int s, int e, int l, int r) {
            if (r < s || l > e)
                return null;
            if (l <= s && e <= r)
                return tree[n];

            int mid = (s + e) >> 1;
            return combine(query(n << 1, s, mid, l, r), query(n << 1 | 1, mid + 1, e, l, r));

        }
    }

    static class Node {
        int l, r, count;

        Node(int l, int r, int c) {
            this.l = l;
            this.r = r;
            this.count = c;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "l=" + l +
                    ", r=" + r +
                    ", count=" + count +
                    '}';
        }
    }

    static class Order {
        int t, a, b;

        Order(int t, int a, int b) {
            this.t = t;
            this.a = a;
            this.b = b;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input(br);

        Segment seg = new Segment();

        seg.init(1, 1, N);

        StringBuilder sb = new StringBuilder();
        while (!orders.isEmpty()) {
            Order o = orders.poll();
            if (o.t == 1)
                sb.append(seg.query(1, 1, N, o.a, o.b).count).append("\n");

            else if (o.t == 2)
                seg.update(1, 1, N, o.a, o.b);
        }

        System.out.println(sb);
    }

    static void input(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        trees = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++)
            trees[i] = Integer.parseInt(st.nextToken());

        orders = new ArrayDeque<>();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            orders.add(new Order(t, a, b));
        }
    }
}
