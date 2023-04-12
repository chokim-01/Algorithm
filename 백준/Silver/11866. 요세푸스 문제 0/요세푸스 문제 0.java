import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static class ArrayQueue {
		int front;
		int rear;
		int size;
		int[] arrays;

		public ArrayQueue(int size) {
			// TODO Auto-generated constructor stub
			this.front = -1;
			this.rear = -1;
			this.size = size;
			this.arrays = new int[size];
		}

		void enqueue(int number) {
			if (isFull()) {
				System.out.println("Full");
				return;
			}
			if (isEmpty()) {
				front = rear = 0;
			} else {
				rear = (rear + 1) % size;
			}
			arrays[rear] = number;
		}

		int dequeue() {
			int number = 0;
			if (isEmpty()) {
				System.out.println("Empty");
				throw new ArrayIndexOutOfBoundsException();
			}
			if (rear == front) {
				number = arrays[rear];
				front = rear = -1;
			} else {
				number = arrays[front];
				front = (front + 1) % size;
			}
			return number;
		}

		int sizeOfQueue() {
			if (isEmpty())
				return 0;
			return (rear - front + size) % size + 1;
		}

		boolean isEmpty() {
			// true : 비어있다. false : 비어있지않다.
			if (front == -1 && rear == -1)
				return true;
			return false;
		}

		boolean isFull() {
			// true : 가득 차있다. false : 가득 차있지 않다.
			if ((rear + 1) % size == front)
				return true;
			return false;
		}

		void print() {
			StringBuilder sb = new StringBuilder();
			for (int i = front; i != rear; i = (i + 1) % size) {
				sb.append(arrays[i] + " ");
			}
			sb.append(arrays[rear]);
			System.out.println(sb);
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 값들
		int N = sc.nextInt();
		int K = sc.nextInt();
		ArrayQueue q = new ArrayQueue(N);

		for (int i = 1; i <= N; i++) {
			q.enqueue(i);
		}
		StringBuilder sb = new StringBuilder();
		sb.append("<");

		// 7번 반복
		while (N-- > 0) {
			int k = K - 1;
			// k-1번만큼 뒤로 보내버려라
			while (k-- > 0) {
				q.enqueue(q.dequeue());
			}
			int number = q.dequeue();
			if (q.sizeOfQueue() == 0) {
				sb.append(number);
			} else {
				sb.append(number + ", ");
			}
		}
		sb.append(">");
		System.out.println(sb);

	}
}