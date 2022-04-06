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
	static int N, multi;
	static int[] nums;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		nums = new int[10];

		int start = 1;
		int end = N;
		multi = 1;

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

		for (int i = 0; i < 10; i++) {
			System.out.print(nums[i] + " ");
		}
	}

	private static void solve(int s) {
        // TODO Auto-generated method stub
        while(s>0) {
            nums[s%10]+=multi;
            s/=10;
        }
    }
}