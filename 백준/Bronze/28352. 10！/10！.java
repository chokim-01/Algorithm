import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] a = new int[18];
		a[10] = 6;
		for (int i = 11; i <= N; i++)
			a[i] = a[i - 1] * i;
		System.out.println(a[N]);
	}
}