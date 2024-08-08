import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static Node[] link;
    static int N;
    static int[] v;

    static class Node {
        char type;
        int cost;

        ArrayList<Integer> to;

        Node(char type, int cost, ArrayList<Integer> to) {
            this.type = type;
            this.cost = cost;
            this.to = to;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (input(br))
            sb.append(dfs(1, 0) ? "Yes" : "No").append("\n");

        System.out.println(sb);
    }

    static boolean dfs(int now, int cost) {
        cost = calcCost(cost, link[now].type, link[now].cost);

        if (cost < 0 || v[now] >= cost)
            return false;
        v[now] = cost;

        if (now == N)
            return true;

        if (link[now] == null)
            return false;

        for (int next : link[now].to)
            if (dfs(next, cost))
                return true;

        return false;
    }

    static int calcCost(int cost, char type, int cCost) {
        if (type == 'L')
            cost = Math.max(cost, cCost);
        else if (type == 'T')
            cost = cost - cCost;

        return cost;
    }

    static boolean input(BufferedReader br) throws IOException {
        N = Integer.parseInt(br.readLine());

        if (N == 0)
            return false;

        link = new Node[N + 1];
        v = new int[N + 1];
        Arrays.fill(v, -1);

        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            char type = st.nextToken().charAt(0);
            int cost = Integer.parseInt(st.nextToken());

            ArrayList<Integer> to = new ArrayList<>();
            while (st.countTokens() != 1)
                to.add(Integer.parseInt(st.nextToken()));

            link[i] = new Node(type, cost, to);
        }

        return true;
    }
}
