import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N;
	static int[][] map;
	static int total;
	static int ans;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		map = new int[N][N];

		total = 0;
		ans = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
				total += map[i][j];
			}
		}

		for (int i = 1; i < N - 1; i++) {
			for (int j = 1; j < N - 1; j++) {
				for (int a = 1; a < N; a++) {
					for (int b = 1; b < N; b++) {
						if (b + j >= N || a + b + i >= N || j - a < 0)
							break;
						divideMap(i, j, a, b);
					}
				}
			}
		}
		System.out.println(ans);

	}

	static void divideMap(int x, int y, int d1, int d2) {
		// 1
		int[] nums = new int[5];
		int ny = y;
		for (int i = 0; i < x + d1; i++) {
			for (int j = 0; j <= ny; j++) {
				nums[0] += map[i][j];
				nums[4] += map[i][j];
			}
			if (x - 1 <= i)
				ny -= 1;
		}
		// 2
		ny = y + 1;
		for (int i = 0; i <= x + d2; i++) {
			for (int j = ny; j < N; j++) {
				nums[1] += map[i][j];
				nums[4] += map[i][j];
			}
			if (x <= i)
				ny += 1;
		}
		// 3
		ny = y - d1; // 시작지점의 끝
		for (int i = x + d1; i < N; i++) {
			for (int j = 0; j < ny; j++) {
				nums[2] += map[i][j];
				nums[4] += map[i][j];
			}
			if (x <= i && i < x+d1+d2)
				ny += 1;
		}
		// 4
		ny = y + d2;
		for (int i = x + d2 + 1; i < N; i++) {
			for (int j = ny; j < N; j++) {
				nums[3] += map[i][j];
				nums[4] += map[i][j];
			}
			if(i <= x + d1 + d2)
			ny -= 1;
		}
		nums[4] = total - nums[4];
		Arrays.sort(nums);

		ans = Math.min(ans, nums[4] - nums[0]);

	}
}