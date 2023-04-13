import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();

		LinkedList<Integer> list = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			list.add(i + 1);
		}
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		for (int i = 0; i < N; i++) {
			int k = K - 1;
			for (int j = 0; j < k; j++) {
				list.add(list.size()-1, list.remove(0));
			}
			// 빼기
			int number = list.remove(0);
			if (list.size() == 0)
				sb.append(number);
			else
				sb.append(number + ", ");
		}
		sb.append(">");
		System.out.println(sb);

	}

}
