import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static ArrayList<Integer>[] link;
    static ArrayList<Integer>[] reverseLink;

    static Queue<Integer> bombs;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input(br);

        System.out.println(solve());
    }

    static StringBuilder solve() {
        StringBuilder sb = new StringBuilder();

        boolean[] startToBomb = bfs(1,link);
        boolean[] endToBomb = bfs(N,reverseLink);

        while (!bombs.isEmpty()) {
            int bomb = bombs.poll();
            sb.append(startToBomb[bomb] && endToBomb[bomb] ? "Defend the CTP" : "Destroyed the CTP").append("\n");
        }

        return sb;
    }

    static boolean[] bfs(int start,ArrayList<Integer>[] link) {
        boolean[] flag = new boolean[N + 1];
        Queue<Integer> q = new ArrayDeque<>();
        flag[start] = true;
        q.add(start);

        while (!q.isEmpty()) {
            int now = q.poll();
            for (int next : link[now]) {
                if (flag[next])
                    continue;
                flag[next] = true;
                q.add(next);
            }
        }
        return flag;
    }

    static void input(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        link = new ArrayList[N + 1];
        reverseLink = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            link[i] = new ArrayList<>();
            reverseLink[i] = new ArrayList<>();
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            link[a].add(b);
            reverseLink[b].add(a);
        }

        bombs = new ArrayDeque<>();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0)
            bombs.add(Integer.parseInt(br.readLine()));
    }
}
