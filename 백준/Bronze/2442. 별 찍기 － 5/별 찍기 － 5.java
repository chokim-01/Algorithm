import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();

		StringBuilder ans = new StringBuilder();
		for (int i = 1; i <= a; i++) {
			for (int k = 0; k < a - i; k++)
				ans.append(" ");
			for (int j = 0; j < 2 * i - 1; j++)
				ans.append("*");
			ans.append("\n");
		}
		System.out.println(ans);
	}
}