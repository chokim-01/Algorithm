import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
    static int N, Q;

    static class Seg {
        long[] tree;

        Seg() {
            this.tree = new long[(N+1) << 1];
        }

        void update(int x, long v) {

            x += N;
            v -= tree[x];
            do {
                tree[x] += v;
            } while ((x >>= 1) != 0);
        }

        long query(int l, int r) {
            l += N;
            r += N + 1;
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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        int[] nums = Stream.of(("0 "+br.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();

        // set
        Seg seg = new Seg();
        for (int i = 1; i <= N; i++)
            seg.update(i, nums[i]);

        StringBuilder ans = new StringBuilder();
        // q
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(x > y){
                int tmp = x;
                x = y;
                y = tmp;
            }
            ans.append(seg.query(x, y)).append("\n");
            seg.update(a, b);
        }
        System.out.println(ans);
    }

}