import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

public class Main {

    static int N, Q;
    static Queue<int[]> query;
    // 0 →, 1 ↑ , 2 ←,3 ↓
    static HashMap<Integer, ArrayList<Node>>[] list;

    static class Node {
        int x;
        long count;

        Node(int x, long c) {
            this.x = x;
            this.count = c;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", count=" + count +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input(br);

        System.out.println(solve());
    }

    static long solve() {
        long ret = 0;

        // make prefix sum
        prefixSum();

        int cx = 0;
        int cy = 0;
        while (!query.isEmpty()) {
            int[] q = query.poll();
            // move
            // →
            if (q[0] == 0) {
                if (list[0].containsKey(cy))
                    ret += binSearch(list[0].get(cy), cx + q[1]) - binSearch(list[0].get(cy), cx + 0.5);
                cx += q[1];
            }
            // ↑
            else if (q[0] == 1) {
                if (list[1].containsKey(cx))
                    ret += binSearch(list[1].get(cx), cy + q[1]) - binSearch(list[1].get(cx), cy + 0.5);
                cy += q[1];
            }
            // ←
            else if (q[0] == 2) {
                if (list[0].containsKey(cy))
                    ret += binSearch(list[0].get(cy), cx - 0.5) - binSearch(list[0].get(cy), cx - q[1] - 0.5);
                cx -= q[1];
            }
            // ↓
            else {
                if (list[1].containsKey(cx))
                    ret += binSearch(list[1].get(cx), cy - 0.5) - binSearch(list[1].get(cx), cy - q[1] - 0.5);
                cy -= q[1];
            }
        }

        return ret;
    }

    static long binSearch(ArrayList<Node> list, double x) {
        int l = 0;
        int r = list.size() - 1;
        long ret = 0;

        while (l <= r) {
            int mid = (l + r) >> 1;
            if (list.get(mid).x <= x) {
                ret = list.get(mid).count;
                l = mid + 1;
            } else
                r = mid - 1;
        }

        return ret;
    }

    static void prefixSum() {
        for (int k = 0; k < 2; k++) {
            for (int key : list[k].keySet()) {
                Collections.sort(list[k].get(key), Comparator.comparingInt(o -> o.x));

                long sum = 0;
                for (int i = 0; i < list[k].get(key).size(); i++) {
                    long tmp = list[k].get(key).get(i).count;
                    list[k].get(key).get(i).count += sum;
                    sum += tmp;
                }
            }
        }
    }

    static void input(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        list = new HashMap[2];
        for (int i = 0; i < 2; i++)
            list[i] = new HashMap<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1; // x
            int b = Integer.parseInt(st.nextToken()) - 1; // y
            int c = Integer.parseInt(st.nextToken()); // c

            if (!list[0].containsKey(b))
                list[0].put(b, new ArrayList<>());
            if (!list[1].containsKey(a))
                list[1].put(a, new ArrayList<>());

            list[0].get(b).add(new Node(a, c));
            list[1].get(a).add(new Node(b, c));
        }
        query = new ArrayDeque<>();

        for (int i = 0; i < Q; i++)
            query.add(Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray());
    }
}
