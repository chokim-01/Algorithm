import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int N, M;
    static HashSet<String> set;
    static Queue<String> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input(br);

        System.out.println(solve());
    }

    static int solve() {
        int ret = 0;
        while (!q.isEmpty())
            ret += set.contains(q.poll()) ? 1 : 0;

        return ret;
    }

    static void input(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        set = new HashSet<>();
        q = new ArrayDeque<>();
        
        while (N-- > 0)
            set.add(br.readLine());
        while (M-- > 0)
            q.add(br.readLine());

    }
}
