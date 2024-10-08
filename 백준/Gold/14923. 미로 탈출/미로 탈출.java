import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public class Main {
    static int N, M;
    static int[] start, end;
    static int[][] map;

    static class Node {
        int x, y;
        boolean f;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
            this.f = false;
        }

        Node(int x, int y, boolean f) {
            this.x = x;
            this.y = y;
            this.f = f;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        start = Stream.of(br.readLine().split(" ")).mapToInt(x -> Integer.parseInt(x) - 1).toArray();
        end = Stream.of(br.readLine().split(" ")).mapToInt(x -> Integer.parseInt(x) - 1).toArray();

        map = new int[N][];
        for (int i = 0; i < N; i++)
            map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solve());
    }

    static int[][] dxy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static int solve() {
        Queue<Node> q = new ArrayDeque<>();
        boolean[][][] v = new boolean[N][M][2];
        q.add(new Node(start[0], start[1]));
        v[start[0]][start[1]][0] = v[start[0]][start[1]][1] = true;

        int count = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                Node n = q.poll();
                if (n.x == end[0] && n.y == end[1])
                    return count;
                for (int[] d : dxy) {
                    int nx = n.x + d[0];
                    int ny = n.y + d[1];

                    if (!mapChk.apply(nx, ny) || v[nx][ny][n.f ? 1 : 0])
                        continue;
                    if (map[nx][ny] == 0) {
                        v[nx][ny][n.f ? 1 : 0] = true;
                        q.add(new Node(nx, ny, n.f));
                    } else if (!n.f) {
                        v[nx][ny][1] = true;
                        q.add(new Node(nx, ny, true));
                    }
                }
            }
            count++;
        }

        return -1;
    }

    static BiFunction<Integer, Integer, Boolean> mapChk = (x, y) -> 0 <= x && x < N && 0 <= y && y < M;
}
