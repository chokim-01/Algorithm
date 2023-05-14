import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int ans = 1;
		String a = br.readLine();
		String b = br.readLine();
		int index = 0;
		for (int i = 0; i < b.length(); i++) {
			if (a.contains(b.substring(index, i + 1)))
				continue;
			ans++;
			index = i;
		}
		System.out.println(ans);
	}
}