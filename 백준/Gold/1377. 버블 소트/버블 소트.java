import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];

        for (int i = 1; i <= N; i++)
            arr[i] = Integer.parseInt(br.readLine());

        List<int[]> sortedArr = IntStream.rangeClosed(0, N)
                .mapToObj(i -> new int[]{i, arr[i]})
                .sorted(Comparator.comparingInt((int[] x) -> x[1]).thenComparing(x -> x[0]))
                .collect(Collectors.toList());

        int c = 0;
        for (int i = 1; i <= N; i++)
            c = Math.max(c, sortedArr.get(i)[0] - i);

        System.out.println(c + 1);
    }
}
