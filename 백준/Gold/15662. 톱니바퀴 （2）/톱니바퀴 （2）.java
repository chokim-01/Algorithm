import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static final int top = 1;
	static final int front = 64;
	static final int rear = 4;

	static int N;
	static int[] gears, chk;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());

		gears = new int[N];
		for (int i = 0; i < N; i++)
			gears[i] = Integer.parseInt(new StringBuilder(br.readLine()).reverse().toString(), 2);

		int K = Integer.parseInt(br.readLine());
		while (K-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken());
			chk = new int[N];
			chk[a] = b;
			go(b, a - 1, -1);
			go(b, a + 1, 1);
			for (int i = 0; i < N; i++)
				if (chk[i] != 0)
					revolve(i, chk[i]);
		}
		int ans = 0;
		for (int g : gears)
			if ((g & top) == top)
				ans++;
		System.out.println(ans);
	}

	static void go(int c, int now, int p) {
		int f = p == -1 ? front : rear;
		int r = f == front ? rear : front;
		for (; now >= 0 && now < N; now += p)
			if (((gears[now] & r) == r) ^ ((gears[now - p] & f) == f))
				chk[now] = c = c == 1 ? -1 : 1;
			else
				break;
	}

	static void revolve(int gear, int cc) {
		if (cc == 1) {
			int n = ((gears[gear] & 128) == 128) ? 1 : 0;
			gears[gear] = (gears[gear] << 1) % 256 + n;
		} else {
			int n = gears[gear] & 1;
			gears[gear] >>= 1;
			gears[gear] += n << 7;
		}
	}
}