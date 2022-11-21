import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static long A, B;
    public static long[] D, S;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        D = new long[55];
        S = new long[55];
        D[0] = 1;
        S[0] = 1;
        for(int i = 1; i < 55; i++) {
            D[i] = (long) Math.pow(2, i) + S[i - 1];
            S[i] = S[i - 1] + D[i];
        }
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
        bw.write(Long.toString(getDigit(B) - getDigit(A - 1)));
        br.close();
        bw.flush();
        bw.close();
    }
    public static long getDigit(long num) {
        if(num <= 0) {
            return 0;
        }
        if(num == 1) {
            return 1;
        }
        long res = 0;
        int idx = 0;
        for(int i = 0; i < 55; i++) {
            if(num < (long) Math.pow(2, i) - 1) {
                num -= ((long) Math.pow(2, i - 1) - 1);
                idx = i - 1;
                break;
            }
        }
        res += S[idx - 1];  // 2 ^ idx승까지의 갯수 구하기
        res += num;     // 맨 앞자리 1의개수만큼 더해주고 1부터 num까지의 자릿수 합 재귀로 계산
        return res + getDigit(num - 1);
    }
}
