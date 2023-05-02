import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.stream.Stream;

class Main {
	static TreeSet<Long> list;
	static int[] array = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		list = new TreeSet<>();

		for (int i = 1; i <= 10; i++)
			for (int j = 0; j < array.length; j++)
				dfs(j, 0, 0, i);

		Long[] arr = list.stream().toArray(Long[]::new);

		if (N >= arr.length)
			System.out.println(-1);
		else
			System.out.println(arr[N]);

	}

	static void dfs(int index, long number, int choice, int count) {
		if (index == -1) {
			if (choice != count)
				return;
			list.add(number);
			return;
		}
		// 선택
		if (choice < count)
			dfs(index - 1, number * 10 + array[index], choice + 1, count);
		// 미선택
		dfs(index - 1, number, choice, count);

	}
}