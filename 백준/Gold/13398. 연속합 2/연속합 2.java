import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine().trim());
        int[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] prefixSum = new int[N];
        int[] ans = new int[N];

        int max = prefixSum[0] = ans[0] = arr[0];
        for (int i = 1; i < N; i++) {
            prefixSum[i] = Math.max(arr[i], prefixSum[i - 1] + arr[i]);
            ans[i] = Math.max(ans[i - 1] + arr[i], prefixSum[i - 1]);
            max = Math.max(max, Math.max(prefixSum[i], ans[i]));
        }

        System.out.println(max);
    }
}
