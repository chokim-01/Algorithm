import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int R, C;
    static Node hanbyul;
    static int[] order;
    static int[][] map;
    static char[][] originMap;
    static boolean[][] answer;

    static int dxy[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static ArrayList<Bundle> bundles;

    static class Bundle {
        boolean flag;
        ArrayList<Node> list;

        Bundle() {
            this.flag = false;
            this.list = new ArrayList<>();
        }
    }

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input(br);

        answer = new boolean[R][C];
        numberingMap();
        go();
        print();

    }

    static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++)
                sb.append(answer[i][j] ? '.' : '#');
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void numberingMap() {
        map = new int[R][C];
        bundles = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] != 0)
                    continue;
                bfs(i, j, originMap[i][j], ++count);
            }
        }
    }

    static void bfs(int x, int y, char c, int cnt) {
        map[x][y] = cnt;

        Bundle bundle = new Bundle();
        bundle.list.add(new Node(x, y));

        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(x, y));
        while (!q.isEmpty()) {
            Node now = q.poll();
            for (int d = 0; d < 4; d++) {
                int nx = now.x + dxy[d][0];
                int ny = now.y + dxy[d][1];
                if (!mapChk(nx, ny) || originMap[nx][ny] != c || map[nx][ny] != 0)
                    continue;
                map[nx][ny] = cnt;
                q.add(new Node(nx, ny));
                bundle.list.add(new Node(nx, ny));
            }
        }
        bundles.add(bundle);
    }

    static void go() {
        int oIdx = -1;
        while (mapChk(hanbyul.x, hanbyul.y) && ++oIdx < order.length) {
            if (order[oIdx] == 4) {
                if (!bundles.get(map[hanbyul.x][hanbyul.y] - 1).flag) {
                    bundles.get(map[hanbyul.x][hanbyul.y] - 1).flag = true;
                    installWard(bundles.get(map[hanbyul.x][hanbyul.y] - 1).list);
                }
            } else {
                hanbyul.x = hanbyul.x + dxy[order[oIdx]][0];
                hanbyul.y = hanbyul.y + dxy[order[oIdx]][1];
            }
        }
        answer[hanbyul.x][hanbyul.y] = true;
        for (int d = 0; d < 4; d++) {
            int nx = hanbyul.x + dxy[d][0];
            int ny = hanbyul.y + dxy[d][1];
            if (!mapChk(nx, ny))
                continue;
            answer[nx][ny] = true;
        }
    }

    static void installWard(ArrayList<Node> list) {
        for (Node n : list)
            answer[n.x][n.y] = true;
    }

    static boolean mapChk(int x, int y) {
        if (x < 0 || y < 0 || x >= R || y >= C)
            return false;
        return true;
    }

    static void input(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        originMap = new char[R][];
        for (int i = 0; i < R; i++)
            originMap[i] = br.readLine().toCharArray();

        st = new StringTokenizer(br.readLine());
        hanbyul = new Node(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);

        order = (br.readLine()).chars().map(Main::convert).toArray();
    }

    static int convert(int c) {
        if (c == 'U')
            return 0;
        if (c == 'D')
            return 1;
        if (c == 'L')
            return 2;
        if (c == 'R')
            return 3;
        return 4;
    }
}
