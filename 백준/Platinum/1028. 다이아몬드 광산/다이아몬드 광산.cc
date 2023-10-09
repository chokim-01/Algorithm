#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int N, M;
vector<vector<bool>> map;
vector<vector<vector<int>>> countMap;

struct Node {
    int x, y;

    Node(int x, int y) : x(x), y(y) {}
};

bool mapChk(int x, int y) {
    if (x < 0 || y < 0 || x >= N || y >= M)
        return false;
    return true;
}

void make(queue<Node>& q, bool flag) {
    int bef = 0;
    while (!q.empty()) {
        bef = 0;
        Node n = q.front();
        q.pop();
        int ni = n.x;
        int nj = n.y;
        while (true) {
            if (!mapChk(ni, nj))
                break;
            if (map[ni][nj])
                bef++;
            else
                bef = 0;
            countMap[ni][nj][flag ? 0 : 1] = bef;
            ni -= 1;
            nj = nj + (flag ? 1 : -1);
        }
    }
}

void makeCountMap() {
    queue<Node> q;
    for (int i = 0; i < N; i++)
        q.push(Node(i, 0));
    for (int j = 1; j < M; j++)
        q.push(Node(N - 1, j));
    make(q, true);
    q = queue<Node>();
    for (int i = 0; i < N; i++)
        q.push(Node(i, M - 1));
    for (int j = 0; j < M - 1; j++)
        q.push(Node(N - 1, j));
    make(q, false);
}

void input() {
    cin >> N >> M;
    map.resize(N, vector<bool>(M, false));
    countMap.resize(N, vector<vector<int>>(M, vector<int>(2, 0)));

    for (int i = 0; i < N; i++) {
        string s;
        cin >> s;
        for (int j = 0; j < M; j++)
            map[i][j] = (s[j] == '1');
    }
}

int findDia() {
    int ret = 0;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            int minVal = min(countMap[i][j][0], countMap[i][j][1]);
            if (minVal <= ret)
                continue;
            while (minVal > ret) {
                int X = i + (minVal - 1);
                int leftY = j - (minVal - 1);
                int rightY = j + (minVal - 1);
                minVal--;
                if (!mapChk(X, leftY) || !mapChk(X, rightY))
                    continue;
                if (countMap[X][rightY][0] < minVal + 1 || countMap[X][leftY][1] < minVal + 1)
                    continue;
                int min2 = min(countMap[X][leftY][1], countMap[X][rightY][0]);
                int m = min(minVal + 1, min2);
                if (ret < m)
                    ret = m;
            }
        }
    }
    return ret;
}

int main() {
    input();
    makeCountMap();
    int ans = findDia();
    cout << ans << endl;

    return 0;
}