import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
    static int N, M, Len;
    static final int MOD = 1000000007;

    static int dxy[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static long[][] origin;

    public long solution(int[][] grid, int[] d, int k) {
        N = grid.length;
        M = grid[0].length;
        Len = N * M;


        long[][] matrix = makeRoute(grid, d);

        origin = Arrays.stream(matrix).map(x -> x.clone()).toArray(long[][]::new);

        long[][] result = divideConquer(matrix, k);

        long answer = 0;
        for (int i = 0; i < Len; i++)
            for (int j = 0; j < Len; j++)
                answer = (answer + result[i][j]) % MOD;

        return answer;
    }

    public static long[][] divideConquer(long[][] matrix, long cnt) {

        if (cnt == 1)
            return matrix;

        long[][] result = divideConquer(matrix, cnt / 2);

        long[][] ret = multiply(result, result);
        if (cnt % 2 == 1)
            return multiply(ret, origin);

        return ret;
    }

    public static long[][] multiply(long[][] A, long[][] B) {
        long[][] ret = new long[Len][Len];
        for (int i = 0; i < Len; i++)
            for (int j = 0; j < Len; j++)
                for (int k = 0; k < Len; k++)
                    ret[i][j] = (ret[i][j] + (A[i][k] * B[k][j]) % MOD) % MOD;

        return ret;
    }

    static long[][] makeRoute(int[][] map, int[] d) {
        long[][][] ret = new long[d.length + 1][Len][Len];

        int[] per = IntStream.concat(IntStream.of(0), Arrays.stream(d)).toArray();

        for (int i = 0; i < N * M; i++)
            ret[0][i][i] = 1;

        for (int i = 1; i <= d.length; i++) {
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < M; y++) {
                    int xy = convertXY(x, y);
                    for (int dd = 0; dd < 4; dd++) {

                        int nx = x + dxy[dd][0];
                        int ny = y + dxy[dd][1];
                        if (!mapChk(nx, ny) || map[nx][ny] - map[x][y] != per[i])
                            continue;
                        int nxy = convertXY(nx, ny);

                        for (int k = 0; k < Len; k++)
                            ret[i][k][nxy] = (ret[i][k][nxy] + ret[i - 1][k][xy]) % MOD;
                    }
                }
            }
        }
        return ret[d.length];
    }

    static boolean mapChk(int x, int y) {
        if (x < 0 || y < 0 || x >= N || y >= M)
            return false;
        return true;
    }

    static int convertXY(int x, int y) {
        return x * M + y;
    }
}