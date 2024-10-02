import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static int N, K;
    static int[] se, num, save;
    static ArrayList<Integer>[] link;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input(br);
        makeLink();

        if(!go()) {
            System.out.println(-1);
            return;
        }

        Stack stack = new Stack();
        while (se[1] != se[0]) {
            stack.add(se[1]);
            se[1] = save[se[1]];
        }
        stack.add(se[0]);

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty())
            sb.append(stack.pop()).append(" ");
        System.out.println(sb);
    }

    static boolean go() {
        Arrays.fill(save, -1);
        Queue<Integer> q = new ArrayDeque<>();
        q.add(se[0]);

        while (!q.isEmpty()) {
            int now = q.poll();
            if (now == se[1])
                return true;
            for (int next : link[now]) {
                if(save[next] != -1)
                    continue;
                save[next] = now;
                q.add(next);
            }
        }
        return false;
    }

    static void makeLink() {
        int[] expo = IntStream.rangeClosed(1, K).map(x -> (int) Math.pow(2, x - 1)).toArray();

        for (int i = 1; i <= N; i++) {
            outer:
            for (int j = i + 1; j <= N; j++) {
                boolean flag = false;
                for (int k = 0; k < K; k++) {
                    if ((expo[k] & num[i]) != (expo[k] & num[j])) {
                        if (flag)
                            continue outer;
                        flag = true;
                    }
                }
                link[i].add(j);
                link[j].add(i);
            }
        }
    }

    static void input(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        num = new int[N + 1];
        save = new int[N + 1];
        link = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            num[i] = Integer.parseInt(br.readLine(), 2);
            link[i] = new ArrayList<>();
        }
        st = new StringTokenizer(br.readLine());
        se = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
    }
}
