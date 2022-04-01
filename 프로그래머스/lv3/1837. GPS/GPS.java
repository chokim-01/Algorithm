import java.util.*;

class Solution {
    static boolean edges[][];
    
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        edges = new boolean[n+1][n+1];
    
        for(int i = 0;i < m;i++) {
            int a = edge_list[i][0];
            int b = edge_list[i][1];
            edges[a][b] = true;
            edges[b][a] = true;
        }
        
        int[][] dp = new int[k][n+1];
        
        for(int i = 0;i<k;i++)
            for(int j = 0;j<n+1;j++)
                dp[i][j] = 12345678;
        
        dp[0][gps_log[0]] = 0;
        
        for(int i = 1;i<k;i++) { // 시간
            for(int j = 1;j<n+1;j++) { // 노드
                dp[i][j] = Math.min(dp[i][j], dp[i-1][j]);
                
                for(int a = 1;a<n+1;a++) {
                    if(!edges[j][a])
                        continue;
                    dp[i][j] = Math.min(dp[i-1][a],dp[i][j]);
                }
                if(j != gps_log[i])
                    dp[i][j]+=1;
                
            }
        }
        
        return dp[k-1][gps_log[k-1]] < 12345678? dp[k-1][gps_log[k-1]] : -1;
    }
    
    
}