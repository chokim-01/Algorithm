import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		int[] nS;
		int[] mS;
		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int nC = Integer.parseInt(st.nextToken());
			int mC = Integer.parseInt(st.nextToken());
			nS = new int[nC];
			mS = new int[mC];
			while (nC-- > 0)
				nS[nC] = Integer.parseInt(br.readLine());
			while (mC-- > 0)
				mS[mC] = Integer.parseInt(br.readLine());
			System.out.println(binSearch(nS, mS) + ".0");

		}
	}

	static int binSearch(int[] nS, int[] mS) {
		int[] mm = getMinMax(nS, mS);
		int l = mm[0];
		int r = mm[1];
		int ret = 0;
		while (l <= r) {
			int mid = (l + r) / 2;
			if (f(nS, mS, mid + 1) - f(nS, mS, mid) >= 0) {
				ret = mid;
				r = mid - 1;
			} else
				l = mid + 1;
		}
		return ret;
	}

	static long f(int[] nS, int[] mS, int h) {
		return f1(nS, mS.length, h) + 2 * nS.length * mS.length + f1(mS, nS.length, h);
	}

	static long f1(int[] arr, int m, int h) {
		return m * Arrays.stream(arr).mapToLong((a) -> Math.abs(a - h)).sum();
	}

	static int[] getMinMax(int[] nS, int[] mS) {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;

		for (int n : nS) {
			min = min < n ? min : n;
			max = max < n ? n : max;
		}
		for (int m : mS) {
			min = min < m ? min : m;
			max = max < m ? m : max;
		}
		return new int[] { min, max };
	}
}