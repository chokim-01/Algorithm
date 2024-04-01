import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    static int N, K;
    static final int NN = 100000;

    static class Seg {
        int[] tree;

        public Seg() {
            // TODO Auto-generated constructor stub
            tree = new int[NN << 1];
        }

        int query(int a, int b) {
            int ret = 0;
            a += NN;
            b += NN;
            while (a < b) {
                if ((a & 1) == 1)
                    ret += tree[a++];
                if ((b & 1) == 1)
                    ret += tree[--b];
                a >>= 1;
                b >>= 1;
            }
            return ret;
        }

        void update(int a, int v) {
            a += NN;
            do
                tree[a] += v;
            while ((a >>= 1) != 0);
        }

        int find() {
            int l = 0;
            int r = 100000;
            while (l < r) {
                int mid = (l + r) >> 1;
                if (query(mid, NN) > (K) / 2)
                    l = mid + 1;
                else
                    r = mid;
            }
            return l - 1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Seg seg = new Seg();

        int[] array = IntStream.range(0, N).map(i -> {
            try {
                return Integer.parseInt(br.readLine());
            } catch (Exception e) {
                return -1;
            }
        }).toArray();

        for (int i = 0; i < K - 1; i++)
            seg.update(array[i], 1);

        long ans = 0;
        for (int i = K - 1; i < N; i++) {
            seg.update(array[i], 1);
            ans += seg.find();
            seg.update(array[i - K + 1], -1);
        }

        System.out.println(ans);
    }
}
