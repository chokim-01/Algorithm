#include <vector>

int binSearch(std::vector<int>& c, int f) {
    int ret = 0;
    int l = 0;
    int r = 44999;

    while (l <= r) {
        int mid = (l + r) / 2;
        if (c[mid] >= f) {
            ret = mid;
            r = mid - 1;
        }
        else {
            l = mid + 1;
        }
    }

    return ret;
}

int kth(std::vector<int>& a, int k) {
    std::vector<int> cA(45000);
    std::vector<int> cB(45000);

    for (int i = 0; i < a.size(); i++) {
        a[i] += 1000000000;
        cA[a[i] / 45000]++;
    }

    for (int i = 1; i < cA.size(); i++) {
        int n = i - 1;
        cA[i] += cA[n];
    }

    int find = binSearch(cA, k);

    for (int i = 0; i < a.size(); i++) {
        if (a[i] / 45000 != find)
            continue;
        cB[a[i] % 45000]++;
    }

    for (int i = 1; i < cB.size(); i++) {
        int n = i - 1;
        cB[i] += cB[n];
    }

    int cnt = k - (find == 0 ? 0 : cA[find - 1]);
    int find2 = binSearch(cB, cnt);
    int ans = find * 45000 + find2 - 1000000000;
    return ans;
}