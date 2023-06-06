public class Test {
	int kth(int[] a, int k) {
		int[] cA = new int[65536];
		int[] cB = new int[65536];
        
		for (int i = 0; i < a.length; i++) {
            a[i] += 1000000000;
			cA[a[i] / 65536]++;
        }

		for (int i = 1; i < cA.length; i++)
			cA[i] += cA[i - 1];

		int find = binSearch(cA, k);

		for (int i = 0; i < a.length; i++) {
			if (a[i] / 65536 != find)
				continue;
			cB[a[i] % 65536]++;
		}
		for (int i = 1; i < cA.length; i++)
			cB[i] += cB[i - 1];

		int cnt = k - (find == 0 ? 0 : cA[find - 1]);
		int find2 = binSearch(cB, cnt);
		int ans = find * 65536 + find2-1000000000;
		return ans;
    }
    
    static int binSearch(int[] c, int f) {
		int ret = 0;
		int l = 0;
		int r = c.length-1;
		while (l <= r) {
			int mid = (l + r) / 2;
			if (c[mid] >= f) {
				ret = mid;
				r = mid - 1;
			} else
				l = mid + 1;
		}
		return ret;
	}
}
