import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
	static int N;
	static char[] ineq;
	static StringBuilder ans;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		sc.nextLine();
		ineq = ("-" + sc.nextLine()).replaceAll("\\s", "").toCharArray();
		ans = new StringBuilder();

		int[] arr = IntStream.range(0, 10).map(i->9-i).toArray();
		dfs(0, arr, new boolean[10], new int[N + 1]);
		arr = IntStream.range(0, 10).toArray();
		dfs(0, arr, new boolean[10], new int[N + 1]);
		System.out.println(ans);
	}

	static boolean dfs(int d, int[] arr, boolean[] v, int[] choice) {
		if (d == N + 1) {
			for (int c : choice)
				ans.append(c);
			ans.append("\n");
			return true;
		}
		boolean f = false;
		for (int i = 0; i < 10; i++) {
			if (v[i])
				continue;
			v[i] = true;
			choice[d] = arr[i];
			if (ineq[d] == '-')
				f = dfs(d + 1, arr, v, choice);
			else if (ineq[d] == '<') {
				if (choice[d - 1] < choice[d])
					f = dfs(d + 1, arr, v, choice);
			} else if (choice[d - 1] > choice[d])
				f = dfs(d + 1, arr, v, choice);
			if(f)
				return true;
			v[i] = false;
		}
		return false;
	}
}