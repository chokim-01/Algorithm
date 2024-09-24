import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    static int N, M, Q;

    static int[] time;
    static long[][] link;

    static ArrayList<Query> query;

    static class Query {
        int q, a, b;

        Query(int q, int a, int b) {
            this.q = q;
            this.a = a;
            this.b = b;
        }

        @Override
        public String toString() {
            return "Query{" +
                    "q=" + q +
                    ", a=" + a +
                    ", b=" + b +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input(br);


        Stack<Long> ans = solve();
        StringBuilder sb = new StringBuilder();

        while (!ans.isEmpty())
            sb.append(ans.pop()).append("\n");

        System.out.println(sb);
    }

    static Stack<Long> solve() {
        Stack<Long> stack = new Stack<>();

        for (int k = 1; k <= N; k++)
            for (int i = 1; i <= N; i++)
                for (int j = 1; j <= N; j++)
                    link[i][j] = Math.min(link[i][j], link[i][k] + link[k][j] + time[k]);


        int index = query.size();
        int save = query.get(index - 1).a;
        while (index-- > 0) {

            Query q = query.get(index);
            if (q.q == 1) {
                time[q.a] -= q.b;
                if (q.a != save)
                    calc(save);
                save = q.a;

            } else {
                long l = link[q.a][q.b];
                if (save != 0)
                    for (int i = 1; i <= N; i++)
                        l = Math.min(l, link[q.a][i] + link[i][q.b] + time[i]);

                stack.add(l == (long) 10e10 ? -1 : l);
            }
        }

        return stack;
    }

    static void calc(int x) {
        for (int i = 1; i <= N; i++)
            for (int j = 1; j <= N; j++)
                link[i][j] = Math.min(link[i][j], link[i][x] + link[x][j] + time[x]);
    }

    static void input(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        link = new long[N + 1][N + 1];
        time = Stream.of(("0 " + br.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 1; i <= N; i++)
            Arrays.fill(link[i], (long) 10e10);

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            link[a][b] = c;
            link[b][a] = c;
        }

        query = new ArrayList<>();
        Query bef = null;
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int q = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            Query quer = new Query(q, a, b);
            if (q == 1) {
                time[a] += b;
                if (bef != null && bef.q == 1 && bef.a == a)
                    bef.b += b;
                else
                    query.add(bef = quer);
            } else {
                query.add(quer);
                bef = null;
            }
        }
    }
}