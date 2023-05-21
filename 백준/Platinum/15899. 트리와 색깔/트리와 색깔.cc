#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int N, M, C, cnt = 0;
vector<int> in, out, colors, numbers;
vector<vector<int>> tree, link;
vector<bool> visit;

void dfs(int now) {
    in[now] = ++cnt;
    for (int next : link[now]) {
        if (visit[next])
            continue;
        visit[next] = true;
        dfs(next);
    }
    out[now] = cnt;
}

void init() {
    for (int i = 0; i < tree.size(); i++)
        tree[i].clear();
    for (int i = N; i < N << 1; i++) {
        int n = i;
        do {
            tree[n].push_back(colors[i - N]);
        } while ((n /= 2) != 0);
    }
    for (int i = 0; i < N; i++)
        sort(tree[i].begin(), tree[i].end());
}

int upperBound(vector<int>& arr, int key) {
    int front = 0;
    int rear = arr.size();
    while (front < rear) {
        int mid = (front + rear) / 2;
        if (arr[mid] <= key)
            front = mid + 1;
        else
            rear = mid;
    }
    return rear;
}

int query(int le, int key) {
    int l = in[le] + N - 1;
    int r = out[le] + N;
    int res = 0;
    for (; l < r; l >>= 1, r >>= 1) {
        if ((l & 1) == 1)
            res += upperBound(tree[l++], key);

        if ((r & 1) == 1)
            res += upperBound(tree[--r], key);
    }
    return res;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N >> M >> C;
    numbers.resize(N);
    for (int i = 0; i < N; i++)
        cin >> numbers[i];

    tree.resize(N * 2);
    link.resize(N + 1);
    visit.resize(N + 1);
    in.resize(N + 1);
    out.resize(N + 1);
    colors.resize(N);

    for (int i = 1; i <= N; i++)
        link[i].clear();

    for (int i = 0; i < N - 1; i++) {
        int a, b;
        cin >> a >> b;
        link[a].push_back(b);
        link[b].push_back(a);
    }

    visit[1] = true;
    dfs(1);

    colors.assign(N, 0);
    for (int i = 0; i < N; i++) {
        colors[in[i + 1] - 1] = numbers[i];
    }
    init();

    const long long mod = 1000000007;
    long long ans = 0;
    while (M-- > 0) {
        int a, b;
        cin >> a >> b;
        long long q = query(a, b);
        ans = (ans + q) % mod;
    }
    cout << ans << '\n';

    return 0;
}
