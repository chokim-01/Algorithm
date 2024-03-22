import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] degree;
    static ArrayList<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        degree = new int[N + 1];
        list = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++)
            list[i] = new ArrayList<>();

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            degree[b]++;
            list[a].add(b);
        }

        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= N; i++)
            if (degree[i] == 0)
                q.add(i);
        StringBuilder ans = new StringBuilder();
        while (!q.isEmpty()) {
            int now = q.poll();
            ans.append(now).append(" ");
            for (int next : list[now])
                if (--degree[next] == 0)
                    q.add(next);
        }
        System.out.println(ans);
    }
}