import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

public class Main {
    static int[] nums;

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        nums = IntStream.range(0, 9).map(i -> {
            try {
                return Integer.parseInt(br.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).toArray();

        int[][] save = {
                {0, 1, 2, 3, 4, 5, 6},
                {0, 1, 2, 3, 4, 5, 7},
                {0, 1, 2, 3, 4, 5, 8},
                {0, 1, 2, 3, 4, 6, 7},
                {0, 1, 2, 3, 4, 6, 8},
                {0, 1, 2, 3, 4, 7, 8},
                {0, 1, 2, 3, 5, 6, 7},
                {0, 1, 2, 3, 5, 6, 8},
                {0, 1, 2, 3, 5, 7, 8},
                {0, 1, 2, 3, 6, 7, 8},
                {0, 1, 2, 4, 5, 6, 7},
                {0, 1, 2, 4, 5, 6, 8},
                {0, 1, 2, 4, 5, 7, 8},
                {0, 1, 2, 4, 6, 7, 8},
                {0, 1, 2, 5, 6, 7, 8},
                {0, 1, 3, 4, 5, 6, 7},
                {0, 1, 3, 4, 5, 6, 8},
                {0, 1, 3, 4, 5, 7, 8},
                {0, 1, 3, 4, 6, 7, 8},
                {0, 1, 3, 5, 6, 7, 8},
                {0, 1, 4, 5, 6, 7, 8},
                {0, 2, 3, 4, 5, 6, 7},
                {0, 2, 3, 4, 5, 6, 8},
                {0, 2, 3, 4, 5, 7, 8},
                {0, 2, 3, 4, 6, 7, 8},
                {0, 2, 3, 5, 6, 7, 8},
                {0, 2, 4, 5, 6, 7, 8},
                {0, 3, 4, 5, 6, 7, 8},
                {1, 2, 3, 4, 5, 6, 7},
                {1, 2, 3, 4, 5, 6, 8},
                {1, 2, 3, 4, 5, 7, 8},
                {1, 2, 3, 4, 6, 7, 8},
                {1, 2, 3, 5, 6, 7, 8},
                {1, 2, 4, 5, 6, 7, 8},
                {1, 3, 4, 5, 6, 7, 8},
                {2, 3, 4, 5, 6, 7, 8}
        };
        for (int i = 0; i < 36; i++) {
            int sum = 0;
            for (int j = 0; j < 7; j++)
                sum += nums[save[i][j]];
            if (sum == 100) {
                for (int n : save[i])
                    System.out.println(nums[n]);
                return;
            }
        }
    }
}