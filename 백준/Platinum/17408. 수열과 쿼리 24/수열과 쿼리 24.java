import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int max, max2;

		public Node() {
			// TODO Auto-generated constructor stub
		}

		public Node(int num) {
			this.max = num;
		}

		public Node(int max, int max2) {
			// TODO Auto-generated constructor stub
			this.max = max;
			this.max2 = max2;
		}

		@Override
		public String toString() {
			return "[" + this.max + " " + this.max2 + "]";
		}
	}

	static class SegmentTree {
		// 세그먼트 트리를 구현할 배열
		private Node[] tree;

		// 생성자에서 세그먼트 트리의 전체노드 수 계산 (즉, 배열 길이)
		SegmentTree(int n) {
			// 트리의 높이 계산, log N 값 올림 후 +1
			double treeHeight = Math.ceil(Math.log(n) / Math.log(2)) + 1;
			// 트리의 노드 수 계산 2^트리의 높이
			long treeNodeCount = Math.round(Math.pow(2, treeHeight));
			// 트리의 길이 설정
			int size = Math.toIntExact(treeNodeCount);
			tree = new Node[size];
			for (int i = 0; i < size; i++)
				tree[i] = new Node();
		}

		// []arr : 세그먼트 트리는 리프노드에 배열 값을 저장.
		// node : 노드 인덱스 (항상 1부터 시작)
		// start, end : 트리의 노드들이 가지는 시작 / 종료 인덱스
		// 세그먼트 트리의 노드 값 초기화, ( insert )
		Node init(Node[] ns, int node, int start, int end) {
			// 세그먼트 트리의 리프노드인 경우 ( 자식이 없는 경우 )
			if (start == end) {
				// 리프노드에 배열의 값 저장 후 리턴
				tree[node] = ns[start];
			} else {
				// 기준 : now
				// 자식노드 번호 : *2 , *2+1
				// 리프노드가 아닌 경우에는 자식노드의 값을 더해서 노드의 값 초기화 후 리턴
				Node[] nodes = { init(ns, node * 2, start, (start + end) / 2),
						init(ns, node * 2 + 1, (start + end) / 2 + 1, end) };
				tree[node] = makeNode(nodes);
			}
			return tree[node];
		}

		// node : node 번호
		// start : 노드가 가지는 합의 시작 인덱스
		// end : 노드가 가지는 합의 끝 인덱스
		// left : 구하려고 하는 배열의 합 구간 시작 인덱스
		// right : 구하려고 하는 배열의 구간 끝 인덱스
		// 배열의 특정 구간 합을 세그먼트 트리로 구하기
		Node get(int node, int nStart, int nEnd, int left, int right) {
			// 노드가 가지는 값의 구간이 구하려고 하는 합의 구간에 속하지 않는 경우 0리턴
			if (nEnd < left || right < nStart) {
				return new Node();
			} else if (left <= nStart && nEnd <= right) {
				// 노드가 가지는 값의 구간이 구하려고 하는 합의 구간에 속하는 경우 노드 값 리턴
				return tree[node];
			} else {
				// 그 외는 2가지 경우가 존재
				// 1. 노드가 가지는 값의 구간이 구하려고 하는 합의 구간에 일부는 속하고 일부는 속하지 않는 경우
				// 2. 노드가 가지는 값의 구간이 구하려고 하는 합의 구간을 모두 포함하는 경우
				// 이와 같은 경우에는 자식노드를 탐색해서 값을 리턴
				int nMid = (nStart + nEnd) / 2;
				Node[] nodes = { get(node * 2, nStart, nMid, left, right),
						get(node * 2 + 1, nMid + 1, nEnd, left, right) };
				return makeNode(nodes);
			}
		}

		public Node update(int node, int nStart, int nEnd, int idx, int diff) {
			if (idx < nStart || idx > nEnd)
				return tree[node];
			int mid = (nStart + nEnd) / 2;
			if (nStart != nEnd) {
				// 리프노트는 이미 변경된 상태이므로 변경할 필요없음!
				Node[] nodes = { update(node * 2, nStart, mid, idx, diff),
						update(node * 2 + 1, mid + 1, nEnd, idx, diff) };
				tree[node] = makeNode(nodes);
			} else
				tree[node] = new Node(diff);

			return tree[node];
		}

		public Node makeNode(Node[] nodes) {
			int[] arr = { nodes[0].max, nodes[0].max2, nodes[1].max, nodes[1].max2 };
			int max = 0;
			int max2 = 0;
			for (int a : arr)
				if (a > max) {
					max2 = max;
					max = a;
				} else if (a > max2)
					max2 = a;
			return new Node(max, max2);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		Node[] nodes = new Node[N + 1];
		nodes[0] = new Node();
		for (int i = 1, num; i <= N; i++) {
			num = Integer.parseInt(st.nextToken());
			nodes[i] = new Node(num);
		}
		SegmentTree sTree = new SegmentTree(N);
		sTree.init(nodes, 1, 1, N);

		int M = Integer.parseInt(br.readLine());
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int o = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (o == 1) {
				sTree.update(1, 1, N, a, b);
			} else {
				Node ans = sTree.get(1, 1, N, a, b);
				bw.write((ans.max + ans.max2) + "\n");
			}
		}
		bw.flush();
		bw.close();
	}
}
