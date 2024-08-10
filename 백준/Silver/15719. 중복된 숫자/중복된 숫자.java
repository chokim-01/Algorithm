import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine())-1;

        StringTokenizer st = new StringTokenizer(br.readLine());

        long sum = 0;
        while (st.hasMoreTokens())
            sum += Integer.parseInt(st.nextToken());

        System.out.println(sum - ((long)N * (N + 1) / 2));
    }
}