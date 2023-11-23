import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();

		int tot = 60 * a + b + c;
		System.out.println((tot / 60) % 24 + " " + tot % 60);
	}
}