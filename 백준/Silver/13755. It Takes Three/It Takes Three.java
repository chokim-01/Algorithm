import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int[][] rectangle = new int[3][2];
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 2; j++)
				rectangle[i][j] = sc.nextInt();
		Arrays.sort(rectangle, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return Math.max(o2[0], o2[1]) - Math.max(o1[0], o1[1]);
			}
		});
		System.out.println(perm(0, rectangle) ? 1 : 0);
	}

	static boolean perm(int index, int[][] rectangle) {
		if (index == rectangle.length) {
			if (rectangle[1][0] + rectangle[2][0] != rectangle[0][0])
				return false;
			if (rectangle[1][1] != rectangle[2][1])
				return false;
			if (rectangle[2][1] + rectangle[0][1] != rectangle[0][0])
				return false;
			return true;
		}
		if (perm(index + 1, rectangle))
			return true;
		swap(rectangle, index);
		if (perm(index + 1, rectangle))
			return true;
		swap(rectangle, index);
		return false;
	}

	static void swap(int[][] rectangle, int a) {
		int tmp = rectangle[a][0];
		rectangle[a][0] = rectangle[a][1];
		rectangle[a][1] = tmp;
	}
}