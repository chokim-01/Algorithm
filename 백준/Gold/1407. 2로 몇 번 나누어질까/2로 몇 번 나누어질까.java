import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

class Main {
    static long[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        arr = IntStream.range(0, 50).mapToLong(x -> (long) Math.pow(2, 49 - x)).toArray();

        long ans = solve(B) - solve(A - 1);

        System.out.println(ans);
    }

    static long solve(long num) {
        long ret = 0;
        int idx = -1;

        while (idx < 49 && arr[++idx] > num) ;

        long sum = 0;

        while (idx < 50) {
            long que = num / arr[idx];
            ret += (que - sum) * arr[idx];
            sum += que - sum;
            idx++;
        }
        return ret;
    }
}

