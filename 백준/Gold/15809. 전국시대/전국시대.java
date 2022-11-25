import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	static int[] army;
	static int[] alliance;
	static boolean[] country;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		army = new int[N + 1];
		alliance = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			alliance[i] = i;
			army[i] = Integer.parseInt(br.readLine());
		}

		for (int i = 0, o, p, q; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			o = Integer.parseInt(st.nextToken());
			p = Integer.parseInt(st.nextToken());
			q = Integer.parseInt(st.nextToken());

			if (o == 1) // 연합
				union(p, q);
			else // 전쟁
				fight(p, q);

		}
		ArrayList<Integer> country = new ArrayList<>();
		for (int i = 0; i < army.length; i++)
			if (army[i] != 0)
				country.add(army[i]);
		Collections.sort(country);

		System.out.println(country.size());
		for (int i = 0; i < country.size(); i++)
			System.out.print(country.get(i) + " ");

	}

	static void fight(int x, int y) {
		x = find(x);
		y = find(y);
		if (army[x] == army[y]) { // destruc
			army[x] = 0;
			army[y] = 0;
		} else if (army[x] < army[y]) {// y win
			army[y] -= army[x];
			army[x] = 0;
		} else {// x win
			army[x] -= army[y];
			army[y] = 0;
		}
		union(x, y);

	}

	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if (x == y)
			return;
		if (x < y) {
			alliance[y] = x;
			army[x] += army[y];
			army[y] = 0;
		} else {
			alliance[x] = y;
			army[y] += army[x];
			army[x] = 0;
		}
	}

	static int find(int x) {
		if (x == alliance[x])
			return x;
		return alliance[x] = find(alliance[x]);
	}
}