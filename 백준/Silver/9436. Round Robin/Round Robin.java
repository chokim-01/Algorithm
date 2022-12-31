import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		while (true) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			if (n == 0)
				break;
			int m = Integer.parseInt(st.nextToken());

			ArrayList<Integer> list = new ArrayList<>();
			for (int i = 0; i < n; i++)
				list.add(0);

			int idx = 0;
			while (true) {

				int size = list.size();
				int quotent = m / size;
				int remainder = m % size;
				idx = idx % size;
				if (quotent != 0)
					while (size-- > 0)
						list.set(size, list.get(size) + quotent);

				idx = (idx + list.size() - 1) % list.size();

				while (remainder-- > 0) {
					idx = (idx + 1) % list.size();
					list.set(idx, list.get(idx) + 1);
				}
				list.remove(idx);
				if (endChk(list))
					break;
			}
			bw.write(list.size() + " " + list.get(0) + "\n");
		}
		bw.flush();
		bw.close();
	}

	static boolean endChk(ArrayList<Integer> list) {
		int now = list.get(0);

		for (int n : list)
			if (n != now)
				return false;
		return true;
	}
}