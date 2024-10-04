import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int D1 = Integer.parseInt(st.nextToken());
        int D2 = Integer.parseInt(st.nextToken());

        int ans = 0;
        boolean[][] save = new boolean[2001][2001];

        for (int i = D1; i <= D2; i++) {
            for (int j = 1; j <= i; j++) {
                int g = gcd(j, i);
                if(save[j/g][i/g])
                    continue;
                save[j/g][i/g] = true;
                ans++;
            }
        }

        System.out.println(ans);
    }
}
