import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static class SegmentTree {
		// 세그먼트 트리를 구현할 배열
		private int[] tree;

		// 생성자에서 세그먼트 트리의 전체노드 수 계산 (즉, 배열 길이)
		SegmentTree(int n) {
			// 트리의 높이 계산, log N 값 올림 후 +1
			double treeHeight = Math.ceil(Math.log(n) / Math.log(2)) + 1;
			// 트리의 노드 수 계산 2^트리의 높이
			int treeNodeCount = (int) Math.round(Math.pow(2, treeHeight));
			// 트리의 길이 설정
			tree = new int[Math.toIntExact(treeNodeCount)];
		}

		// []arr : 세그먼트 트리는 리프노드에 배열 값을 저장.
		// node : 노드 인덱스 (항상 1부터 시작)
		// start, end : 트리의 노드들이 가지는 시작 / 종료 인덱스
		// 세그먼트 트리의 노드 값 초기화, ( insert )
		int init(int[] arr, int node, int start, int end) {
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
		int sum(int node, int nStart, int nEnd, int left, int right) {
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
	}

	static SegmentTree[] tree;
	static int[][] arr;
	static int N, M;
	static int countOfArea;
	static int ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		countOfArea = 4;

		ans = 0;

		arr = new int[N][M + 1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}

		tree = new SegmentTree[N];
		for (int i = 0; i < N; i++) {
			tree[i] = new SegmentTree(M);
			tree[i].init(arr[i], 1, 1, M);
		}
		for (int i = 0; i < N; i++)
			for (int j = 1; j <= M; j++)
				dfs(i, j, j, countOfArea, 0);

		System.out.println(ans);
	}

	static void dfs(int x, int beforeMinY, int beforeMaxY, int count, int sum) {
		if (count == 0) {
			ans = ans < sum ? sum : ans;
			return;
		}
		if (x >= N)
			return;

		// 선택하면 count가 줄어듦.

		// 이번 x에서 c개를 선택
		for (int c = 1; c <= count; c++) {
			// beforeX보다 작지 않으면서 beforeY보다 크지 않게.
			// 현재를 선택하고. beforeMinY, beforeMaxY를 세팅 후 전달
			int now = beforeMinY - (c - 1) < 1 ? 1 : beforeMinY - (c - 1); // 스타트지점 부터 c개를 선택

			for (; now <= beforeMaxY; now++) {
				if (now < 1) // 왼쪽 초과
					continue;
				if (now + (c - 1) > M) // 오른쪽 초과
					break;

				int nowSum = 0;
				if (c == 1)
					nowSum = arr[x][now];
				else {
					// 선택한 지역 넣어주기
					nowSum = tree[x].sum(1, 1, M, now, now + c - 1);
				}
				dfs(x + 1, now, now + c - 1, count - c, sum + nowSum);
			}
		}
	}
}
