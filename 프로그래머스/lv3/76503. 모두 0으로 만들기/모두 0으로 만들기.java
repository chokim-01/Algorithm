import java.util.*;

class Solution {
    static long sum = 0;
    static List<Integer>[] connect;
    static long[] weight;
    
    public long solution(int[] a, int[][] edges) {
        int sz = a.length;

        // init
        connect = new List[sz];
        weight = new long[sz];
        for(int i = 0; i < sz; i++){
            sum += a[i];
            weight[i] = a[i];
            connect[i] = new ArrayList<Integer>();
        }
        
        // no answer case
        if(sum != 0) return -1;
        
        // connection
        for(int[] e : edges){
            int e1 = e[0], e2 = e[1];
            connect[e1].add(e2);
            connect[e2].add(e1);
        }
        
        // DFS
        dfs(0,0);
        return sum;
    }
    void dfs(int cur, int parent){
        // 자식으로 내려가기
        for(int i = 0; i < connect[cur].size(); i++){
            if(connect[cur].get(i) == parent) continue;
            dfs(connect[cur].get(i), cur);
        }
        
        // 현재 값에서 부모로 올리기
        weight[parent] += weight[cur];
        sum += Math.abs(weight[cur]);
        
    }
    
}