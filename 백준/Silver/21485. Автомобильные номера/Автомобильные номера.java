import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static HashSet<String> charList;
	static HashSet<String> numberList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String str = br.readLine();
		boolean[] chk = new boolean[str.length()];
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c >= '0' && c <= '9')
				chk[i] = true;
		}
		charList = new HashSet<>();
		numberList = new HashSet<>();

		char[] chars = str.replaceAll("[^A-Z]", "").toCharArray(); // XFK
		char[] numbers = str.replaceAll("[^0-9]", "").toCharArray(); // [0, 4, 3, 2, 5]
		dfs(0, chars, new char[chars.length], true);
		dfs(0, numbers, new char[chars.length], false);

		StringBuilder sb = new StringBuilder();
		sb.append(charList.size() * numberList.size()).append("\n");
		for (String s : charList) {
			char[] ans = new char[str.length()];
			int index = 0;
			for (int i = 0; i < str.length(); i++)
				if (!chk[i])
					ans[i] = s.charAt(index++);
			
			for (String s2 : numberList) {
				index = 0;
				for (int i = 0; i < str.length(); i++) 
					if(chk[i])
						ans[i] = s2.charAt(index++);
				
				sb.append(String.valueOf(ans)).append("\n");
			}
		}
		System.out.println(sb);
	}

	static void dfs(int index, char[] array, char[] choice, boolean flag) {
		if (index == choice.length) {
			if (flag)
				charList.add(String.valueOf(choice));
			else
				numberList.add(String.valueOf(choice));
			return;
		}
		for (int i = 0; i < choice.length; i++) {
			if (array[i] == '\0')
				continue;
			choice[index] = array[i];
			char tmp = array[i];
			array[i] = '\0';
			dfs(index + 1, array, choice, flag);
			array[i] = tmp;
			choice[index] = '\0';

		}
	}
}