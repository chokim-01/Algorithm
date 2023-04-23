import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		String ans = ">";
		if (a < b)
			ans = "<";
		else if (a == b)
			ans = "==";
		System.out.println(ans);
	}
}