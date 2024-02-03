import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;

public class Main {
	static ArrayList<Integer>[][] link;
	static HashMap<ArrayList<Integer>, Integer> map;
	static int c;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new HashMap<>();
		c = 0;

		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			String a = br.readLine();
			String b = br.readLine();
			if (func(a, b))
				System.out.println(1);
			else
				System.out.println(0);
		}
	}

	static boolean func(String a, String b) {
		link = new ArrayList[2][1501];
		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 1501; j++)
				link[i][j] = new ArrayList<>();

		makeLink(a, 0);
		makeLink(b, 1);
		return dfs(1, 0) == dfs(1, 1);
	}

	static int dfs(int now, int v) {
		ArrayList<Integer> list = new ArrayList<>();
		for (int next : link[v][now])
			list.add(dfs(next, v));

		Collections.sort(list);
		if (!map.containsKey(list))
			map.put(list, ++c);
		return map.get(list);
	}

	static void makeLink(String s, int v) {
		Stack<Integer> stack = new Stack<>();
		int cnt = 1;
		stack.push(cnt);
		for (char c : s.toCharArray()) {
			if (c == '0') {
				link[v][stack.peek()].add(++cnt);
				stack.push(cnt);
			} else
				stack.pop();
		}
	}
}