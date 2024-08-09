import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());

        while (N-- > 0)
            q.add(Integer.parseInt(br.readLine()));

        int count = 0;
        while (!q.isEmpty()) {
            int num = q.poll();
            if (K < num)
                continue;
            count += K / num;
            K = K % num;
        }

        System.out.println(count);

    }
}