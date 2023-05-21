#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int N;
vector<int> numbers;
vector<vector<int>> tree;

void init() {
    tree.resize(N * 2);
    for (int i = 0; i < tree.size(); i++)
        tree[i] = vector<int>();
    for (int i = N; i < N + numbers.size(); i++) {
        int n = i;
        do {
            tree[n].push_back(numbers[i - N]);
        } while ((n /= 2) != 0);
    }
    for (int i = 0; i < N; i++)
        sort(tree[i].begin(), tree[i].end());
}

int query(int l, int r, int key) {
    int res = 0;
    for (; l < r; l >>= 1, r >>= 1) {
        if (l & 1) {
            if (tree[l][0] > key)
                res += tree[l].size();
            else if (tree[l][tree[l].size() - 1] <= key)
                ;
            else
                res += tree[l].size() - (upper_bound(tree[l].begin(),tree[l].end(), key)-tree[l].begin());
            l++;
        }

        if (r & 1) {
            --r;
            if (tree[r][0] > key)
                res += tree[r].size();
            else if (tree[r][tree[r].size() - 1] <= key)
                ;
            else
                res += tree[r].size() - (upper_bound(tree[r].begin(), tree[r].end(), key)-tree[r].begin());
        }
    }
    return res;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> N;

    numbers.resize(N);
    for (int i = 0; i < N; i++)
        cin >> numbers[i];

    init();

    int M;
    cin >> M;
    int last_ans = 0;
    while (M-- > 0) {
        int a, b, k;
        cin >> a >> b >> k;
        a = a ^ last_ans;
        b = b ^ last_ans;
        k = k ^ last_ans;
        last_ans = query(a + N - 1, b + N, k);
        cout << last_ans << "\n";
    }

    return 0;
}