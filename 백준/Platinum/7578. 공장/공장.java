import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static class Node implements Comparable<Node> {
		int index1, index2;

		public Node(int index1, int index2) {
			// TODO Auto-generated constructor stub
			this.index1 = index1;
			this.index2 = index2;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.index1 - o.index1;
		}
	}

	static class SegmentTree {
		// 세그먼트 트리를 구현할 배열
		long[] tree;

		// 생성자에서 세그먼트 트리의 전체노드 수 계산 (즉, 배열 길이)
		SegmentTree(int n) {
			// 트리의 높이 계산, log N 값 올림 후 +1
			double treeHeight = Math.ceil(Math.log(n) / Math.log(2)) + 1;
			// 트리의 노드 수 계산 2^트리의 높이
			long treeNodeCount = Math.round(Math.pow(2, treeHeight));
			// 트리의 길이 설정
			tree = new long[Math.toIntExact(treeNodeCount)];
		}

		// []arr : 세그먼트 트리는 리프노드에 배열 값을 저장.
		// node : 노드 인덱스 (항상 1부터 시작)
		// start, end : 트리의 노드들이 가지는 시작 / 종료 인덱스
		// 세그먼트 트리의 노드 값 초기화, ( insert )
		long init(int[] arr, int node, int start, int end) {
			// 세그먼트 트리의 리프노드인 경우 ( 자식이 없는 경우 )
			if (start == end) {
				// 리프노드에 배열의 값 저장 후 리턴
				return tree[node] = arr[start];
			} else {
				// 기준 : now
				// 자식노드 번호 : *2 , *2+1
				// 리프노드가 아닌 경우에는 자식노드의 값을 더해서 노드의 값 초기화 후 리턴
				return tree[node] = init(arr, node * 2, start, (start + end) / 2)
						+ init(arr, node * 2 + 1, (start + end) / 2 + 1, end);
			}
		}

		// node : node 번호
		// start : 노드가 가지는 합의 시작 인덱스
		// end : 노드가 가지는 합의 끝 인덱스
		// left : 구하려고 하는 배열의 합 구간 시작 인덱스
		// right : 구하려고 하는 배열의 구간 끝 인덱스
		// 배열의 특정 구간 합을 세그먼트 트리로 구하기
		long sum(int node, int nStart, int nEnd, int left, int right) {
			// 노드가 가지는 값의 구간이 구하려고 하는 합의 구간에 속하지 않는 경우 0리턴
			if (nEnd < left || right < nStart) {
				return 0;
			} else if (left <= nStart && nEnd <= right) {
				// 노드가 가지는 값의 구간이 구하려고 하는 합의 구간에 속하는 경우 노드 값 리턴
				return tree[node];
			} else {
				// 그 외는 2가지 경우가 존재
				// 1. 노드가 가지는 값의 구간이 구하려고 하는 합의 구간에 일부는 속하고 일부는 속하지 않는 경우
				// 2. 노드가 가지는 값의 구간이 구하려고 하는 합의 구간을 모두 포함하는 경우
				// 이와 같은 경우에는 자식노드를 탐색해서 값을 리턴
				int nMid = (nStart + nEnd) / 2;
				return sum(node * 2, nStart, nMid, left, right) + sum(node * 2 + 1, nMid + 1, nEnd, left, right);
			}
		}

		public void update(int node, int nStart, int nEnd, int idx, int diff) {
			if (idx < nStart || idx > nEnd)
				return;
			tree[node] = tree[node] + diff;
			int mid = (nStart + nEnd) / 2;
			if (nStart != nEnd) {
				// 리프노트는 이미 변경된 상태이므로 변경할 필요없음!

				update(node * 2, nStart, mid, idx, diff);
				update(node * 2 + 1, mid + 1, nEnd, idx, diff);
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		HashMap<Integer, Integer> map = new HashMap<>();
		int N = Integer.parseInt(br.readLine());
		int[] numIndex = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1, n; i <= N; i++) {
			n = Integer.parseInt(st.nextToken());
			map.put(n, i);
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 1, n; i <= N; i++) {
			n = Integer.parseInt(st.nextToken());
			numIndex[i] = map.get(n);
		}
		SegmentTree tree = new SegmentTree(N);
		int[] arr = new int[N + 1];
		tree.init(arr, 1, 1, N);

		long ans = 0;
		for (int i = 1; i <= N; i++) {
			int num = numIndex[i];
			ans += tree.sum(1, 1, N, num+1, N);
			tree.update(1, 1, N, num, 1);
		}
		System.out.println(ans);
	}
}