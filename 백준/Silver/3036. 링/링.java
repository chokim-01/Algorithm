import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
    static int N;
    static int[] circle;

    static int gcd(int x, int y) {
        int r;
        while (y > 0) {
            r = x % y;
            x = y;
            y = r;
        }
        return x;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        circle = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < N; i++) {
            int gcdV = gcd(circle[0], circle[i]);
            sb.append(circle[0] / gcdV + "/" + circle[i] / gcdV).append("\n");
        }
        System.out.println(sb);
    }
}
 