import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
    static int M, N;
    static int[] caterpillar;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        caterpillar = new int[1500];
        Arrays.fill(caterpillar,1);

        for (int i = 0; i < N; i++) {
            int loc = 0;
            int[] grow = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < 3; j++)
                for (int k = 0; k < grow[j]; k++)
                    caterpillar[loc++] += j;
        }


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            sb.append(caterpillar[M - 1 - i]).append(" ");
            for (int j = 0; j < M - 1; j++)
                sb.append(caterpillar[M + j]).append(" ");
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
