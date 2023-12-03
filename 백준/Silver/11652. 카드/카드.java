import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Map<Long, Integer> map = new HashMap<>();

		int N = Integer.parseInt(br.readLine());
		while (N-- > 0) {
			long a = Long.parseLong(br.readLine());
			if (!map.containsKey(a))
				map.put(a, 0);
			map.put(a, map.get(a) + 1);
		}

		ArrayList<Long> keys = new ArrayList<>(map.keySet());
		Collections.sort(keys, new Comparator<Long>() {

			@Override
			public int compare(Long o1, Long o2) {
				// TODO Auto-generated method stub
				if (map.get(o1) - map.get(o2) == 0)
					return o1.compareTo(o2);
				return map.get(o2) - map.get(o1);
			}
		});
		System.out.println(keys.get(0));
	}
}