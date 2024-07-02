#include <iostream>
#include <vector>
#include <sstream>
#include <string>
#include <algorithm>

using namespace std;

int N, S;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    string line;
    getline(cin, line);
    istringstream iss(line);
    iss >> N >> S;

    vector<long long> nums(N + 1, 0);
    getline(cin, line);
    istringstream issNums(line);

    for (int i = 1; i <= N; ++i) {
        long long num;
        issNums >> num;
        nums[i] = nums[i - 1] + num;
    }

    int ans = N + 2;
    int left = 1;
    int right = 1;

    while (right <= N) {
        if (nums[right] - nums[left - 1] < S)
            ++right;
        else
            ans = min(ans, right - left++ + 1);
    }

    cout << (ans == N + 2 ? 0 : ans) << '\n';

    return 0;
}
