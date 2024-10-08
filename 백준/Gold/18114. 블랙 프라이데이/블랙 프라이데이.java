import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    static int N, C;
    static int[] thing;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        thing = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(thing);
        System.out.println(solve() ? 1 : 0);

    }

    static boolean solve() {
        for (int w : thing)
            if (w == C)
                return true;

        for (int i = 0; i < N; i++) {
            if (thing[i] > C)
                break;
            if (choice(i + 1, C - thing[i]))
                return true;
        }

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (thing[i] + thing[j] > C)
                    break;
                if (choice(j + 1, C - thing[i] - thing[j]))
                    return true;
            }
        }

        return false;
    }

    static boolean choice(int idx, int c) {
        int l = idx;
        int r = thing.length;

        while (l < r) {
            int mid = (l + r) >> 1;
            if (thing[mid] > c)
                r = mid;
            else if (thing[mid] < c)
                l = mid + 1;
            else
                return true;
        }
        return false;
    }
}
