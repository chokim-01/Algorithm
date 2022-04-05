import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

class Main {
	static int N;
	static long B;

	static LinkedList<int[][]> list;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		B = sc.nextLong();
		int[][] now = new int[N][N];
		list = new LinkedList<>();
		String bin =Long.toBinaryString(B);
		
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				now[i][j] = sc.nextInt()%1000;

		list.add(now);
		list.add(now); // 1부터 시작
		
		
		for(int i = 2;i<=bin.length();i++) {
			now = calc(now,now);
			list.add(now);
		}
		// 단위행렬
		int[][] ans = new int[N][N];
		for(int i = 0;i<N;i++)
			ans[i][i] = 1;
		
		for(int i = bin.length()-1;i>=0;i--) {
			if(bin.charAt(i) == '0')
				continue;
			ans = calc(list.get(bin.length()-i),ans);
		}
		
		print(ans);
		
	}
	static void print(int[][] ans) {
		StringBuffer sb = new StringBuffer();
		for(int i = 0;i<N;i++) {
			for(int j = 0;j<N;j++) {
				sb.append(ans[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	

	static int[][] calc(int[][] input, int[][] input2) {
		int[][] res = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					res[i][j] += (input[i][k] * input2[k][j]);
				}
				res[i][j] = res[i][j] % 1000;
			}
		}

		return res;
	}

}
// 10
// 2*5
// 1000
// 1,2,4,8,16,32,64,128,256, 512

// 1 2 3  |  1 2 3
// 4 5 6  |  4 5 6
// 7 8 9  |  7 8 9

// 00일때 00*00 + 00*01+00*02
// 01일때 01*