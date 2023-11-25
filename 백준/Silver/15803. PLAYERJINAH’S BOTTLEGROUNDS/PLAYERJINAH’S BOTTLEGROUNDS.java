import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		double x1 = sc.nextDouble();
		double y1 = sc.nextDouble();
		double x2 = sc.nextDouble();
		double y2 = sc.nextDouble();
		double x3 = sc.nextDouble();
		double y3 = sc.nextDouble();

		boolean f = false;
		if (x1 == x2 && x2 == x3)
			f = true;
		else if (y1 == y2 && y2 == y3)
			f = true;
		else if (calc(x1, y1, x2, y2) == calc(x2, y2, x3, y3))
			f = true;

		System.out.println(f ? "WHERE IS MY CHICKEN?" : "WINNER WINNER CHICKEN DINNER!");
	}

	static double calc(double x1, double y1, double x2, double y2) {
		return (x2 - x1) / (y2 - y1);

	}
}