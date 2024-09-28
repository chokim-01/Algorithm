import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Stream;

class Main {
    static int N, M;
    static int[][] map;
    static Queue<Node>[] virus;
    static Infection[][] infection;

    static int[][] dxy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static class Infection {
        int virusNum, time;

        Infection() {
            this.virusNum = 0;
            this.time = Integer.MAX_VALUE;
        }

        Infection(int a, int b) {
            this.virusNum = a;
            this.time = b;
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

        int[] ans = solve();

        System.out.println(ans[1] + " " + ans[2] + " " + ans[3]);
    }

    static int[] solve() {
        findVirus();

        int time = 0;

        while (!virus[1].isEmpty() || !virus[2].isEmpty()) {
            // blue
            bfs(1, time + 1);

            // red
            bfs(2, time + 1);
            time++;
        }

        int[] ret = new int[4];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                if (infection[i][j].virusNum > 0)
                    ret[infection[i][j].virusNum]++;

        return ret;
    }

    static void findVirus() {
        // 1 : blue 2 : red
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                infection[i][j] = new Infection(map[i][j], map[i][j] > 0 ? 0 : Integer.MAX_VALUE);

                if (map[i][j] == 1)
                    virus[1].add(new Node(i, j));
                else if (map[i][j] == 2)
                    virus[2].add(new Node(i, j));

            }
        }
    }

    static void bfs(int virusNum, int time) {
        int size = virus[virusNum].size();
        while (size-- > 0) {
            Node n = virus[virusNum].poll();
            if(infection[n.x][n.y].virusNum == 3)
                continue;

            for (int d = 0; d < 4; d++) {
                int nx = n.x + dxy[d][0];
                int ny = n.y + dxy[d][1];
                if (!mapChk.apply(nx, ny) || infection[nx][ny].time < time)
                    continue;
                if (infection[nx][ny].virusNum == -1 || infection[nx][ny].virusNum == 3 || infection[nx][ny].virusNum == virusNum)
                    continue;
                if (infection[nx][ny].virusNum == 0) {
                    infection[nx][ny].virusNum = virusNum;
                    infection[nx][ny].time = time;
                    virus[virusNum].add(new Node(nx, ny));
                } else if (infection[nx][ny].time == time && infection[nx][ny].virusNum != virusNum)
                    infection[nx][ny].virusNum = 3;
            }
        }
    }

    static BiFunction<Integer, Integer, Boolean> mapChk = (x, y) -> x >= 0 && x < N && y >= 0 && y < M;

    static void input(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++)
            map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        infection = new Infection[N][M];

        virus = new ArrayDeque[3];
        virus[1] = new ArrayDeque<>();
        virus[2] = new ArrayDeque<>();

    }
}