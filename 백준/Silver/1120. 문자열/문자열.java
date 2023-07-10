import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int ans = 50;
		String a = sc.next();
		String b = sc.next();

		for (int i = 0, c; i <= b.length() - a.length(); i++) {
			c = 0;
			for (int j = 0; j < a.length(); j++)
				if (a.charAt(j) != b.charAt(i + j))
					c++;
			ans = ans < c ? ans : c;
			if(ans == 0)
				break;
		}
		System.out.println(ans);
	}
}