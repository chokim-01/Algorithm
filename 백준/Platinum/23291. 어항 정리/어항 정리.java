import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

class Main {

	static int N, K;
	static ArrayList<LinkedList<Integer>> list;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		K = sc.nextInt();

		// 링크드리스트 안에 링크드리스트
		list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			list.add(new LinkedList<>());
			list.get(i).add(sc.nextInt());
		}

		// K만큼.
		int time = 0;
		while (true) {
			time++;
			// 더하는 경우
			plus();

			// 옮기는 경우
			dfs();

			// N/2 옮기기
			dfs2(0);
			if (endFlag())
				break;
		}
		System.out.println(time);
	}

	// 차 구하기
	static boolean endFlag() {
		int min = 1000000;
		int max = -1;
		for (int i = 0; i < list.size(); i++) {
			int n = list.get(i).get(0);
			min = min > n ? n : min;
			max = max < n ? n : max;
		}
		if (max - min <= K)
			return true;
		return false;
	}

	// 가장 작은 것 더하기
	static void plus() {
		int min = 1000000;
		for (int i = 0; i < list.size(); i++) {
			int n = list.get(i).get(0);
			min = min > n ? n : min;
		}
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).get(0) == min)
				list.get(i).set(0, list.get(i).get(0) + 1);
	}

	static void dfs2(int idx) {
		if (idx == 2) {
			leveling();
			changeList();
			return;
		}
		// 절반 만큼 반대로
		int ySize = list.size() / 2;
		// 위로 올리기
		int xSize = list.get(0).size();
		Stack<Integer> s = new Stack<>();

		for (int i = 0; i < xSize; i++)
			for (int j = 0; j < ySize; j++)
				s.add(list.get(j).get(i));

		int cnt = ySize;
		while (cnt-- > 0)
			list.remove(0);

		int index = 0;
		while (!s.isEmpty()) {
			list.get(index).add(s.pop());
			if (++index == ySize)
				index = 0;
		}
		dfs2(idx + 1);
	}

	// dfs : 위로 올리고 회전. 가장 왼쪽에 있는 값을.
	static void dfs() {

		// 가장 앞의 height만큼 오른쪽의 위로 올림
		// 올릴 때 clockWise 90도
		int height = list.get(0).size();
		int width = 1;
		if (height != 1) {
			width = 0;
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).size() != height)
					break;
				width++;
			}
		}

		// 종료 조건 : height > list.size()-width?
		if (height > list.size() - width) {
			leveling();
			changeList();
			return;
		}
		Queue<Integer> q = new LinkedList<>();
		for (int i = width - 1; i >= 0; i--)
			for (int j = 0; j < list.get(i).size(); j++)
				q.add(list.get(i).get(j));

		// width만큼 지움
		int cnt = width;
		while (cnt-- > 0)
			list.remove(0);

		int index = 0;
		while (!q.isEmpty()) {

			list.get(index).add(q.poll());
			if (++index == height) {
				index = 0;
			}
		}
		dfs();
	}

	static void changeList() {
		ArrayList<LinkedList<Integer>> newList = new ArrayList<>();

		int index = 0;
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.get(i).size(); j++) {
				newList.add(new LinkedList<>());
				newList.get(index++).add(list.get(i).get(j));
			}
		}
		list = newList;

	}

	// 물고기 수 조절
	static void leveling() {
		// 오른쪽과 위를 비교
		// 맵배열은 리스트와 반대
		int[][] map = new int[N][N];
		int xSize = list.size();
		for (int i = 0; i < xSize; i++) {
			int ySize = list.get(i).size();
			for (int j = 0; j < ySize; j++) {
				// 위쪽과의 차이
				if (j + 1 < ySize) {
					int minus = (list.get(i).get(j) - list.get(i).get(j + 1)) / 5;
					if (minus > 0) { // 내가 큼
						map[i][j] -= minus;
						map[i][j + 1] += minus;
					}
					if (minus < 0) { // 상대방이 큼
						map[i][j] -= minus;
						map[i][j + 1] += minus;
					}
				}
				
				// 오른쪽과의 차이
				if (i + 1 < xSize) {
					if (list.get(i + 1).size() > j) {
						int minus = (list.get(i).get(j) - list.get(i + 1).get(j)) / 5;
						if (minus > 0) { // 내가 큼
							map[i][j] -= minus;
							map[i + 1][j] += minus;
						}
						if (minus < 0) { // 상대방이 큼
							map[i][j] -= minus;
							map[i + 1][j] += minus;
						}
					}
				}
			}
		}
		for (int i = 0; i < list.size(); i++)
			for (int j = 0; j < list.get(i).size(); j++)
				if (map[i][j] != 0)
					list.get(i).set(j, list.get(i).get(j) + map[i][j]);
	}

	static void print() {
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.get(i).size(); j++) {
				System.out.print(list.get(i).get(j) + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
