import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, Q;
    static int[] nums;
    static Queue<Order> orders;

    static class Segment {
        int size;
        Node[] tree;

        Segment() {
            size = N + 1;
            tree = new Node[size << 1];
        }

        Node combine(Node l, Node r) {
            if (l == null)
                return r;
            if (r == null)
                return l;

            if (l.x > r.x)
                return r;
            else if (l.x < r.x)
                return l;
            return l.idx - r.idx < 0 ? l : r;
        }

        void update(int idx, int v) {
            idx += size;
            tree[idx] = new Node(v, idx-size);
            while ((idx /= 2) != 0)
                tree[idx] = combine(tree[idx << 1], tree[idx << 1 | 1]);

        }

        int query(int l, int r) {
            l += size;
            r += size + 1;
            Node node = new Node(Integer.MAX_VALUE, Integer.MAX_VALUE);
            for (; l < r; l >>= 1, r >>= 1) {
                if ((l & 1) == 1)
                    node = combine(node, tree[l++]);
                if ((r & 1) == 1)
                    node = combine(node, tree[--r]);
            }
            return node.idx;
        }
    }

    static class Node {
        int x, idx;

        Node(int x, int idx) {
            this.x = x;
            this.idx = idx;
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

        for (int i = 1; i <= N; i++)
            seg.update(i, nums[i]);

        StringBuilder sb = new StringBuilder();
        while (!orders.isEmpty()) {
            Order o = orders.poll();
            if (o.t == 2) {
                if (o.a > o.b) {
                    int tmp = o.a;
                    o.a = o.b;
                    o.b = tmp;
                }
                sb.append(seg.query(o.a, o.b)).append("\n");
            } else
                seg.update(o.a, o.b);
        }

        System.out.println(sb);
    }

    static void input(BufferedReader br) throws IOException {
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        nums = new int[N + 1];
        for (int i = 1; i <= N; i++)
            nums[i] = Integer.parseInt(st.nextToken());

        Q = Integer.parseInt(br.readLine());
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
