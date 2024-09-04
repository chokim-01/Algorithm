import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
    static int N, M;
    static ArrayList<Node>[] link;

    static class Node implements Comparable<Node> {
        int x, cost;

        Node(int x, int c) {
            this.x = x;
            this.cost = c;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] se = input(br);

        int[] v = new int[N + 1];
        Arrays.fill(v, Integer.MAX_VALUE);

        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(se[0], 0));
        while (!q.isEmpty()) {
            Node now = q.poll();

            for (Node next : link[now.x]) {
                if (now.cost + next.cost >= v[next.x])
                    continue;
                q.add(new Node(next.x, v[next.x] = now.cost + next.cost));
            }
        }

        System.out.println(v[se[1]]);
    }

    static int[] input(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        link = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++)
            link[i] = new ArrayList<>();

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            link[a].add(new Node(b, c));
            link[b].add(new Node(a, c));
        }

        return Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
