import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int size;
	static char[] parse;
	static ArrayList<String> ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = 1;
		while (true) {
			String line = br.readLine();
			if (line.equals("#"))
				break;
			parse = line.toCharArray();
			// 가장 마지막
			sb.append("Problem ").append(tc++).append("\n");
			while (true) {
				int[] number = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
				if (number.length == 1 && number[0] == 0)
					break;
				size = Integer.MAX_VALUE;
				ans = new ArrayList<>();
				dfs(0, new ArrayList<>(), number);
				Collections.sort(ans, Collections.reverseOrder());
				sb.append(ans.get(0)).append("\n");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static void dfs(int index, ArrayList<Integer> choice, int[] number) {
		if (index == number.length) {
			int[] ne = choice.stream().mapToInt(a -> a).toArray();
			StringBuilder sb2 = new StringBuilder();

			if (choice.size() < size) {
				ans = new ArrayList<>();
				size = choice.size();
			}
			for (int n : ne)
				sb2.append(parse[n]);

			if (choice.size() == size)
				ans.add(sb2.toString());
			return;
		}
		// choice cnt 1
		choice.add(number[index]);
		dfs(index + 1, choice, number);
		choice.remove(choice.size() - 1);
		// choice cnt 2
		if (index < number.length - 1) {
			int next = number[index] * 10 + number[index + 1];
			if (next < parse.length) {
				choice.add(next);
				dfs(index + 2, choice, number);
				choice.remove(choice.size() - 1);
			}
		}
	}
}