import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;

    static Node[][] line;

    static class Node implements Comparable<Node> {
        int x, y;

        Node() {
        }

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Node o) {
            return this.x < o.x ? -1 : (this.x == o.x ? this.y - o.y : 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input(br);

        boolean flag = false;


        int i = 0;
        int j = 1;
        int ccw1 = ccw(line[i][0], line[i][1], line[j][0]) * ccw(line[i][0], line[i][1], line[j][1]);
        int ccw2 = ccw(line[j][0], line[j][1], line[i][0]) * ccw(line[j][0], line[j][1], line[i][1]);

        if (ccw1 == 0 && ccw2 == 0) {
            compare(i, line[i][0], line[i][1]);
            compare(j, line[j][0], line[j][1]);
            // 1 2 3 4
            // 1 3 2 4
            if (line[j][0].compareTo(line[i][1]) <= 0 && line[i][0].compareTo(line[j][1]) <= 0)
                flag = true;

        } else if (ccw1 <= 0 && ccw2 <= 0)
            flag = true;


        System.out.println(flag ? 1 : 0);
    }

    static void compare(int index, Node a, Node b) {
        if (a.compareTo(b) > 0) {
            line[index][0] = new Node(b.x, b.y);
            line[index][1] = new Node(a.x, a.y);
        }
    }

    static int ccw(Node a, Node b, Node c) {
        return Long.signum((long)(b.x - a.x) * (c.y - a.y) - (long)(c.x - a.x) * (b.y - a.y));
    }

    static void input(BufferedReader br) throws IOException {
        StringTokenizer st;

        line = new Node[2][2];

        for (int i = 0; i < 2; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++)
                line[i][j] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
    }
}
