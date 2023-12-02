import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		StringBuilder ans = new StringBuilder();
		Pattern p = Pattern.compile("(100+1+|01)+");
		while (n-- > 0) {
			Matcher m = p.matcher(sc.next());
			ans.append(m.matches() ? "YES" : "NO").append("\n");
		}
		System.out.println(ans);
	}
}