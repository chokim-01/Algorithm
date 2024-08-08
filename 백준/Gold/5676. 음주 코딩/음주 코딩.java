import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

class Main {
    static int N, K;

    static class Seg {
        int max;
        int[] tree;

        Seg(int[] nums) {
            this.max = nums.length;

            this.tree = new int[this.max << 1];

            Arrays.fill(this.tree, 1);

            for (int i = 1; i < nums.length; i++)
                update(i, Integer.signum(nums[i]));

        }

        void update(int x, int v) {
            x += max;
            tree[x] = v;

            while ((x >>= 1) != 0)
                tree[x] = tree[x << 1] * tree[x << 1 | 1];
        }

        int query(int l, int r) {
            int ret = 1;
            l += max;
            r += max + 1;

            for (; l < r; l >>= 1, r >>= 1) {
                if ((l & 1) == 1)
                    ret *= this.tree[l++];
                if ((r & 1) == 1)
                    ret *= this.tree[--r];
            }
            return ret;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        Seg seg;

        String nk;
        while ((nk = br.readLine()) != null) {
            st = new StringTokenizer(nk);

            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            int[] nums = Stream.of(("0 " + br.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();

            seg = new Seg(nums);

            while (K-- > 0) {
                st = new StringTokenizer(br.readLine());
                char o = st.nextToken().charAt(0);
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (o == 'C')
                    seg.update(a, Integer.signum(b));
                else {
                    int ans = seg.query(a, b);
                    sb.append((ans == 1) ? "+" : (ans == -1) ? "-" : "0");
                }

            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}