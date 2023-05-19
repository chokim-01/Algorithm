import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

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

//		for (int i = 0; i < a.length; i++)
//			System.out.println(Arrays.toString(arr[i]));
		System.out.println(arr[a.length - 1][b.length - 1]);

	}
}
