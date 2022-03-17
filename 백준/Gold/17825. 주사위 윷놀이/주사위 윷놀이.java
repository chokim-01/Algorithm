import java.util.Arrays;
import java.util.Scanner;

public class Main {

	static int order[];
	static int[] go;
	static int[] score;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		order = new int[10];

		go = new int[33];
		score = new int[33];
		// 20까지는 기본
		// 21부터 31까지는 지정.
		for (int i = 0; i <= 32; i++) { // 20이 40점
			go[i] = i + 1;
			score[i] = i * 2;
		}
		for (int i = 0; i < 10; i++)
			order[i] = sc.nextInt();

		// 13 16 19 : 21 22 23
		// 28 27 26 : 24 25 26
		// 22 24 : 27 28
		// 30 35 : 29 30
		// 25 : 31

		go[23] = 31;
		go[26] = 31;
		go[28] = 31;
		go[31] = 29;
		go[30] = 20;
		go[20] = 32; // 도착지점
		go[32] = 0;

		score[21] = 13;
		score[22] = 16;
		score[23] = 19;
		score[24] = 28;
		score[25] = 27;
		score[26] = 26;
		score[27] = 22;
		score[28] = 24;
		score[29] = 30;
		score[30] = 35;
		score[31] = 25;
		score[32] = 0;

		ans = 0;
		// if go == 20 end
		dfs(new int[4], new int[4], 0);

		System.out.println(ans);

	}

	static int findDest(int x, int count) {
		if (count == 0)
			return x;
		if(x == 20)
			return 32;

		return findDest(go[x], count - 1);
	}

	static int ans;

	// 말의 위치, 말의 지금까지 합한 점수, 명령 index
	static void dfs(int[] horse, int[] horseScore, int o) {
		if (o == 10) {
			int cnt = 0;
			for (int i = 0; i < 4; i++)
				cnt += horseScore[i];
			ans = Math.max(ans, cnt);
			return;
		}
		for (int i = 0; i < 4; i++) {
			// 도착 칸에 있는 경우는 하지 않음.
			if (horse[i] == 32)
				continue;
			int c = order[o];
			int tmp = horse[i];

			if (horse[i] == 5) {
				horse[i] = 21;
				c = c - 1;
			} else if (horse[i] == 10) {
				horse[i] = 27;
				c = c - 1;
			} else if (horse[i] == 15) {
				horse[i] = 24;
				c = c - 1;
			}

			boolean flag = true;
			horse[i] = findDest(horse[i], c);
			for (int a = 0; a < 4; a++) {
				if(horse[i] == 32)
					break;
				if (a == i)
					continue;
				if (horse[a] == horse[i]) {
					flag = false;
					break;
				}
			}
			horseScore[i] += score[horse[i]];
			if (flag)
				dfs(horse, horseScore, o + 1);
			horseScore[i] -= score[horse[i]];
			horse[i] = tmp;
		}

	}
}