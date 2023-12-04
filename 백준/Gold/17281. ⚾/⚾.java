import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int[][] hitterHit;
	static int maxScore = 0;

	static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        
		N = sc.nextInt();
		hitterHit = new int[N][9];
		for (int inning = 0; inning < N; inning++)
			for (int i = 0; i < 9; i++)
				hitterHit[inning][i] = sc.nextInt();
		per(new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 }, 0);
		System.out.println(maxScore);
	}

	static int calcScore(int[] hitters) {
		boolean[] point = new boolean[3];
		int out = 0;
		int inning = 0;
		int score = 0;

		int i = 0;
		while (true) {
			if (hitterHit[inning][hitters[i]] == 0) {
				out += 1;
				if (out >= 3) {
					Arrays.fill(point, false);
					inning += 1;
					if (inning >= N)
						break;
					out = 0;
					i += 1;
					if (i >= 9)
						i = 0;
					continue;
				}
			}
			else if (hitterHit[inning][hitters[i]] == 1) {
				if (point[2]) {
					point[2] = false;
					score += 1;
				}
				if (point[1]) {
					point[2] = true;
					point[1] = false;
				}
				if (point[0]) {
					point[1] = true;
					point[0] = false;
				}
				point[0] = true;
			}
			else if (hitterHit[inning][hitters[i]] == 2) {
				if (point[2]) {
					score += 1;
					point[2] = false;
				}
				if (point[1]) {
					score += 1;
					point[1] = false;
				}
				if (point[0]) {
					point[2] = true;
					point[0] = false;
				}
				point[1] = true;
			}
			else if (hitterHit[inning][hitters[i]] == 3) {
				for (int a = 0; a < 3; a++) {
					if (point[a]) {
						score += 1;
						point[a] = false;
					}
				}
				point[2] = true;
			}
			// 홈런
			else {
				for (int a = 0; a < 3; a++) {
					if (point[a]) {
						score += 1;
						point[a] = false;
					}
				}
				score += 1;
			}
			i += 1;
			if (i >= 9)
				i = 0;
		}
		return score;
	}
	static void per(int[] hitters, int depth) {
		if (depth == 9) {
			if (hitters[3] == 0) {
				int score = calcScore(hitters);
				if (score > maxScore)
					maxScore = score;
			}
			return;
		}
		for (int i = depth; i < 9; i++) {
			swap(hitters, depth, i);
			per(hitters, depth + 1);
			swap(hitters, depth, i);
		}
	}
	static void swap(int[] hitters, int a, int b) {
		int tmp = hitters[a];
		hitters[a] = hitters[b];
		hitters[b] = tmp;
	}
}