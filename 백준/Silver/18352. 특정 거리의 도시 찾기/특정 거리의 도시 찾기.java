import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K, X;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        boolean[] v = new boolean[N + 1];
        ArrayList<Integer>[] link = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++)
            link[i] = new ArrayList<>();

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            link[a].add(b);
        }

        v[X] = true;
        Queue<Integer> q = new ArrayDeque<>();
        q.add(X);

        ArrayList<Integer> list = new ArrayList<>();
        while (!q.isEmpty() && K != 0) {
            int size = q.size();
            K--;
            while (size-- > 0) {
                int now = q.poll();

                for (int next : link[now]) {
                    if (v[next])
                        continue;
                    v[next] = true;
                    if(K == 0)
                        list.add(next);
                    q.add(next);
                }
            }
        }
        Collections.sort(list);
        StringBuilder ans = new StringBuilder();
        list.forEach(x->ans.append(x).append("\n"));
        System.out.println(ans.isEmpty() ?-1:ans);
    }
}
