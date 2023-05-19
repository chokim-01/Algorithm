import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		char[] a = (" " + sc.next()).toCharArray();
		char[] b = (" " + sc.next()).toCharArray();
		char[] c = (" " + sc.next()).toCharArray();

		int[][][] arr = new int[a.length][b.length][c.length];

		for (int i = 1; i < a.length; i++)
			for (int j = 1; j < b.length; j++)
				for (int k = 1; k < c.length; k++)
					if (a[i] == b[j] && b[j] == c[k])
						arr[i][j][k] = arr[i - 1][j - 1][k - 1] + 1;
					else
						arr[i][j][k] = Math.max(arr[i - 1][j][k], Math.max(arr[i][j - 1][k], arr[i][j][k - 1]));

		System.out.println(arr[a.length - 1][b.length - 1][c.length - 1]);
		StringBuilder ans = new StringBuilder();
		int i = a.length - 1;
		int j = b.length - 1;
		int k = c.length - 1;
		while (i > 0 && j > 0 && k > 0) {
			if (a[i] == b[j] && a[i] == c[k]) {
				ans.append(a[i]);
				i--;
				j--;
				k--;
				continue;
			}
			// i를 옮기냐 j를 옮기냐 k를 옮기냐
			if (arr[i - 1][j][k] > arr[i][j - 1][k] && arr[i - 1][j][k] > arr[i][j][k - 1]) {

				i--;
			} else if (arr[i - 1][j][k] <= arr[i][j - 1][k] && arr[i - 1][j][k] > arr[i][j][k - 1])
				j--;
			else
				k--;

		}
//		System.out.println(ans.reverse());
	}
}
