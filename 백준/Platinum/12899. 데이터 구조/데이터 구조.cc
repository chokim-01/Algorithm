#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

const int N = 2097152;

class Segment {
public:
    vector<int> tree;

    Segment() {
        tree.resize(N << 1, 0);
    }

    void update(int idx, int v) {
        idx += N;
        tree[idx] += v;
        while ((idx /= 2) != 0)
            tree[idx] = tree[idx << 1] + tree[idx << 1 | 1];
    }

    int query(int l, int r) {
        int ret = 0;
        l += N;
        r += N + 1;
        for (; l < r; l >>= 1, r >>= 1) {
            if (l & 1)
                ret += tree[l++];
            if (r & 1)
                ret += tree[--r];
        }
        return ret;
    }

    int binarySearch(int x) {
        int ret = 0;
        int l = 0;
        int r = 2000000;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (query(0, mid) >= x) {
                ret = mid;
                r = mid;
            }
            else {
                l = mid + 1;
            }
        }
        return ret;
    }
};

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    Segment seg;

    int N;
    cin >> N;

    while (N-- > 0) {
        int a, b;
        cin >> a >> b;

        if (a == 1) {
            seg.update(b, 1);
        }
        else {
            int r = seg.binarySearch(b);
            seg.update(r, -1);
            cout << r << "\n";
        }
    }

    return 0;
}
