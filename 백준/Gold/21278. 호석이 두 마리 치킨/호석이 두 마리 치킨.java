import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    static int N, M;
    static ArrayList<Integer>[] link;
    static int[][] time;
    static boolean[] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input(br);

        int[] ans = new int[]{100000, 100000, 100000};
        for (int a = 1; a <= N; a++) {
            for (int b = a + 1; b <= N; b++) {
                int cost = bfs(a, b, ans[2]) << 1;

                if (ans[2] > cost) {
                    ans[2] = cost;
                    ans[0] = a;
                    ans[1] = b;
                } else if (ans[2] == cost) {
                    if (ans[0] > a) {
                        ans[0] = a;
                        ans[1] = b;
                    } else if (ans[0] == a)
                        ans[1] = Math.min(ans[1], b);
                }
            }
        }
        System.out.println(ans[0] + " " + ans[1] + " " + ans[2]);
    }

    static int bfs(int a, int b, int max) {
        Queue<Integer> firstStore = new ArrayDeque<>();
        Queue<Integer> secondStore = new ArrayDeque<>();

        firstStore.add(a);
        secondStore.add(b);

        v = new boolean[N + 1];
        v[a] = v[b] = true;

        int cost = 0;
        while (cost <= max && (!firstStore.isEmpty() || !secondStore.isEmpty())) {
            int firstSize = firstStore.size();
            while (firstSize-- > 0) {
                int now = firstStore.poll();
                for (int next : link[now]) {
                    if (v[next])
                        continue;
                    v[next] = true;
                    cost++;
                    firstStore.add(next);
                }
            }

            int secondSize = secondStore.size();
            while (secondSize-- > 0) {
                int now = secondStore.poll();
                for (int next : link[now]) {
                    if (v[next])
                        continue;
                    v[next] = true;
                    cost++;
                    secondStore.add(next);
                }
            }

        }
        return cost;
    }

    static void input(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        time = new int[N + 1][N + 1];

        link = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++)
            link[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            link[a].add(b);
            link[b].add(a);
        }
    }
}
