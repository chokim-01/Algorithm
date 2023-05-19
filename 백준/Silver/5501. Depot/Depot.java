import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static ArrayList<Integer>[] depot;
	static int N;
	static int[] array;
	static StringBuilder sb;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		depot = new ArrayList[N + 1];
		depot[N] = new ArrayList<>();
		sb = new StringBuilder();
		
		for (int i = 0; i < N; ++i) {
			depot[i] = new ArrayList<>();
			int n = sc.nextInt();
			while (n-- > 0) {
				int a = sc.nextInt();
				depot[i].add(a);
			}
		}

		array = new int[20];
		dfs(-1, -1, 0);
		System.out.println(sb);
	}

	public static void dfs(int beforeNum, int beforeRow, int index) {
		if (beforeNum < 0 && depot[0].isEmpty()) {
			
			for (int i = index - 1; i >= 0; --i)
				sb.append(array[i] + " ");
			sb.append("\n");
			return;
		}

		if (beforeNum < 0) {
			for (int i = 0; !depot[i].isEmpty() && i < N; i++) {
				if (depot[i].size() == depot[i + 1].size()) {
					continue;
				}
				int row = i;
				int val = depot[i].get(depot[i].size() - 1);
				depot[i].remove(depot[i].size() - 1);
				dfs(val, row, index);
				depot[i].add(val);
			}
			return;
		}

		if (beforeRow == 0) {
			array[index] = beforeNum;
			dfs(-1, -1, index + 1);
			return;
		}

		int row = beforeRow - 1;
		for (int i = 0; i < depot[row].size() && depot[row].get(i) < beforeNum; ++i) {
			if (i + 1 < depot[row].size() && depot[row].get(i + 1) < beforeNum) {
				continue;
			}
			int val = depot[row].get(i);
			depot[row].set(i, beforeNum);
			dfs(val, row, index);
			depot[row].set(i, val);
		}
	}
}
