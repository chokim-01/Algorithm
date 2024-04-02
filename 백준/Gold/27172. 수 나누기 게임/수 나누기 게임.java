import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {
    static final int MAX = 1000001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        br.readLine();
        int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] ans = new int[input.length];

        int[] numbers = new int[MAX];
        for (int i = 0; i < input.length; i++)
            numbers[input[i]] = i == 0 ? -1 : i;

        for (int i = 0, now; i < input.length; i++) {
            now = input[i];
            for (int next = now * 2; next < MAX; next += now) {

                if (numbers[next] != 0) {
                    ans[i]++;
                    ans[numbers[next] == -1 ? 0 : numbers[next]]--;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int a : ans)
            sb.append(a).append(" ");

        System.out.println(sb);

    }
}
