import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NavigableSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder ans = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		TreeSet<String> set = new TreeSet<>();
		while (N-- > 0) {
			st = new StringTokenizer(br.readLine());
			String a = st.nextToken();
			if (set.contains(a))
				set.remove(a);
			else
				set.add(a);
		}
		NavigableSet<String> dset = set.descendingSet();
		for (String d : dset)
			ans.append(d).append("\n");
		System.out.println(ans);
	}
}