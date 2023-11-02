import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
	static int N, K;
	static List<String> strs;
	static HashSet<Character> aList;
	static HashSet<Character> exist;
	static ArrayList<Character> alphabets;

	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		if (K < 5) {
			System.out.println("0");
			return;
		}
		ans = 0;
		K -= 5;
		aList = new HashSet<>();
		exist = new HashSet<>(Arrays.asList('a', 'n', 't', 'c', 'i'));
		alphabets = new ArrayList<>();

		strs = Stream.iterate(0, i -> i + 1).limit(N).map(i -> {
			try {
				String s = br.readLine().replaceAll("[antci]", "");
				aList.addAll(s.chars().mapToObj(c -> (char) c).collect(Collectors.toSet()));
				return s;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException();
			}
		}).collect(Collectors.toList());
		alphabets.addAll(aList);

		if (K >= alphabets.size()) {
			System.out.println(N);
			return;
		}

		dfs(0, 0);
		System.out.println(ans);
	}

	static void dfs(int idx, int cnt) {
		if (cnt == K) {
			int a = 0;
			outer: for (String s : strs) {
				for (int i = 0; i < s.length(); i++)
					if (!exist.contains(s.charAt(i)))
						continue outer;
				a++;
			}
			ans = a < ans ? ans : a;
			return;
		}
		if (idx == alphabets.size())
			return;

		char c = alphabets.get(idx);
		exist.add(c);
		dfs(idx + 1, cnt + 1);
		exist.remove(c);
		dfs(idx + 1, cnt);
	}
}