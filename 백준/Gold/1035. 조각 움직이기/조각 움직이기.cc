#include <iostream>
#include <vector>
#include <queue>
#include <bitset>
#include <algorithm>

using namespace std;

const int N = 5;
const int SIZE = 25;
const int MAX_MASK = 1 << SIZE;

int map[N][N];
int save[SIZE][SIZE];
vector<int> stars;
vector<vector<int>> ansList;
int minCost = 987654321;

const int dt[] = { 1, -1, -5, 5 };
const int dxy[][2] = { {1, 0}, {0, 1}, {0, -1} };

void init();
void input();
void findAnsList();
void dfs(int depth);
void swap(int x, int y);
void bfs();
bool matched(int map, vector<int>& list);
bool mapChk(int d, int x);

int main() {
    init();
    input();
    bfs();
    findAnsList();
    dfs(0);
    cout << minCost << endl;
    return 0;
}

void init() {
    stars.clear();
    ansList.clear();
}

void input() {
    for (int i = 0; i < N; i++) {
        string row;
        cin >> row;
        for (int j = 0; j < N; j++) {
            map[i][j] = (row[j] == '.') ? 0 : 1;
            if (map[i][j] == 1)
                stars.push_back(24 - (i * N + j));
        }
    }
}

void findAnsList() {
    int count = stars.size();
    for (int i = 1; i < MAX_MASK; i++) {
        int sum = bitset<25>(i).count();
        if (sum == count) {
            vector<int> list;
            if (matched(i, list))
                ansList.push_back(list);
        }
    }
}

void dfs(int depth) {
    if (depth == stars.size()) {
        for (const auto& ans : ansList) {
            int cost = 0;
            for (int j = 0; j < stars.size() && cost <= minCost; j++) {
                int a = ans[j];
                int b = stars[j];
                cost += save[a][b];
            }
            minCost = min(minCost, cost);
        }
    }
    for (int i = depth; i < stars.size(); i++) {
        swap(depth, i);
        dfs(depth + 1);
        swap(depth, i);
    }
}

void swap(int x, int y) {
    int tmp = stars[x];
    stars[x] = stars[y];
    stars[y] = tmp;
}

void bfs() {
    bool v[N][N];
    queue<vector<int>> q;

    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            fill(&v[0][0], &v[0][0] + sizeof(v) / sizeof(bool), false);
            while (!q.empty())
                q.pop();
            int a = 24 - (i * N + j);

            q.push({ i, j, 0 });
            v[i][j] = true;

            while (!q.empty()) {
                auto n = q.front();
                q.pop();
                for (int d = 0; d < 3; d++) {
                    int nx = n[0] + dxy[d][0];
                    int ny = n[1] + dxy[d][1];
                    if (ny < 0 || nx >= N || ny >= N || v[nx][ny])
                        continue;
                    v[nx][ny] = true;
                    int b = 24 - (nx * N + ny);
                    int c = n[2] + 1;
                    save[a][b] = c;
                    save[b][a] = c;
                    q.push({ nx, ny, c });
                }
            }
        }
    }
}

bool matched(int map, vector<int>& list) {
    for (int i = 0; i < SIZE; i++) {
        if ((map & (1 << i)) == (1 << i)) {
            list.push_back(i);
            break;
        }
    }
    bitset<SIZE> v;
    queue<int> q;
    q.push(list[0]);
    v[list[0]] = true;
    int c = 1;
    while (!q.empty()) {
        int now = q.front();
        q.pop();
        for (int d = 0; d < 4; d++) {
            if (!mapChk(dt[d], now))
                continue;
            int next = now + dt[d];
            if (v[next] || ((1 << next) & map) != (1 << next))
                continue;
            v[next] = true;
            q.push(next);
            list.push_back(next);
            c++;
        }
    }
    return (c == stars.size());
}

bool mapChk(int d, int x) {
    if (d == -1 && x % 5 == 0)
        return false;
    if (d == 1 && (x + 1) % 5 == 0)
        return false;
    if (d == -5 && x < 5)
        return false;
    if (d == 5 && x > 19)
        return false;
    return true;
}
