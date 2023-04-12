import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i <= N; i++)
			q.add(i);

		StringBuilder sb = new StringBuilder();
		sb.append("<");
		while (N-- > 0) {
			int k = K - 1;
			while (k-- != 0) {
				q.add(q.poll());
			}
			if (q.size() == 1) {
				sb.append(q.poll());
			} else
				sb.append(q.poll() + ", ");
		}
		sb.append(">");
		System.out.println(sb);

	}
}
