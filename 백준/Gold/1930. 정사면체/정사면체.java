import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

	static int[][] rotate = { { 0, 1, 2, 3 }, { 1, 0, 3, 2 }, { 2, 1, 3, 0 }, { 3, 1, 0, 2 } };
	static int[][] change = { { 1, 2, 3 }, { 3, 1, 2 }, { 2, 3, 1 } };

	/*
	 * static int[] front = { 1, 0, 3, 2 }; static int[] left = { 2, 1, 3, 0 };
	 * static int[] right = { 3, 1, 0, 2 };
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int[] dice = new int[4];
			int[] target = new int[4];
			for (int i = 0; i < 4; i++)
				dice[i] = Integer.parseInt(st.nextToken());
			for (int i = 0; i < 4; i++)
				target[i] = Integer.parseInt(st.nextToken());

			boolean flag = false;

			for (int i = 0; i < 4; i++) {
				if (dice[rotate[i][0]] != target[0])
					continue;
				// 0번째 일치
				// 1,2,3번째를 돌려가며 정답을 찾음.
				int[] diceNext = new int[4];
				for(int d = 0;d<4;d++)
					diceNext[d] = dice[rotate[i][d]];
				if(equalFind(diceNext,target)) {
					flag = true;
					break;
				}
			}
			System.out.println(flag ? 1 : 0);
		}
	}
	// 맞는 다이스 찾기
	static boolean equalFind(int[] dice, int[] target) {
		// 3번을 미룸
		// 123, 312, 231

		for (int c = 0; c < 3; c++) {
			int index = 0;
			for (; index < 3; index++)
				if (dice[change[c][index]] != target[index + 1])
					break;
			if (index == 3)
				return true;
		}

		return false;
	}
}
