import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int res = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		int[] map = new int[N];
		getRes(map, 0, N);

		System.out.println(res);

	}

	// N * N 맵에 퀸 N개를 놔야 함.
	static void getRes(int[] map, int idx, int N) {
		if (idx == N) { // 갯수만큼 놓음.
			res += 1;
			return;
		}

		for (int i = 0; i < N; i++) {
			map[idx] = i;
			// 놓고 지나감. 다음 칸으로.
			boolean flag = true;
			for (int a = 0; a < idx; a++) {
				if (map[a] == map[idx] || idx - a == Math.abs(map[idx] - map[a])) {
					flag = false;
					break;
				}
			}
			if (flag) {
				getRes(map, idx + 1, N);
			}
		}

	}

}
