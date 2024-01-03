#include <bits/stdc++.h>

using namespace std;
using ll = long long;
const int MOD = 998244353;
const int MAX_N = 10000001;

struct UF {
    int par[MAX_N]{};

    int find(int a) {
        if (par[a] == a) return a;
        return par[a] = find(par[a]);
    }

    bool merge(int a, int b) {
        a = find(a), b = find(b);
        if (a == b)return false;
        par[a] = b;
        return true;
    }

    explicit UF() {
        for (int i = 0; i < MAX_N; ++i) par[i] = i;
    }

} uf;


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int n;
    cin >> n;

    vector<int> p;
    vector<int> q;
    for (int i = 2; i * i <= n + n - 1; ++i) {
        if ((i * i) & 1)
            p.push_back(i * i);
        else
            q.push_back(i * i);
    }

    ll ans = 1;
    int cnt = 0;
    int c = 0;
    for (int i = 1; i <= n; ++i) {
        vector<int> r;
        if (i & 1)
            r = p;
        else
            r = q;
        for (auto j : r) {
            c++;
            int l = j / 2 - i / 2;
            int r = (j & 1) ? j / 2 + i / 2 + 1 : j / 2 + i / 2;
            if (l <= 0 || r > n) continue;
            if (uf.merge(l, r)) {
                ++cnt;
                ans = (ans * i) % MOD;
            }
            if (cnt == n - 1)
                break;
        }
        if(cnt == n-1)
            break;
    }

    if (cnt == n - 1)cout << ans;
    else cout << -1;


}