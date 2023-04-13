import java.util.Scanner;
import java.util.Stack;

public class Main {
	static class customList {
		Node head;
		Node tail;
		int size;

		public customList() {
			// TODO Auto-generated constructor stub
			size = 0;
		}

		public void addLast(int value) {
			Node n = new Node(value);
			size++;
			if (head == null) {
				head = n;
				tail = n;
			} else {
				tail.next = n;
				tail = n;
			}
		}

		public int removeFirst() {
			size--;
			int retValue = head.value;
			head = head.next;
			return retValue;
		}

	}

	static class Node {
		int value;
		Node next;

		public Node(int value) {
			this.value = value;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		customList list = new customList();
		// 숫자 총 개수
		int N = sc.nextInt();
		for (int i = 1; i <= N; i++) {
			list.addLast(i);
		}

		int K = sc.nextInt();

		StringBuilder sb = new StringBuilder();
		sb.append("<");
		// 7번을 돌리면 끝
		for (int i = 0; i < N; i++) {
			int k = (K - 1) % list.size;
			// 시간을 줄여야 한다.
			for (; k > 0; k--) {
				// 앞에있는걸 뒤로 보내버려라
				list.addLast(list.removeFirst());
			}
			// 제거할 대상이 앞.
			int number = list.removeFirst();
			if (i == N - 1) {
				sb.append(number);
			} else {
				sb.append(number + ", ");
			}
		}
		sb.append(">");
		System.out.println(sb);
	}
}