import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    static int N, M;
    static int[][] link;

    static Queue<Customer> customers;

    static class Customer {
        int start, dest, time;

        Customer(int s, int d, int t) {
            this.start = s;
            this.dest = d;
            this.time = t;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input(br);

        for (int k = 1; k <= N; k++)
            for (int i = 1; i <= N; i++)
                for (int j = 1; j <= N; j++)
                    link[i][j] = Math.min(link[i][j], link[i][k] + link[k][j]);

        String[] speech = {"Enjoy other party", "Stay here"};
        StringBuilder ans = new StringBuilder();

        while (!customers.isEmpty()) {
            Customer now = customers.poll();
            ans.append(speech[link[now.start][now.dest] <= now.time ? 0 : 1]).append("\n");
        }

        System.out.println(ans);
    }

    static void input(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        link = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++)
                link[i][j] = Integer.parseInt(st.nextToken());
        }

        customers = new ArrayDeque<>();
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            customers.add(new Customer(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
    }
}
