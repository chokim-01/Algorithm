#include <iostream>
#include <queue>
#include <vector>
#include <climits>
using namespace std;

int N, M;
vector<vector<char>> map;
vector<vector<int>> tMap;
vector<vector<int>> dxy = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
vector<int> s, e;

struct Node {
    int x, y, save;
    Node(int x, int y) : x(x), y(y), save(INT_MAX) {}
    Node(int x, int y, int s) : x(x), y(y), save(s) {}
    bool operator<(const Node& other) const {
        return save < other.save;
    }
};

void treeMapSet() {
    queue<Node> q;
    for (int i = 0; i < N; i++) {
        fill(tMap[i].begin(), tMap[i].end(), INT_MAX);
        for (int j = 0; j < M; j++) {
            if (map[i][j] == '+') {
                tMap[i][j] = 0;
                q.push(Node(i, j, 0));
            }
            else if (map[i][j] == 'V') {
                s = { i, j };
            }
            else if (map[i][j] == 'J') {
                e = { i, j };
            }
        }
    }

    while (!q.empty()) {
        Node n = q.front();
        q.pop();

        for (int i = 0; i < dxy.size(); i++) {
            int nx = n.x + dxy[i][0];
            int ny = n.y + dxy[i][1];
            if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                continue;
            if (tMap[nx][ny] <= n.save+1)
                continue;
            tMap[nx][ny] = n.save + 1;
            q.push(Node(nx, ny, n.save + 1));
        }
    }
}

int solve() {
    priority_queue<Node> q;

    q.push(Node(s[0], s[1], tMap[s[0]][s[1]]));
    tMap[s[0]][s[1]] = -1;
    while (!q.empty()) {
        Node n = q.top();
        q.pop();
        for (int i = 0; i < dxy.size(); i++) {
            int nx = n.x + dxy[i][0];
            int ny = n.y + dxy[i][1];
            if (nx < 0 || ny < 0 || nx >= N || ny >= M || tMap[nx][ny] == -1)
                continue;
            int nsave = min(n.save, tMap[nx][ny]);
            tMap[nx][ny] = -1;
            if (nx == e[0] && ny == e[1])
                return nsave;
            q.push(Node(nx, ny, nsave));
        }
    }
    return 0;
}

void input() {
    cin >> N >> M;
    map.resize(N, vector<char>(M));
    tMap.resize(N, vector<int>(M));
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            cin >> map[i][j];
        }
    }
    
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    input();
    treeMapSet();
    cout << solve() << endl;
    return 0;
}