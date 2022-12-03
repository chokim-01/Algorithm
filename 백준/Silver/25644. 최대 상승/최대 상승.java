import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br.readLine();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int ans = 0;
		int min = 1111111111;
		while (st.hasMoreTokens()) {
			int n = Integer.parseInt(st.nextToken());
			ans = ans < n - min ? n - min : ans;
			min = min < n ? min : n;
		}
		System.out.println(ans);
	}
}