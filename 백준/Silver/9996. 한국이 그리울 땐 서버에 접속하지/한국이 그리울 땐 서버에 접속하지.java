import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder ans = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		String s = br.readLine();
		s = s.replaceAll("\\*", "[a-z]*");
		Pattern p = Pattern.compile(s);
		while (N-- > 0)
			ans.append(p.matcher(br.readLine()).matches() ? "DA" : "NE").append("\n");

		System.out.println(ans);
	}
}