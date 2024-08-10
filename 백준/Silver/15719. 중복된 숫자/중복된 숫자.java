import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        boolean[] m = new boolean[N + 1];
        while (st.hasMoreTokens()) {
            int n = Integer.parseInt(st.nextToken());
            if (!m[n])
                m[n] = true;
            else {
                System.out.println(n);
                break;
            }
        }
    }
}