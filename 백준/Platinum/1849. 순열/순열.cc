#include <iostream>
#include <sstream>
#include <algorithm>

using namespace std;

const int MAX_N = 100000;
int N;
int tree[2 * MAX_N];

void update(int id) {
    id += N;
    do {
        tree[id] -= 1;
    } while ((id >>= 1) != 0);
}

int query(int l, int r) {
    int ret = 0;
    l = l + N;
    r = r + N;
    for (; l < r; l >>= 1, r >>= 1) {
        if ((l & 1) == 1)
            ret += tree[l++];
        if ((r & 1) == 1)
            ret += tree[--r];
    }
    return ret;
}

void init() {
    for (int i = N; i < 2 * N; i++) {
        int n = i;
        do {
            tree[n] += 1;
        } while ((n >>= 1) != 0);
    }
}

int main() {
    cin >> N;
    cin.ignore();

    init();
    int ans[MAX_N];
    int n = 0;
    int cnt = N;
    while (cnt-- > 0) {
        int num;
        cin >> num;
        cin.ignore();

        int l = 0;
        int r = N;
        int re = 0;
        while (l <= r) {
            int mid = (l + r) / 2;
            int q = query(0, mid);
            if (q <= num) {
                l = mid + 1;
                re = mid;
            }
            else
                r = mid - 1;
        }
        update(re);
        ans[re] = ++n;
    }

    for (int i = 0; i < N; i++) {
        cout << ans[i] << "\n";
    }

    return 0;
}
