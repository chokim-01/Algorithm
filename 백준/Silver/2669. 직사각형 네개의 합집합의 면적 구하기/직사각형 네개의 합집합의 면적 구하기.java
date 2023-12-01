import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean[][] map = new boolean[101][101];
		int ans = 0;
		for (int i = 0; i < 4; i++) {
			int[] xy = Stream.of(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for (int a = xy[0]; a < xy[2]; a++)
				for (int b = xy[1]; b < xy[3]; b++)
					if (!map[a][b]) {
						map[a][b] = true;
						ans++;
					}
		}
		System.out.println(ans);
	}
}