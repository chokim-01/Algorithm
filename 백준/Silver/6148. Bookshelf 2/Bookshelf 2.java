import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Main {
	static int N, B, ans;
	static int[] cow;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		ans = Integer.MAX_VALUE;
		cow = new int[B];
		for (int i = 0; i < N; i++)
			cow[i] = Integer.parseInt(br.readLine());
		dfs(0, 0);
		System.out.println(ans);
	}

	static void dfs(int index, int val) {
		if (val >= B) {
			ans = ans < val - B ? ans : val - B;
			return;
		}
		for (int i = index; i < N; i++)
			dfs(i + 1, val + cow[i]);
	}
}