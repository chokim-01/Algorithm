import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	// 배열 0, 배열 1은 알아보기 힘드니 나만의 클래스를 만들어 변수를 저장해놓고 사용한다.
	static class Node {
		int index, importance;

		public Node(int index, int importance) {
			// TODO Auto-generated constructor stub
			this.index = index;
			this.importance = importance;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 테스트 케이스 T
		int T = sc.nextInt();

		// 테스트케이스 만큼 반복
		for (int tc = 1; tc <= T; tc++) {

			// index와 중요도가 함께 움직여야 하므로 나만의 클래스를 가지는 큐로 생성
			Queue<Node> q = new LinkedList<>();

			// 값 수
			int N = sc.nextInt();
			// 정답을 구할 인덱스
			int M = sc.nextInt();

			// index, importance 로 이루어진 클래스
			for (int i = 0; i < N; i++) {
				q.add(new Node(i, sc.nextInt()));
			}

			// 정답 시간. 인쇄를 할 때마다 더한다
			int time = 1;
			while (true) {
				// peek. 즉 가장 앞에것 보다 뒤에 있는 값이 중요도가 크면 빼지 않고 뒤로 전달
				Node peek = q.peek();

				// true : 앞에 있는게 가장 큰 값 false : 가장 큰 값이 아님
				boolean flag = true;
				for (Node now : q) {
					if (peek.importance < now.importance) {
						// 앞에 있는 게 가장 큰 중요도를 가지고 있지 않음
						flag = false;
						break;
					}
				}

				if (!flag) { // 가장 큰 값이 아니면 뒤로 보내기
					q.add(q.poll());
				} else {// 가장 큰 값이면 빼기.
					// 구하려는 인덱스와 일치하면 정답
					Node n = q.poll();
					if (n.index == M)
						break;

					time++;
				}
			}
			System.out.println(time);

		}

	}
}