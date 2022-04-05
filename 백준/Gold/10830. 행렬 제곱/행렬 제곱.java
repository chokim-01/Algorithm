import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Scanner;

class Main {
	static int N;
	static long B;

	static LinkedHashMap<Long, int[][]> list;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		B = sc.nextLong();
		int[][] now = new int[N][N];
		list = new LinkedHashMap<Long, int[][]>();

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				now[i][j] = sc.nextInt()%1000;

		list.put((long) 1, now);

		long num = 2;
		while (num <= B) {

			now = calc(now,now);
			
			list.put(num, now);
			num *= 2;
		}
		Object[] keyset = list.keySet().toArray();
		Arrays.sort(keyset,Collections.reverseOrder());
		int index = 0;
		B = B-(long)keyset[0];
		int[][] ans = list.get(keyset[0]);
		while(true) {
			if(B == 0)
				break;
			if((long)keyset[index] > B) {
				index++;
				continue;
			}
			B = B-(long)keyset[index];
			ans = calc(ans,list.get(keyset[index]));
		}
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