import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] nums = new int[N][2];
		for (int i = 0; i < N; i++) {
			nums[i][0] = Integer.parseInt(br.readLine());
			nums[i][1] = i;
		}

		Arrays.sort(nums, (o1, o2) -> o1[0] - o2[0]);
		ArrayList<ArrayList<Integer>> list = new ArrayList<>();
		ArrayList<Integer> save;

		boolean[] v = new boolean[N];
		for (int i = 0; i < N; i++) {
			if (v[i])
				continue;
			save = new ArrayList<>();
			int now = i;
			while (!v[now]) {
				save.add(nums[now][0]);
				v[now] = true;
				now = nums[now][1];
			}
			if (save.size() == 1)
				continue;
			list.add(save);
		}
		int ans = 0;
		for (ArrayList<Integer> l : list) {
			int size = l.size();
			for (int n : l)
				ans += n;
			ans += Math.min(l.get(0) * (size - 2), nums[0][0] * (size + 1) + l.get(0));
		}
		System.out.println(ans);

	}
}