#include <iostream>
#include <bitset>
#include <cstring>
using namespace std;

class Trie {
    struct Node {
        Node* childs[2];
        int cnt;

        Node() {
            memset(childs, 0, sizeof(childs));
            cnt = 0;
        }
    };

    Node* head;

public:
    Trie() {
        head = new Node();
    }

    void insert(const string& str) {
        Node* now = head;
        for (int i = 0; i < str.length(); i++) {
            int c = str[i] - '0';
            if (now->childs[c] == nullptr) {
                now->childs[c] = new Node();
            }
            now = now->childs[c];
            now->cnt += 1;
        }
    }

    void remove(const string& str) {
        Node* now = head;
        for (int i = 0; i < str.length(); i++) {
            int c = str[i] - '0';
            now = now->childs[c];
            now->cnt -= 1;
        }
    }

    int xorOperation(const string& xo) {
        Node* now = head;
        int arr[30] = { 0 };
        for (int i = 0; i < xo.length(); i++) {
            int n = xo[i] - '0';
            if ((n ^ 0) == 0) {
                if (now->childs[1] != nullptr && now->childs[1]->cnt > 0) {
                    now = now->childs[1];
                    arr[i] = 1;
                }
                else {
                    now = now->childs[0];
                }
            }
            else {
                if (now->childs[0] != nullptr && now->childs[0]->cnt > 0) {
                    now = now->childs[0];
                    arr[i] = 1;
                }
                else {
                    now = now->childs[1];
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < 30; i++) {
            ans += arr[i] << (29 - i);
        }
        return ans;
    }
};

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    cin >> N;

    Trie trie;
    trie.insert(bitset<30>(0).to_string());

    for (int i = 0; i < N; i++) {
        int o, x;
        cin >> o >> x;
        string xo = bitset<30>(x).to_string();
        if (o == 1) {
            trie.insert(xo);
        }
        else if (o == 2) {
            trie.remove(xo);
        }
        else {
            cout << trie.xorOperation(xo) << "\n";
        }
    }

    return 0;
}