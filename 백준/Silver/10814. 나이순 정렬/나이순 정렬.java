import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		String[][] b = new String[a][2];
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < a; i++) {
			b[i][0] = sc.next();
			b[i][1] = sc.next();
		}
		Arrays.sort(b,
				(e1, e2) -> Integer.parseInt(e1[0]) - Integer.parseInt(e2[0]));
		for (int i = 0; i < a; i++) {
			sb.append(b[i][0]).append(" ").append(b[i][1]).append("\n");
		}
		System.out.println(sb);
	}
}