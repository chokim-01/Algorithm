import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		Queue<Integer> q = new ArrayDeque<>();
		for (int i = 1; i <= N; i++)
			q.add(i);

		int M = Integer.parseInt(br.readLine());

		while (M-- > 0) {
			int num = Integer.parseInt(br.readLine());
			int index = 1;
			int size = q.size();
			while (size-- > 0) {
				int now = q.poll();

				if (index++ % num == 0)
					continue;
				q.add(now);
			}
		}
		while(!q.isEmpty())
			sb.append(q.poll()+"\n");
		System.out.println(sb.toString());
	}
}