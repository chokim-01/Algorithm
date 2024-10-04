import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        while (N-- > 0) {
            int num = Integer.parseInt(br.readLine());
            int c = calc(num, 2);
            int c2 = calc(num, 5);
            sb.append(Math.min(c, c2)).append("\n");
        }
        System.out.println(sb);
    }

    static int calc(int N, int c) {
        int ret = 0;
        while (N > 0) {
            ret += N / c;
            N /= c;
        }
        return ret;
    }

    /*
    2*5의 개수
    2의 약수의 개수 n 5의 약수의 개수

     */
}
