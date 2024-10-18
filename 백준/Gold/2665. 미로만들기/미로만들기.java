import java.util.*;

public class Main {
    static int N;
    static int[][] map;

    static int[][] dxy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static class Node {
        int x, y, cnt;

        public Node(int x, int y, int cnt) {
            // TODO Auto-generated constructor stub
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            String s = sc.next();
            for (int j = 0; j < N; j++)
                map[i][j] = s.charAt(j) - 48;
        }

        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(0, 0, 0));

        int[][] visit = new int[N][N];
        for (int i = 0; i < N; i++)
            Arrays.fill(visit[i], Integer.MAX_VALUE);
        visit[0][0] = 0;

        outer:
        while (!q.isEmpty()) {
            Node n = q.poll();

            for (int d = 0, nx, ny; d < 4; d++) {
                nx = n.x + dxy[d][0];
                ny = n.y + dxy[d][1];
                if (!mapChk(nx, ny) || n.cnt + (map[nx][ny] == 0 ? 1 : 0) >= visit[nx][ny])
                    continue;

                if (map[nx][ny] == 0)
                    q.add(new Node(nx, ny, visit[nx][ny] = n.cnt + 1));
                else
                    q.add(new Node(nx, ny, visit[nx][ny] = n.cnt));
                }
        }

        System.out.println(visit[N-1][N-1]);
    }

    static boolean mapChk(int x, int y) {
        if (x < 0 || y < 0 || x >= N || y >= N)
            return false;
        return true;
    }

}