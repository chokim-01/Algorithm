import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static class Node {
		int x, y, dxy;
		Queue<Tail> tail = new LinkedList<>();

		public Node(int x, int y, int dxy, int tx, int ty) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
			this.dxy = dxy;
			tail.add(new Tail(tx, ty));
		}
		public Node(int x,int y,int dxy) {
			this.dxy = dxy;
			this.x=x;
			this.y=y;
			// TODO Auto-generated constructor stub
		}

	}

	static class Tail {
		int x, y;

		public Tail(int x, int y) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
		}
	}

	static int[][] dxy = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	// 상, 우, 하, 좌 +90도 ( Right) == +1
	// -90도 ( Left) == -1
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt(); // 보드의 크기
		int K = sc.nextInt(); // 사과의 위치

		int[][] map = new int[N][N];
		for (int i = 0; i < K; i++)
			map[sc.nextInt() - 1][sc.nextInt() - 1] = 2;

		// 사과 : 2
		// 뱀 : 1
		int L = sc.nextInt(); // 뱀의 방향 변환 횟수

		int[][] arr = new int[L][2];
		for (int i = 0; i < L; i++) {

			int num = sc.nextInt();
			String s = sc.next();

			arr[i][0] = num;
			if (s.equals("D"))
				arr[i][1] = 1;
			else
				arr[i][1] = -1;

		}
		Queue<Node> q = new LinkedList<>();
		// 맨 위 맨 좌측에서 시작
		q.add(new Node(0, 0, 1)); // 시작 좌표 | 방향 | 꼬리 좌표
		map[0][0] = 1;
		// 꼬리 좌표는 큐로 구성됨.

		// 꼬리의 좌표를 기억하고다녀야하나? Tail(node)로 들고다닌다. Que로, 그럼 순차제거
		int sec = 0;
		int idx = 0;
		while (!q.isEmpty()) {
			
			/*
			 System.out.println(sec); for(int i = 0;i<map.length;i++)
			 System.out.println(Arrays.toString(map[i])); System.out.println();
			 */
			Node n = q.poll();
			// 끝까지 갔을 때
			

			int nx = n.x + dxy[n.dxy][0];
			int ny = n.y + dxy[n.dxy][1];
			// 맵 밖으로 가거나 가려는 방향에 꼬리가 있으면 리턴
			if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == 1) {
				sec++;
				break;
			}
			// 간 곳에 사과가 있다면.
			if (map[nx][ny] == 2) {
				map[nx][ny] = 1;
				// 1로 바꾼 후
				// Tail을 추가. 현재 머리를.
				n.tail.add(new Tail(n.x, n.y));
				n.x = nx;
				n.y = ny;
				q.add(n);

			} else {
				// 아니라면 꼬리를 줄이고 추가
				n.tail.add(new Tail(n.x, n.y));
				Tail t = n.tail.poll();
				map[t.x][t.y] = 0;

				map[nx][ny] = 1;
				n.x = nx;
				n.y = ny;
				q.add(n);

			}
			sec++;
			if (idx == arr.length)
				idx = 9999999;
			// 방향전환
			if (idx != 9999999 && sec == arr[idx][0]) {

				n.dxy += arr[idx][1];
				if (n.dxy == dxy.length)
					n.dxy = 0;
				if (n.dxy < 0)
					n.dxy = 3;
				idx++;
			}

		}
		System.out.println(sec);

	}
}
