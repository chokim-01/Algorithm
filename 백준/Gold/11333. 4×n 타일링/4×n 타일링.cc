#include <iostream>

using namespace std;

 

const int MAX = 10000;

const long long MOD = 1000000007;

 

long long cache[MAX];

 

void preCalc(void)

{

        cache[0] = 1;

        cache[3] = 3;

        cache[6] = 13;

 

        for (int i = 9; i < MAX; i += 3)

                 cache[i] = (((5 * cache[i - 3]) % MOD) + MOD - ((3 * cache[i - 6]) % MOD) + cache[i - 9]) % MOD;

}

 

int main(void)

{

        ios_base::sync_with_stdio(0);

        cin.tie(0);

        int T;

        cin >> T;

 

        preCalc();

        for (int t = 0; t < T; t++)

        {

                 int N;

                 cin >> N;

 

                 if (N % 3)

                         cout << 0 << "\n";

                 else

                         cout << cache[N] << "\n";

        }

        return 0;

}