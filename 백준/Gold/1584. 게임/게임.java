import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    static int N, M;

    static boolean[][] map = new boolean[501][501];
    static boolean[][] v = new boolean[501][501];

    static int[][] deaths, dangers;
    static int[][] dxy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static class Node implements Comparable<Node> {
        int x, y, c;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
            this.c = 0;
        }

        Node(int x, int y, int c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }

        @Override
        public int compareTo(Node o) {
            return this.c - o.c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input(br);

        mapSet();

        System.out.println(go());


    }

    static int go() {

        PriorityQueue<Node> q = new PriorityQueue<>();

        q.add(new Node(0, 0));
        v[0][0] = true;

        while (!q.isEmpty()) {
            Node now = q.poll();
            for (int d = 0; d < 4; d++) {
                int nx = now.x + dxy[d][0];
                int ny = now.y + dxy[d][1];
                int nc = now.c;

                if (!mapChk.apply(nx, ny) || v[nx][ny])
                    continue;
                if (map[nx][ny])
                    nc++;
                if (nx == 500 && ny == 500)
                    return nc;

                v[nx][ny] = true;
                q.add(new Node(nx, ny, nc));
            }
        }

        return -1;
    }

    static BiFunction<Integer, Integer, Boolean> mapChk = (x, y) -> 0 <= x && x <= 500 && 0 <= y && y <= 500;

    static void mapSet() {
        for (int i = 0; i <= 500; i++) {
            for (int j = 0; j <= 500; j++) {
                if (findDeath(i, j))
                    v[i][j] = true;
                else if (findDanger(i, j))
                    map[i][j] = true;
            }
        }
    }

    static boolean findDeath(int x, int y) {
        for (int i = 0; i < M; i++)
            if (deaths[i][0] <= x && x <= deaths[i][2] && deaths[i][1] <= y && y <= deaths[i][3])
                return true;
        return false;
    }

    static boolean findDanger(int x, int y) {
        for (int i = 0; i < N; i++)
            if (dangers[i][0] <= x && x <= dangers[i][2] && dangers[i][1] <= y && y <= dangers[i][3])
                return true;
        return false;
    }

    static void input(BufferedReader br) throws IOException {

        N = Integer.parseInt(br.readLine());
        dangers = new int[N][4];

        for (int i = 0; i < N; i++)
            dangers[i] = changeXY(Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray());

        M = Integer.parseInt(br.readLine());
        deaths = new int[M][4];

        for (int i = 0; i < M; i++)
            deaths[i] = changeXY(Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray());
    }

    static int[] changeXY(int[] arr) {
        int tmp = 0;
        if (arr[0] > arr[2]) {
            tmp = arr[0];
            arr[0] = arr[2];
            arr[2] = tmp;
        }
        if (arr[1] > arr[3]) {
            tmp = arr[1];
            arr[1] = arr[3];
            arr[3] = tmp;
        }

        return arr;
    }
}
