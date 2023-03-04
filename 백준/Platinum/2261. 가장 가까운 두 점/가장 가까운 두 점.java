import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int ans;
	static LinkedHashMap<Integer, ArrayList<Integer>> list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		ans = Integer.MAX_VALUE;
		int N = Integer.parseInt(br.readLine());

		list = new LinkedHashMap<>();

		for (int i = 0, x, y; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());

			if (!list.containsKey(y))
				list.put(y, new ArrayList<>());
			list.get(y).add(x);
		}
		List<Integer> keyList = new ArrayList<>(list.keySet());
		Collections.sort(keyList);
		for (int i = 0; i < keyList.size(); i++) {
			int key = keyList.get(i); // y

			// 기준 축 같은라인
			sortArrayList(list.get(key), -1);
			int bef = list.get(key).get(0);
			for (int j = 1, k, c; j < list.get(key).size(); j++) {
				k = list.get(key).get(j);
				c = pow(k - bef);
				ans = ans < c ? ans : c;
				bef = k;
			}
			///
			// 기준 축과 이후 축 찾기
			for (int x : list.get(key)) { // 기준 축 x
				// x y 기준으로 짧은 것.
				for (int j = i + 1; j < keyList.size(); j++) {
					// 기준 축 : (x,key) 현재 축 : (?,key2)
					int key2 = keyList.get(j);

					// y축이 정답보다 길면 방문 x
					if (key2 - key >= Math.sqrt(ans))
						break;
					// next sort
					sortArrayList(list.get(key2), x);

					// 정렬 후 front가 짧은 범위 (x,key2)
					int x2 = list.get(keyList.get(j)).get(0);
					int len = pow(key2 - key) + pow(x2 - x);
					ans = ans < len ? ans : len;
				}
			}
			///
		}
		System.out.println(ans);
	}

	static int pow(int x) {
		return x * x;
	}

	static void sortArrayList(ArrayList<Integer> list, int x) {
		if (x == -1) { // solo
			Collections.sort(list);
		} else { // others
			Collections.sort(list, new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					// TODO Auto-generated method stub
					// o1 o2 : x
					return (o1 - x) * (o1 - x) - (o2 - x) * (o2 - x);
				}
			});
		}
	}
}
