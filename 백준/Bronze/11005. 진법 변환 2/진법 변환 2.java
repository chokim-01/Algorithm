import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {
    static int N, B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        ArrayList<Integer> square = new ArrayList<>();

        long num = 1;
        while (num <= N) {
            square.add((int) num);
            num *= B;
        }
        Collections.reverse(square);

        StringBuilder ans = new StringBuilder();
        for (int s : square) {
            int v = N / s;
            ans.append(v < 10 ? v : String.valueOf((char) ('A' + v - 10)));
            N -= v * s;
        }
        System.out.println(ans);
    }
}
//10
// 