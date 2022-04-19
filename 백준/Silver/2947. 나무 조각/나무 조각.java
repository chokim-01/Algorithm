import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

class Main {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int[] arr = new int[5];
		for (int i = 0; i < 5; i++)
			arr[i] = sc.nextInt();

		while (true) {
			for (int i = 0; i < 4; i++) {
				if (change(i, i + 1, arr)) {
					bw.write(print(arr).toString());
					bw.newLine();
				}
			}
			boolean flag = true;
			for(int i = 0;i<5;i++)
				if(arr[i] != i+1) {
					flag = false;
					break;
				}
			if(flag)
				break;
		}
		bw.flush();
		bw.close();

	}
	static StringBuffer print(int[] arr) {
		StringBuffer sb = new StringBuffer();
		for(int i = 0;i<5;i++)
			sb.append(arr[i] + " ");
		return sb;
	}

	static boolean change(int x, int y, int[] arr) {
		if (arr[x] > arr[y]) {
			int tmp = arr[y];
			arr[y] = arr[x];
			arr[x] = tmp;
			return true;
		}
		return false;
	}
}
