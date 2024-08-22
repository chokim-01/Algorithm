import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Optional;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Comparator comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (Math.abs(o1) == Math.abs(o2))
                    return o1 - o2;
                return Math.abs(o1) - Math.abs(o2);
            }
        };

        PriorityQueue<Integer> pq = new PriorityQueue<>(comparator);
        StringBuilder sb = new StringBuilder();

        while (N-- > 0) {
            int num = Integer.parseInt(br.readLine());

            switch (num) {
                case 0:
                    sb.append(Optional.ofNullable(pq.poll()).orElse(0)).append("\n");
                    break;
                default:
                    pq.add(num);
                    break;
            }
        }

        System.out.println(sb);
    }
}
