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
        N = Integer.parseInt(br.readLine());

        int ans = 0;
        for (int i = 1; ((long) i) * i <= N; i++)
            ans++;

        System.out.println(ans);

    }
    /*
    3개 시작 : 1
    1 : 111
    2 : 101
    3 : 100
    3 2 1

    4개 시작 : 2
    1 : 1111
    2 : 1010
    3 : 1000
    4 : 1001
    4 2 1 2

    5개 시작 : 2
    1 : 11111
    2 : 10101
    3 : 10001
    4 : 10011
    5 : 10010

    6개 시작 : 2
    1 : 111111
    2 : 101010
    3 : 101111
    4 : 101011
    5 : 101001
    6 : 101000

    7개 시작 : 2
    1 : 1111111
    2 : 1010101
    3 : 1000111
    4 : 1001111
    5 : 1001011
    6 : 1001001
    7 : 1001000

    8개 시작 : 2
    1 : 11111111
    2 : 10101010
    3 : 10001110
    4 : 10011111
    5 : 10010111
    6 : 10010011
    7 : 10010001
    8 : 10010000

    9개 시작 : 3
    1 : 111111111
    2 : 101010101
    3 : 100011100
    4 : 100111110
    5 : 100101110
    6 : 100100110
    7 : 100100010
    8 : 100100000
    9 : 100100001

    10개 시작 :
    1 : 1111111111
    2 : 1010101010
    3 : 1000111000
    4 : 1001111100
    5 : 1001011101
    6 : 1001001101
    7 : 1001000101
    8 : 1001000001
    9 : 1001000011
    10 : 1001000010

    1,4,9,.. 제곱 수마다 켜짐




    등장 제곱 수 짝수 : 0, 닫힘
    홀수 : 1, 열림

    열려있는 창문 수


     */
}
