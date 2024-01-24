#include <bits/stdc++.h>

using namespace std;

int tree[500010];
int n;

void update(int idx, int val) {
    while (idx < n + 1) {
        tree[idx] += val;
        idx += idx & -idx;
    }
}

int query(int idx) {
    int ret = 0;
    while (idx > 0) {
        ret += tree[idx];
        idx -= idx & -idx;
    }
    return ret;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    vector<int> nums, v, c;

    cin >> n;

    for (int i = 0,x; i < n; i++) {
        cin >> x;
        nums.push_back(x);
        v.push_back(x);
    }


    sort(nums.begin(), nums.end());
    nums.erase(unique(nums.begin(), nums.end()), nums.end());

    for (int i = 0; i < n; i++)
        c.push_back(lower_bound(nums.begin(), nums.end(), v[i]) - nums.begin() + 1);
    for (int i = 0; i < n; ++i) {
        cout << i + 1 - query(c[i]) << '\n';
        update(c[i], 1);
    }
}