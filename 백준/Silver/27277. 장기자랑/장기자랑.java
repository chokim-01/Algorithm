import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		ArrayList<Integer> list = new ArrayList<>();
		while (N-- > 0)
			list.add(Integer.parseInt(st.nextToken()));
		Collections.sort(list);

		int ans = 0;
		int l = 0;
		int r = 0;
		Deque<Integer> dq = new ArrayDeque<>(list);
		while (dq.size() > 1) {
			l = dq.pollFirst();
			r = dq.pollLast();
			ans += r - l;
		}
		if (dq.isEmpty())
			ans += l;
		else if (dq.size() == 1)
			ans += dq.poll();
		System.out.println(ans);
	}
}
