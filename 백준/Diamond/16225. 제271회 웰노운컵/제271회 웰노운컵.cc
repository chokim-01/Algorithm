#include <vector>
#include <iostream>
#include <algorithm>
#include <queue>


using namespace std;

pair<int, int> paiir[200001];

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(0);
	cout.tie(0);

	int N;
	cin >> N;
	
	for (int i = 0; i < N; i++)
		cin >> paiir[i].second;
	for (int i = 0; i < N; i++)
		cin >> paiir[i].first;

	sort(paiir, paiir + N);
	unsigned long long ans = 0;
	ans += paiir[0].second;
	priority_queue<int,vector<int>,greater<>> q;
	for (int i = N - 2; i > 0; i -= 2) {
		q.push(paiir[i].second);
		q.push(paiir[i-1].second);
		q.pop();
	}
	while (q.size()) {
		ans += q.top();
		q.pop();
	}
	cout << ans;
}

