import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Node {
        int[] scv;

        Node(int[] arr) {
            scv = arr;
        }
    }

    static int[][] dn = {{9, 3, 1}, {9, 1, 3}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] scv = new int[3];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            scv[i] = Integer.parseInt(st.nextToken());

        boolean[][][] v = new boolean[61][61][61];

        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(scv));

        int count = 0;

        outer:
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                Node n = q.poll();
                if (n.scv[0] == 0 && n.scv[1] == 0 && n.scv[2] == 0)
                    break outer;

                for (int d = 0; d < 2; d++) {

                    for (int i = 0; i < 3; i++) {
                        int[] next = n.scv.clone();
                        for (int j = 0; j < 3; j++) {
                            int k = (i + j) % 3;
                            next[k] = Math.max(0, next[k] - dn[d][j]);
                            if (v[next[0]][next[1]][next[2]])
                                continue;

                            v[next[0]][next[1]][next[2]] = true;
                            q.add(new Node(next));
                        }
                    }
                }
            }
            count++;
        }
        
        System.out.println(count);
    }

}