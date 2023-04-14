import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static class customDoublyLinkedList {
		Node head; // 첫 번째 노드
		Node tail; // 마지막 노드
		int size;

		// head와 tail을 더미로 둔다.
		public customDoublyLinkedList() {
			head = new Node(-1);
			tail = new Node(-1);
			size = 0;
			// TODO Auto-generated constructor stub
		}

		public void addLast(int data) {
			Node n = new Node(data);
			if (size == 0) {
				head.next = n;
				tail.prev = n;
				n.prev = head;
				n.next = tail;
			} else {
				tail.prev.next = n;
				n.prev = tail.prev;
				tail.prev = n;
				n.next = tail;
			}
			size++;
		}

		public Node moveLeft(Node n) {
			if (head != n.prev)
				return n.prev;
			return n;
		}

		public Node moveRight(Node n) {
			if (tail != n)
				return n.next;
			return n;
		}

		public Node removeLeft(Node now) {
			if (now.prev == head)
				return now;
			Node delNode = now.prev;
			delNode.prev.next = now;
			now.prev = delNode.prev;
			size--;
			return now.prev;
		}

		// 문자를 커서 왼쪽에 추가하기 위해 새로운 메소드 작성
		public Node addLeft(Node now, int data) {
			Node newNode = new Node(data);
			newNode.prev = now.prev;
			newNode.next = now;
			now.prev.next = newNode;
			now.prev = newNode;
			size++;
			return newNode.next;
		}
	}

	// 노드
	static class Node {
		int value;
		Node prev;
		Node next;

		public Node(int value) {
			// TODO Auto-generated constructor stub
			this.prev = null;
			this.next = null;
			this.value = value;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 만든 이중 연결 리스트
		customDoublyLinkedList list = new customDoublyLinkedList();
		// 자바 자료구조 LinkedList에서 remove(n), add(n)은 앞에서부터 찾기 때문에 O(n)이라는 시간이 든다.
		// 그렇다면 Node를 가지고 있고 직접 이동하면서 지우고 추가한다면 O(n)보다는 빠른 시간이지 않을까?
		String str = br.readLine();

		for (int i = 0; i < str.length(); i++) {
			list.addLast(str.charAt(i));
		}

		Node cursor = list.tail;
		int M = Integer.parseInt(br.readLine());
		while (M-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			switch (st.nextToken()) {
			case "L": // 왼쪽으로 한칸. 이전이 dummy head면 그대로
				cursor = list.moveLeft(cursor);
				break;
			case "D": // 오른쪽으로 한칸. dummy tail이면 그대로
				cursor = list.moveRight(cursor);
				break;
			case "B": // 커서 왼쪽에 있는 문자를 삭제함. 커서가 더미헤드 뒤이면
				list.removeLeft(cursor);
				break;
			case "P": // 추가.
				char add = st.nextToken().charAt(0);
				cursor = list.addLeft(cursor, add);
				break;
			}
		}
		StringBuilder sb = new StringBuilder();
		Node now = list.head.next;
		while (now != list.tail) {
			sb.append((char) now.value);
			now = now.next;
		}
		sb.append("\n");
		System.out.println(sb);
	}

}
