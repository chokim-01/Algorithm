#include <iostream>
#include <vector>
#include <sstream>

using namespace std;

struct Node {
    Node* parent;
    int num;

    Node(int n, Node* p) : num(n), parent(p) {}
};

int N;
Node* list[80001];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> N;
    stringstream ans;

    list[0] = new Node(-1, nullptr);

    for (int k = 1; k <= N; k++) {
        char c;
        int n;
        cin >> c;

        if (c == 'a') {
            cin >> n;
            list[k] = new Node(n, list[k - 1]);
            ans << n;
        }
        else if (c == 's') {
            list[k] = list[k - 1]->parent;
            if (list[k]->parent == nullptr)
                ans << -1;
            else
                ans << list[k]->num;
        }
        else if (c == 't') {
            cin >> n;
            list[k] = list[n - 1];
            if (list[k]->parent == nullptr)
                ans << -1;
            else
                ans << list[k]->num;
        }

        ans << "\n";
    }
    cout << ans.str();
    return 0;
}
