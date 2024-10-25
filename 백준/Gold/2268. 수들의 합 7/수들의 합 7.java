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
        long[] tree;

        Segment() {
            size = N + 1;
            tree = new long[size << 1];
        }

        void update(int idx, int v) {
            long diff = tree[idx += size] - v;
            do
                tree[idx] -= diff;
            while ((idx /= 2) != 0);
        }

        long query(int l, int r) {
            l += size;
            r += size + 1;
            long ret = 0;
            for (; l < r; l >>= 1, r >>= 1) {
                if ((l & 1) == 1)
                    ret += tree[l++];
                if ((r & 1) == 1)
                    ret += tree[--r];
            }
            return ret;
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
            if (o.t == 0) {
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        nums = new int[N + 1];

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
