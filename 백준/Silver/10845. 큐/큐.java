import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		Deque<Integer> deque = new ArrayDeque<>();

		int N = Integer.parseInt(br.readLine());
		while (N-- > 0) {
			st = new StringTokenizer(br.readLine());
			switch (st.nextToken()) {
			case "push":
				deque.add(Integer.parseInt(st.nextToken()));
				break;
			case "pop":
				if (deque.isEmpty())
					print(-1);
				else
					print(deque.poll());
				break;
			case "size":
				print(deque.size());
				break;
			case "empty":
				if (deque.isEmpty()) {
					print(1);
				} else {
					print(0);
				}
				break;
			case "front":
				if (deque.isEmpty()) {
					print(-1);
				} else {
					print(deque.getFirst());
				}
				break;
			case "back":
				if (deque.isEmpty()) {
					print(-1);
				} else {
					print(deque.getLast());
				}
				break;
			}
		}
		System.out.println(sb);

	}

	static void print(int num) {
		sb.append(num).append("\n");
	}
}