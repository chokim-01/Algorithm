#include <iostream>
using namespace std;

int main(){
  int N, M;
  cin >> N >> M;
  int count = 1;
  while(true){
    if(M==N) {
      cout << count;
      break;
    }
    if(M < N) {
      cout << -1;
      break;
    }
    if(M%2 == 0 || (M-1)%10 == 0){
      if(M%2==0){
        M = M/2;
        count++;
      }
      if(M==N){
          cout << count;
          break;
      }
      if((M-1)%10 == 0){
        M = (M-1)/10;
        count++;
      }
    }
    else{
      cout << -1;
      break;
    }
  }
}