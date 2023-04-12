import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 테스트 케이스 T
		int T = sc.nextInt();

		// 테스트케이스 만큼 반복
		for (int tc = 1; tc <= T; tc++) {

			// index와 중요도가 함께 움직여야 하므로 int배열로 생성
			Queue<int[]> q = new LinkedList<int[]>();

			// 값 수
			int N = sc.nextInt();
			// 정답을 구할 인덱스
			int M = sc.nextInt();

			// int[0] : index, int[1] : importance
			for (int i = 0; i < N; i++) {
				q.add(new int[] { i, sc.nextInt() });
			}

			// 정답 시간. 인쇄를 할 때마다 더한다
			int time = 1;
			while (true) {
				// peek. 즉 가장 앞에것 보다 뒤에 있는 값이 중요도가 크면 빼지 않고 뒤로 전달
				int[] peek = q.peek();

				// true : 앞에 있는게 가장 큰 값 false : 가장 큰 값이 아님
				boolean flag = true;
				for (int[] ar : q) {
					if (peek[1] < ar[1]) {
						// 앞에 있는 게 가장 큰 중요도를 가지고 있지 않음
						flag = false;
						break;
					}
				}

				if (!flag) { // 가장 큰 값이 아니면 뒤로 보내기
					q.add(q.poll());
				} else {// 가장 큰 값이면 빼기.
					// 구하려는 인덱스와 일치하면 정답
					if (q.poll()[0] == M)
						break;

					time++;
				}
			}
			System.out.println(time);

		}

	}
}