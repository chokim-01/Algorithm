import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {

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
        int[] nums = Stream.of(br.readLine().split(":")).mapToInt(Integer::parseInt).toArray();

        int g = gcd(nums[0], nums[1]);
        System.out.println(nums[0] / g + ":" + nums[1] / g);
    }
}
