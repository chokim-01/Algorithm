import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    static boolean[][] v = new boolean[2000][2000];

    static class Node {
        int[] rocks;

        Node(int[] array) {
            this.rocks = array;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] array = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(go(array) ? 1 : 0);
    }

    static boolean go(int[] array) {
        if (array[0] == array[1] && array[2] == array[0])
            return true;
        if ((array[0] + array[1] + array[2]) % 3 != 0)
            return false;

        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(array));
        v[array[0]][array[1]] = v[array[0]][array[2]] = v[array[1]][array[0]] = v[array[1]][array[2]] = v[array[2]][array[0]] = v[array[2]][array[1]] = true;

        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int i = 0; i < 3; i++) {
                int zIdx = (i + 2) % 3;
                int x = now.rocks[i];
                int y = now.rocks[(i + 1) % 3];

                if (x == y)
                    continue;
                if (x < y) {
                    y -= x;
                    x += x;
                } else {
                    x -= y;
                    y += y;
                }
                if (x < 0 || y < 0 || v[x][y])
                    continue;

                if (x == y && y == now.rocks[zIdx])
                    return true;
                v[x][y] = v[y][x] = true;


                q.add(new Node(new int[]{x, y, now.rocks[zIdx]}));
            }
        }
        return false;
    }
}