#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

const int divv = 777;

class Bucket {
public:
    vector<int> list;
    vector<int> sortedList;

    void add(int v) {
        list.push_back(v);
        sortedList.push_back(v);
    }

    void sortByValue() {
        sort(sortedList.begin(), sortedList.end());
    }

    int search(int x, int l, int r, bool flag) {
        if (flag) {
            return list.size() - binSearch(x + 1, 0, list.size());
        }
        else {
            int c = 0;
            for (int i = l; i <= r; i++)
                if (list[i] > x)
                    c++;
            return c;
        }
    }

    int binSearch(int x, int l, int r) {
        int ret = list.size();
        while (l < r) {
            int mid = (l + r) >> 1;
            if (sortedList[mid] >= x) {
                r = mid;
                ret = mid;
            }
            else
                l = mid + 1;
        }
        return ret;
    }

    void change(int idx, int x) {
        int index = binSearch(list[idx], 0, list.size());
        sortedList[index] = x;
        list[idx] = x;
        sortByValue();
    }
};

class Order {
public:
    bool o;
    int i, j, k;

    Order(int i, int j, int k) : o(true), i(i), j(j), k(k) {}

    Order(int i, int k) : o(false), i(i), k(k) {}
};

int N;
int nums[100001];
Bucket bucket[777];
vector<Order> order;

void solve() {
    for (const auto& o : order) {
        if (o.o) {
            int v = 0;
            int c = 0;
            int i = o.i;
            int j = o.j;
            int a = o.i;
            int b = o.j;
            while (i <= j) {
                int n = i / divv;
                if (c == 0 || (i / divv + 1) * divv > j) {
                    int l = max(a - n * divv, 0);
                    int r = min(b - n * divv, divv - 1);
                    a = 0;
                    c++;
                    v += bucket[n].search(o.k, l, r, false);
                }
                else
                    v += bucket[n].search(o.k, -1, -1, true);
                i = (i / divv + 1) * divv;
            }
            cout << v << "\n";
        }
        else {
            int bIdx = o.i / divv;
            bucket[bIdx].change(o.i - o.i / divv * divv, o.k);
        }
    }
}

void setBucket() {
    for (int i = 0; i < divv; i++)
        bucket[i] = Bucket();
    for (int i = 0; i < N; i++)
        bucket[i / divv].add(nums[i]);
    for (int i = 0; i < divv; i++)
        bucket[i].sortByValue();
}

void input() {
    cin >> N;
    for (int i = 0; i < N; i++)
        cin >> nums[i];
    int M;
    cin >> M;
    order.clear();
    while (M-- > 0) {
        int o;
        cin >> o;
        int a, b, c;
        cin >> a >> b;
        if (o==2) {
            cin >> c;
            order.push_back(Order(a - 1, b - 1, c));
        }
        else {
            order.push_back(Order(a - 1, b));
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    input();
    setBucket();
    solve();

    return 0;
}
