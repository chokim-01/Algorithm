class Solution {
    public int solution(int[] money) {
        
        int[] first_dp = new int[money.length]; // choose fist
        int[] not_dp = new int[money.length]; // choose not
        
        first_dp[0] = money[0]; // 무조건 선택
        first_dp[1] = money[0];
        not_dp[1] = money[1];
        
        for(int i = 2;i<money.length;i++) {
            not_dp[i] = Math.max(not_dp[i-2] + money[i], not_dp[i-1]);
            
            if(money.length-1 == i) {
                first_dp[i] = first_dp[i-1];
                break;
            }
            first_dp[i] = Math.max(first_dp[i-2] + money[i], first_dp[i-1]);
        }
        int answer = 0;
        return Math.max(first_dp[money.length-1], not_dp[money.length-1]);
    }
}