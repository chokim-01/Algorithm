import java.util.*;

public class Main {
	static int N;
	static Stack<List<Integer>> ans;

	public static void dfs(int sum, List<Integer> choice) {
		if (sum == N) {
			ans.add(choice);
			return;
		}
		int maxv = (choice.size() == 0) ? N : choice.get(choice.size() - 1);
		for (int i = 1; i <= maxv; i++) {
			if (sum + i <= N) {
				List<Integer> nv = new ArrayList<>(choice);
				nv.add(i);
				dfs(sum + i, nv);
			} else
				break;
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