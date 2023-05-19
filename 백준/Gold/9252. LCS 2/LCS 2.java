import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		char[] a = (" " + sc.next()).toCharArray();
		char[] b = (" " + sc.next()).toCharArray();

		int[][] arr = new int[a.length][b.length];

		for (int i = 1; i < a.length; i++)
			for (int j = 1; j < b.length; j++)
				if (a[i] == b[j])
					arr[i][j] = arr[i - 1][j - 1] + 1;
				else
					arr[i][j] = Math.max(arr[i - 1][j], arr[i][j - 1]);

		System.out.println(arr[a.length-1][b.length-1]);
		StringBuilder ans = new StringBuilder();
		int i = a.length - 1;
		int j = b.length - 1;
		while (i > 0 && j > 0) {
			if (a[i] == b[j]) {
				ans.append(a[i]);
				i--;
				j--;
				continue;
			}
			if (arr[i - 1][j] > arr[i][j - 1])
				i--;
			else
				j--;

		}
		System.out.println(ans.reverse());
	}
}
