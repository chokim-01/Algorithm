import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int cases = Integer.parseInt(br.readLine());
        int x = 0;
        ArrayList<Integer> list;

        for (int c = 1; c <= cases; c++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            x = Integer.parseInt(st.nextToken());

            list = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens())
                list.add(Integer.parseInt(st.nextToken()));

            Collections.sort(list);

            int ans = 0, l = 0, r = list.size() - 1;
            while (l < r) {
                ans++;
                if (list.get(l) + list.get(r) <= x)
                    l++;
                r--;
            }
            ans += l == r ? 1 : 0;

            sb.append("Case #").append(c).append(": ").append(ans).append("\n");
        }
        System.out.println(sb);

    }
}
