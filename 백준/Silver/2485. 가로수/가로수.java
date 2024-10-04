import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int[] array;

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
        array = new int[N];

        for (int i = 0; i < N; i++)
            array[i] = Integer.parseInt(br.readLine());

        int[] diff = new int[N];
        int gcdV = array[1] - array[0];
        for (int i = 1; i < N; i++)
            gcdV = gcd(gcdV, diff[i] = array[i] - array[i - 1]);

        int ans = 0;
        for (int i = 1; i < N; i++)
            ans += diff[i] / gcdV - 1;

        System.out.println(ans);
    }
}
