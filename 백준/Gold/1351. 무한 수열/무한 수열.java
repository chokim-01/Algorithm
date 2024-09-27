import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.stream.Stream;

class Main {
    static long[] input;
    static HashMap<Long, Long> save;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = Stream.of(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        save = new HashMap<>() {{
            put(0L, 1L);
        }};

        long ans = rec(input[0]);

        System.out.println(ans);
    }

    static long rec(long N) {
        if (save.containsKey(N))
            return save.get(N);

        long ret = rec(N / input[1]) + rec(N / input[2]);
        return save.computeIfAbsent(N, key -> ret);
    }
}