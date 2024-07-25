import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static class Segment {
        final int MAX = 86401;
        long[] tree;

        Segment() {
            this.tree = new long[MAX << 1];
        }

        void update(int[] array) {
            for (int i = MAX, n; i < tree.length; i++) {
                this.tree[i] = array[i - MAX];
                n = i;
                while ((n >>= 1) != 0)
                    this.tree[n] += this.tree[i];
            }
        }

        long prefixSum(int l, int r) {
            if (l > r)
                return query(l, MAX) + query(0, r + 1);
            else
                return query(l, r + 1);
        }

        long query(int l, int r) {
            l += MAX;
            r += MAX;
            long ret = 0;
            for (; l < r; l >>= 1, r >>= 1) {
                if ((l & 1) == 1) ret += tree[l++];
                if ((r & 1) == 1) ret += tree[--r];
            }
            return ret;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] array = new int[86403];
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " - ");
            int start = convert(st.nextToken());
            int end = convert(st.nextToken());
            if (start > end) {
                array[1]++;
                array[86401]--;
            }
            array[start]++;
            array[end + 1]--;
        }

        for (int i = 1; i < array.length; i++)
            array[i] += array[i - 1];

        Segment seg = new Segment();
        seg.update(array);

        StringBuilder sb = new StringBuilder();
        int Q = Integer.parseInt(br.readLine());

        for (int i = 0; i < Q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " - ");
            int start = convert(st.nextToken());
            int end = convert(st.nextToken());
            double len;
            if (start > end)
                len = 86401 - start + end;
            else
                len = end - start + 1;
            sb.append(String.format("%.10f",seg.prefixSum(start, end) / len)).append("\n");
        }

        System.out.print(sb);
    }

    static int convert(String s) {
        StringTokenizer st = new StringTokenizer(s, ":");
        int hours = Integer.parseInt(st.nextToken());
        int minutes = Integer.parseInt(st.nextToken());
        int seconds = Integer.parseInt(st.nextToken());

        return hours * 3600 + minutes * 60 + seconds + 1;
    }
}
