import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int i = 1; i <= T; i++) {
			String str = sc.next();
			String result = "YES";
			int cnt = 0;
			char[] arr = new char[str.length()];

			for (int j = 0; j < arr.length; j++) {
				arr[j] = str.charAt(j);
			}

			for (int j = 0; j < arr.length; j++) {
				if (arr[j] == '(') {
					cnt++;
				} else {
					cnt--;
				}
				if (cnt < 0) {
					result = "NO";
					break;
				}
			}

			if (cnt != 0) {
				result = "NO";
			}
			System.out.println(result);
		}
	}

}
