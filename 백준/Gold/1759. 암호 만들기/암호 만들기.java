import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int L, C;
	static StringBuilder ans;
	static Character[] alphabets;
	static boolean[] alphaMJ;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		alphabets = br.readLine().replaceAll(" ", "").chars().sorted().mapToObj(c -> (char) c)
				.toArray(Character[]::new);
		alphaMJ = new boolean[26];
		alphaMJ[0] = true;
		alphaMJ[4] = true;
		alphaMJ[8] = true;
		alphaMJ[14] = true;
		alphaMJ[20] = true;
		ans = new StringBuilder();

		dfs(-1, 0, new char[L], 0, 0);
		System.out.println(ans);
	}

	static void dfs(int index, int count, char[] choice, int moeum, int jaeum) {
		if (count == L) {
			if (moeum < 1 || jaeum < 2)
				return;
			ans.append(String.valueOf(choice)).append("\n");
			return;
		}
		for (int i = index + 1; i < alphabets.length; i++) {
			int ind = alphabets[i] - 97;
			choice[count] = alphabets[i];
			dfs(i, count + 1, choice, moeum + (alphaMJ[ind] ? 1 : 0), jaeum + (alphaMJ[ind] ? 0 : 1));
		}
	}
}