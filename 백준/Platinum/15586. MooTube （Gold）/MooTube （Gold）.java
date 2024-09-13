import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static int N, M;

    static ArrayList<Node> linkQue;
    static ArrayList<Node> querys;

    static class UF {
        int[] parent, count;

        UF() {
            this.parent = IntStream.rangeClosed(0, N).toArray();
            this.count = IntStream.rangeClosed(0, N).map(x -> 1).toArray();
        }

        void union(int a, int b) {
            a = find(a);
            b = find(b);

            if (a == b)
                return;
            if (a < b) {
                parent[b] = a;
                count[a] += count[b];
            } else {
                parent[a] = b;
                count[b] += count[a];
            }
        }

        int find(int a) {
            if (a == this.parent[a])
                return a;
            return this.parent[a] = find(this.parent[a]);
        }

        int getCount(int a) {
            return this.count[find(a)];
        }
    }

    static class Node {
        // x : node, y : node | idx, k : value
        int x, y, k;

        Node(int x, int y, int k) {
            this.x = x;
            this.y = y;
            this.k = k;
        }

        Node(int y, int k) {
            this.x = -1;
            this.y = y;
            this.k = k;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    ", k=" + k +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input(br);

        StringBuilder sb = solve();

        System.out.println(sb);
    }

    static StringBuilder solve() {
        StringBuilder retSb = new StringBuilder();

        Comparator<Node> sortByK = (o1, o2) -> o2.k - o1.k;
        Comparator<Node> sortByIdx = Comparator.comparingInt(o -> o.y);

        Collections.sort(querys, sortByK);
        Collections.sort(linkQue, sortByK);

        ArrayList<Node> answer = new ArrayList<>();
        UF uf = new UF();

        int linkQueIdx = 0;
        for (Node query : querys) {
            while (linkQueIdx < linkQue.size() && linkQue.get(linkQueIdx).k >= query.k) {
                Node link = linkQue.get(linkQueIdx);
                uf.union(link.x, link.y);
                linkQueIdx++;
            }
            answer.add(new Node(query.y, uf.getCount(query.x)));
        }

        Collections.sort(answer, sortByIdx);

        for (Node ans : answer)
            retSb.append(ans.k - 1).append("\n");

        return retSb;
    }

    static void input(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        linkQue = new ArrayList<>();
        for (int i = 1, a, b, c; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            linkQue.add(new Node(a, b, c));
        }

        querys = new ArrayList<>();
        for (int i = 0, a, b; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            querys.add(new Node(b, i, a));
        }
    }
}
