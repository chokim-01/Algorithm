import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static int A, B, C;
	static ArrayList<Food> foods;
	static HashMap<String, Food> map;

	static ArrayList<String> order;

	static class Food {
		char type;
		String name;
		int price;

		public Food(char type, String name, int price) {
			// TODO Auto-generated constructor stub
			this.type = type;
			this.name = name;
			this.price = price;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input(br);
		long price = 0;
		boolean flag = true;
		boolean service = false;
		for (String o : order) {
			Food menu = map.get(o);
			char type = menu.type;
			switch (type) {
			case 'A':
				price += menu.price;
				break;
			case 'B':
				if (price < 20000)
					flag = false;
				else
					price += menu.price;
				break;
			default:
				if (service || price < 50000)
					flag = false;
				service = true;
				break;

			}
			if (!flag)
				break;
		}
		System.out.println(flag ? "Okay" : "No");
	}

	static void input(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new HashMap<>();
		foods = new ArrayList<>();
		order = new ArrayList<>();

		while (A-- > 0) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			int p = Integer.parseInt(st.nextToken());
			map.put(name, new Food('A', name, p));
		}
		while (B-- > 0) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			int p = Integer.parseInt(st.nextToken());
			map.put(name, new Food('B', name, p));
		}
		while (C-- > 0) {
			String name = br.readLine();
			map.put(name, new Food('C', name, 0));
		}
		int K = Integer.parseInt(br.readLine());
		while (K-- > 0) {
			order.add(br.readLine());
		}
		Collections.sort(order, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				return map.get(o1).type - map.get(o2).type;
			}
		});

	}
}