#include <cstdlib>
#include <vector>
#include <iostream>
#include <algorithm>
#include <cstring>

using namespace std;
int hitterHit[51][9];
int n,c,ans = 0;

void dfs(int[], int);
int calc(int[]);

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL), cout.tie(NULL);
    
    cin >> n;
    for (int i = 0; i < n; i++)
        for (int j = 0; j < 9; j++)
            cin >> hitterHit[i][j];

    int hit[] = {0,1,2,3,4,5,6,7,8};
    
    dfs(hit, 0);

    cout << ans;
    return 0;

}


void dfs(int a[], int b) {
    
    if (b == 9) {
        
        if (a[3] == 0) {
            int score = calc(a);
            ans = score < ans ? ans : score;
        }
        return;
    }
    for (int i = b; i < 9; i++) {
        swap(a[b], a[i]);
        dfs(a, b + 1);
        swap(a[b], a[i]);
    }
}
int calc(int a[]) {

    bool point[3] = { false };

    int out = 0, inning = 0, score = 0;
    int index = 0;

    while (1) {
        if (hitterHit[inning][a[index]] == 0) {
            
            out += 1;
            if (out >= 3) {
                fill_n(point, 3, false);
                
                inning += 1;
                if (inning >= n)
                    break;
                
                out = 0;
                if (++index >= 9)
                    index = 0;
                continue;
            }
        }
        else if (hitterHit[inning][a[index]] == 1) {
            if (point[2]) {
                point[2] = false;
                score += 1;
            }
            if (point[1]) {
                point[2] = true;
                point[1] = false;
            }
            if (point[0]) {
                point[1] = true;
                point[0] = false;
            }
            point[0] = true;
        }
        else if (hitterHit[inning][a[index]] == 2) {
            if (point[2]) {
                score += 1;
                point[2] = false;
            }
            if (point[1]) {
                score += 1;
                point[1] = false;
            }
            if (point[0]) {
                point[2] = true;
                point[0] = false;
            }
            point[1] = true;
        }
        else if (hitterHit[inning][a[index]] == 3) {
            for (int i = 0; i < 3; i++) {
                if (point[i]) {
                    score += 1;
                    point[i] = false;
                }
            }
            point[2] = true;
        }
        else {
            for (int i = 0; i < 3; i++) {
                if (point[i]) {
                    score += 1;
                    point[i] = false;
                }
            }
            score += 1;
        }
        if (++index >= 9)
            index = 0;
    }
    return score;
}

