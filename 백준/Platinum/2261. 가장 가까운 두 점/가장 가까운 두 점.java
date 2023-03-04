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
			// 현재 축 기준으로 계산
			int key = keyList.get(i); // y

			Collections.sort(list.get(key));
			int bef = list.get(key).get(0);
			for (int j = 1, k, c; j < list.get(key).size(); j++) {
				k = list.get(key).get(j);
				c = (k - bef) * (k - bef);
				ans = ans < c ? ans : c;
				bef = k;
			}

			// choice
			for (int x : list.get(key)) { // x
				// x y 기준으로 짧은 것.
				for (int j = i + 1; j < keyList.size(); j++) {
					int key2 = keyList.get(j);
					if (key2 - key >= Math.sqrt(ans))
						break;
					// next sort
					Collections.sort(list.get(key2), new Comparator<Integer>() {

						@Override
						public int compare(Integer o1, Integer o2) {
							// TODO Auto-generated method stub
							// o1 o2 : x
							return (o1 - x) * (o1 - x) - (o2 - x) * (o2 - x);
						}
					});
					int x2 = list.get(keyList.get(j)).get(0);
					int len = (key2 - key) * (key2 - key) + (x2 - x) * (x2 - x);
					ans = ans < len ? ans : len;
				}
			}
		}
		System.out.println(ans);

	}
}
