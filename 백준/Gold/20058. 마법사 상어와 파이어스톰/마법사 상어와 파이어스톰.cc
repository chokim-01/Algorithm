#include<iostream>
#include<queue>
#include<vector>
#include<cstring>
#include<algorithm>
using namespace std;
int N, Q;
vector<int> LL;
bool visited[100][100];
bool bfs_visited[100][100];
int map[100][100];
int temp[100][100];
int dy[] = { -1,1,0,0 };
int dx[] = { 0,0,1,-1 };
int sum;
int answer;
int bfs(int y, int x) {
	int cnt = 1;
	queue<pair<int, int>> q;
	q.push({ y,x });
	bfs_visited[y][x] = true;
	while (!q.empty()) {
		int y = q.front().first;
		int x = q.front().second;
		q.pop();
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
			
			if (!bfs_visited[ny][nx] && map[ny][nx] > 0) {
				q.push({ ny,nx });
				bfs_visited[ny][nx] = true;
				cnt++;
			}
		}
	}
	return cnt;
}
bool melting(int y, int x) {
	int cnt = 0;
	for (int i = 0; i < 4; i++) {
		int ny = y + dy[i];
		int nx = x + dx[i];
		if (ny < 0 || nx < 0 || nx >= N || ny >= N) continue;
		if (map[ny][nx] > 0) {
			cnt++;
		}
	}
	if (cnt >= 3) return true;
	return false;
}
void rotate(int y,int x, int cnt) {
	for (int i = 0; i < cnt; i++) {
		for (int j = 0; j < cnt; j++) {
			temp[i + y][j + x] = map[y+cnt -1 - j][i+x];
		}
	}
	
	for (int i = 0; i < cnt; i++) {
		for (int j = 0; j < cnt; j++) {
			map[i + y][j + x] = temp[i + y][j + x];
		}
	}
}
void solve() {
	for(int i=0;i<LL.size();i++){
		//1.격자나누기
		int cnt = (1 << LL[i]);
		for (int y = 0; y < N; y+=cnt) {
			for (int x = 0; x < N; x+=cnt) {
				rotate(y,x,cnt);
			}
		}
		//2.얼음줄이기
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				if (melting(y, x) == false) {
					visited[y][x] = true;
				}
			}
		}
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				if (visited[y][x]==true) {
					if(map[y][x]>0) map[y][x]--;
				}
			}
		}
		memset(visited, false, sizeof(visited));
	
	}
	//남아있는 얼음의합
	for (int y = 0; y < N; y++) {
		for (int x = 0; x < N; x++) {
			sum += map[y][x];
		}
	}
	//남아있는 얼음중 가장 큰 덩어리가 차지하는 칸의 개수 
	for (int y = 0; y < N; y++) {
		for (int x = 0; x < N; x++) {
			if (!bfs_visited[y][x] && map[y][x] > 0) {
				answer= max(answer,bfs(y, x));
			}
		}
	}
}
int main() {
	cin >> N >> Q;
	N = (1 << N);
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> map[i][j];
		}
	}
	for (int i = 0; i < Q; i++) {
		int a;
		cin >> a;
		LL.push_back(a);
	}
	
	solve();
	cout << sum << '\n' << answer;
	return 0;
}