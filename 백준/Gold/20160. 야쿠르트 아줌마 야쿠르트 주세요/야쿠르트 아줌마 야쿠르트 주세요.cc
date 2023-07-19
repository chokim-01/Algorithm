#include <iostream>
#include <vector>
#include <queue>
#include <climits>
#include <algorithm>
#include <sstream>

using namespace std;

int N, M;
vector<int> route;
vector<vector<pair<int, int>>> link;

struct Node {
    int n, v;
    Node(int d, int v) : n(d), v(v) {}

    bool operator<(const Node& o) const {
        return v > o.v; // Note the ">" sign for min-heap behavior
    }
};

vector<long long> dij(int now);

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> N >> M;

    link.resize(N + 1);
    for (int i = 0; i <= N; i++)
        link[i].clear();

    for (int i = 0, a, b, c; i < M; i++) {
        cin >> a >> b >> c;
        link[a].emplace_back(b, c);
        link[b].emplace_back(a, c);
    }

    string line;
    getline(cin, line);
    getline(cin, line);
    istringstream iss(line);
    int num;
    while (iss >> num) {
        route.push_back(num);
    }

    int now;
    cin >> now;
    vector<long long> nCost = dij(now);

    int ans = INT_MAX;
    int yNow = route[0];
    long long yC = 0;
    // Find the smallest number for output
    if (route[0] == now)
        ans = route[0];
    for (int i = 1; i < 10; i++) {
        vector<long long> yCost = dij(yNow);
        if (yCost[route[i]] == LLONG_MAX)
            continue;

        yC += yCost[route[i]];
        if (nCost[route[i]] <= yC)
            if (ans > route[i])
                ans = route[i];

        yNow = route[i];
    }
    cout << (ans == INT_MAX ? -1 : ans) << endl;

    return 0;
}

vector<long long> dij(int now) {
    vector<long long> c(N + 1, LLONG_MAX);

    int oNow = now;

    priority_queue<Node> q;
    q.emplace(now, 0);
    c[now] = 0;
    while (!q.empty()) {
        Node n = q.top();
        q.pop();
        int cost = n.v;
        now = n.n;

        if (c[now] < cost)
            continue;
        for (const auto& next : link[now]) {
            long long nCost = c[now] + next.second;
            if (nCost >= c[next.first])
                continue;
            c[next.first] = nCost;
            q.emplace(next.first, nCost);
        }
    }
    c[oNow] = LLONG_MAX;
    return c;
}