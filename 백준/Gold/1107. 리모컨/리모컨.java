import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {
    static int count, dest;
    static boolean[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        dest = Integer.parseInt(br.readLine());
        numbers = new boolean[10];
        Arrays.fill(numbers, true);

        int c = Integer.parseInt(br.readLine());
        if (c != 0) {
            int[] broken = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for (int b : broken)
                numbers[b] = false;
        }
        count = Math.min(Math.abs(dest - 100), numbers[0] ? dest + 1 : 10000000);
        dfs(0, 0);
        System.out.println(count);
    }

    static void dfs(int number, int c) {
        if (number >= 1000000)
            return;
        if (c != 0)
            count = Math.min(Math.abs(dest - number) + c, count);
        for (int i = 0; i < 10; i++) {
            if (c == 0 && i == 0)
                continue;
            if (numbers[i])
                dfs(number * 10 + i, c + 1);
        }
    }

}