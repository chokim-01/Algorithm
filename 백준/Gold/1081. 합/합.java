import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Main {
	static long multi;
	static long[] nums;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		System.out.println(getCount(M) - getCount(N - 1));
	}

	static long getCount(int N) {
		if (N < 0)
			return 0;
		nums = new long[10];

		multi = 1;
		int start = 1;
		int end = N;

		while (start <= end) {

			while (start % 10 != 0 && start <= end) {
				solve(start);
				start++;
			}

			if (start > end)
				break;

			while (end % 10 != 9 && start <= end) {
				solve(end);
				end--;
			}
			start /= 10;
			end /= 10;

			for (int i = 0; i < 10; i++) {
				nums[i] += (end - start + 1) * multi;
			}
			multi *= 10;
		}
		long ret = 0;
		for (int i = 1; i <= 9; i++)
			ret += i * nums[i];
		return ret;
	}

	private static void solve(int s) {
		// TODO Auto-generated method stub
		while (s > 0) {
			nums[s % 10] += multi;
			s /= 10;
		}
	}
}