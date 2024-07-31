import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void fraction_sum(int[] A, int[] B) {
        StringBuilder sb = new StringBuilder();

        // 굳이 0으로 초기화하고 다시 변수에 값을 집어넣을 필요는 없을 것 같습니다.
        int numerator = (A[0] * B[1]) + (A[1] * B[0]); // 분자
        int denominator = B[0] * B[1]; // 분모
        int gcdValue = findGCD(numerator, denominator); // gcd 값

        sb.append(numerator / gcdValue).append(" ").append(denominator / gcdValue);
        
        System.out.println(sb);
    }

    // 최대공약수
    // 반복문으로 작성해도 현재 코드보다 간결하게 작성할 수 있을 것 같네요
    static int findGCD(long A, long B) {
        long max = Math.max(A, B);
        long min = Math.min(A, B);

        while (max != 0 && min != 0) {
            long result = max % min;
            max = min;
            min = result;
        }

        return (int) max;
    }

    // A,B는 사용되지 않는 변수 같습니다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] numerator = new int[2];
        int[] denominator = new int[2];

        for (int i = 0; i < 2; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            numerator[i] = Integer.parseInt(st.nextToken());
            denominator[i] = Integer.parseInt(st.nextToken());
        }

        fraction_sum(numerator, denominator);
    }
}