import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] a = new int[n];
		for (int i = 0; i < n; i++)
			a[i] = Integer.parseInt(br.readLine());
		int ans = 0;
		int num = a[n - 1];
		int i = n - 1;
		while (i-- > 0)
			ans += a[i] > (num = Math.min(a[i], --num)) ? a[i] - num : 0;
		System.out.println(ans);
	}
}