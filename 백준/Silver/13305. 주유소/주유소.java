import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dist = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] oil = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 1; i < oil.length; i++)
            oil[i] = Math.min(oil[i - 1], oil[i]);

        long ans = 0;
        for (int i = 0; i < dist.length; i++)
            ans += (long) dist[i] * oil[i];

        System.out.println(ans);
    }
}
