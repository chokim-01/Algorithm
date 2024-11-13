import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    static int N, T, M;
    static Node[] nodes;
    static int[][] dist;
    static Queue<int[]> query;

    static class Node {
        int idx, x, y;
        boolean flag;

        Node(int idx, int x, int y, boolean flag) {
            this.idx = idx;
            this.x = x;
            this.y = y;
            this.flag = flag;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input(br);

        StringBuilder ans = solve();

        System.out.println(ans);

    }

    static StringBuilder solve() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dist[i][j] = nodes[i].flag && nodes[j].flag ? T : 1000000;
                if (i == j)
                    dist[i][j] = 0;

                dist[i][j] = Math.min(dist[i][j], calc(i, j));
            }
        }
        for (int k = 0; k < N; k++)
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++)
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);

        StringBuilder sb = new StringBuilder();
        while (!query.isEmpty()) {
            int[] q = query.poll();
            sb.append(dist[q[0]][q[1]]).append("\n");
        }

        return sb;
    }

    static int calc(int idx1, int idx2) {
        return Math.abs(nodes[idx1].x - nodes[idx2].x) + Math.abs(nodes[idx1].y - nodes[idx2].y);
    }

    static void input(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        nodes = new Node[N];
        dist = new int[N][N];


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            boolean flag = Integer.parseInt(st.nextToken()) == 0 ? false : true;
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            nodes[i] = new Node(i, x, y, flag);
        }

        M = Integer.parseInt(br.readLine());

        query = new ArrayDeque<>();
        while (M-- > 0)
            query.add(Stream.of(br.readLine().split(" ")).mapToInt(x -> Integer.parseInt(x) - 1).toArray());
    }
}
