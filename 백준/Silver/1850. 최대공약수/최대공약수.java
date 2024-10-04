import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
    static int N;
    static long[] array;

    static long gcd(long x, long y) {
        long r;
        while (y > 0) {
            r = x % y;
            x = y;
            y = r;
        }
        return x;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        array = Stream.of(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        
        System.out.println("1".repeat((int) gcd(array[0],array[1])));
    }
}
