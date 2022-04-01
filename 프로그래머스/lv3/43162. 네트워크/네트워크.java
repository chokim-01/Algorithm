import java.util.*;

class Solution {
    static boolean visit[];
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        visit = new boolean[n];
        
        for(int i = 0;i<n;i++) {
            if(visit[i])
                continue;
            bfs(i, computers);
            answer+=1;    
        }
        
        return answer;
    }
    static void bfs(int x, int[][] computers) {
        Queue<Integer> que = new LinkedList<>();
        que.add(x);
        visit[x] = true;
        while(!que.isEmpty()) {
            int now = que.poll();
            
            for(int i = 0;i<computers.length;i++) {
                if(i == now || visit[i] || computers[now][i] == 0)
                    continue;
                visit[i] = true;
                que.add(i);
            }
            
        }
    }
}