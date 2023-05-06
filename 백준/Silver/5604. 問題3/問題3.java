import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	static int N;
	static Stack<List<Integer>> ans;

	public static void dfs(int sum, List<Integer> choice) {
		if (sum == N) {
			ans.add(new ArrayList<>(choice));
			return;
		}

		int max = choice.isEmpty() ? N : choice.get(choice.size() - 1);
		int range = Math.min(max, N - sum);
		for (int i = 1; i <= range; i++) {
			choice.add(i);
			dfs(sum + i, choice);
			choice.remove(choice.size() - 1);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		ans = new Stack<>();

		dfs(0, new ArrayList<>());

		StringBuilder sb = new StringBuilder();
		while (!ans.isEmpty()) {
			for (int a : ans.pop())
				sb.append(a + " ");
			sb.append("\n");
		}
		System.out.println(sb);
	}
}