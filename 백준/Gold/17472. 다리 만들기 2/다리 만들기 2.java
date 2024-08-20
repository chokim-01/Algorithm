import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.function.BiFunction;
import java.util.stream.Stream;

// 조합, 탐색만으로 문제 풀이

public class Main {
    static int N, M;
    static int[][] map, link;
    static int[][] dxy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static boolean[] v;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input(br);

        mapNumbering();

        linkIslandsHorizontal();
        linkIslandsVertical();

        ans = link.length == 2 ? 0 : Integer.MAX_VALUE;

        for (int start = 1; start < link.length; start++) {
            v = new boolean[link.length];
            v[start] = true;
            combination(0, 0, new ArrayList<>(Arrays.asList(start)));
            v[start] = false;
        }

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    static void combination(int depth, int cost, ArrayList<Integer> choice) {
        if (depth == link.length - 2) {
            ans = Math.min(ans, cost);
            return;
        }

        for (int now : new ArrayList<>(choice)) {
            for (int next = 1; next < link.length; next++) {
                if (link[now][next] == Integer.MAX_VALUE || v[next])
                    continue;

                v[next] = true;
                choice.add(next);
                combination(depth + 1, cost + link[now][next], choice);
                choice.remove(choice.size() - 1);
                v[next] = false;
            }
        }
    }


    static void linkIslandsHorizontal() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0)
                    continue;

                if (j >= M - 3 || map[i][j + 1] != 0 || map[i][j + 2] != 0)
                    continue;

                int islandIdx = map[i][j];
                for (int k = j + 3; k < M; k++) {
                    if (map[i][k] == 0)
                        continue;
                    link[islandIdx][map[i][k]] = link[map[i][k]][islandIdx] = Math.min(link[islandIdx][map[i][k]], k - j - 1);
                    j = k - 1;
                    break;

                }
            }
        }
    }

    static void linkIslandsVertical() {
        for (int j = 0; j < M; j++) {
            for (int i = 0; i < N; i++) {
                if (map[i][j] == 0)
                    continue;
                if (i >= N - 3 || map[i + 1][j] != 0 || map[i + 2][j] != 0)
                    continue;

                int islandIdx = map[i][j];
                for (int k = i + 3; k < N; k++) {
                    if (map[k][j] == 0)
                        continue;

                    link[islandIdx][map[k][j]] = link[map[k][j]][islandIdx] = Math.min(link[islandIdx][map[k][j]], k - i - 1);
                    i = k - 1;
                    break;
                }
            }
        }
    }

    static void mapNumbering() {
        int islandIdx = 2;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 1)
                    continue;
                markIsland(i, j, islandIdx);
                islandIdx++;
            }
        }

        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                map[i][j] = map[i][j] != 0 ? map[i][j] - 1 : 0;

        link = new int[islandIdx - 1][islandIdx - 1];
        for (int i = 0; i < link.length; i++)
            Arrays.fill(link[i], Integer.MAX_VALUE);
    }

    static void markIsland(int x, int y, int islandIdx) {

        map[x][y] = islandIdx;

        boolean findFlag = true;

        while (findFlag) {
            findFlag = false;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] != islandIdx)
                        continue;

                    for (int d = 0; d < 4; d++) {
                        int nx = i + dxy[d][0];
                        int ny = j + dxy[d][1];

                        if (!mapChk.apply(nx, ny) || map[nx][ny] != 1)
                            continue;

                        map[nx][ny] = islandIdx;
                        findFlag = true;
                    }
                }
            }
        }
    }


    static BiFunction<Integer, Integer, Boolean> mapChk = (x, y) -> x >= 0 && y >= 0 && x < N && y < M;

    static void input(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++)
            map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
