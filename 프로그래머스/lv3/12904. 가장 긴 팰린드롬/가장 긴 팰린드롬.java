import java.util.*;
class Solution{
    public boolean check(String s, int i, int j){
        if(s.charAt(i+j) == s.charAt(i-j))
            return true;
        else
            return false;
    }
    public boolean check2(String s, int i, int j){
        if(s.charAt(i+1+j) == s.charAt(i-j))
            return true;
        else
            return false;
    }

    public int solution(String s){
        int answer = 1;//아무리짧아도 1
        int n = s.length();
        for(int i=0; i<n; i++){   //  ...jjijj... -> 홀수 처리
            for(int j=1; j<n; j++){
                if(i-j<0 || i+j>=n){
                    break;
                }
                if(check(s, i, j)){
                    if(j*2+1>answer){
                        answer = j*2+1;
                    }   
                }
                else{
                    break;
                }
            }

            for(int j=1; j<n; j++){ // ...jiij... -> 짝수 처리
                if(i == n-1 || s.charAt(i) != s.charAt(i+1))//짝수이려면 처음 붙은 두 글자는 같아야함.
                    break;

                if(i-j<0 || i+1+j>=n){
                    break;
                }
                if(check2(s, i, j)){
                    if(j*2+2>answer){
                        answer = j*2+2;
                    }   
                }
                else{
                    break;
                }
            }
        }




        return answer;
    }
}