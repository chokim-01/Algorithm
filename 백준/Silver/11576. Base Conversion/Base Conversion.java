import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
    static int AF, BF;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        AF = Integer.parseInt(st.nextToken());
        BF = Integer.parseInt(st.nextToken());
        br.readLine();

        int[] array = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int total = 0;
        int c = 1;
        for (int i = array.length - 1; i >= 0; i--) {
            total += c * array[i];
            c *= AF;
        }

        StringBuilder sb = new StringBuilder();
        do {
            int r = total % BF;
            sb.insert(0, r + " ");
            total /= BF;
        } while (total > 0);

        System.out.println(sb);


    }
}
