import java.util.*;

class Solution {
    static class Node implements Comparable<Node> {
        int x, fixCnt, time;
        public Node(int x, int time, int fixCnt) {
            this.x = x;
            this.time = time;
            this.fixCnt = fixCnt;
        }
        @Override
        public int compareTo(Node o) {
            if(this.fixCnt == o.fixCnt)
                return -this.time + o.time;
            return this.fixCnt - o.fixCnt;
        }
        
    }
    static LinkedList<Integer> edges[];
    
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        edges = new LinkedList[n+1];
        
        for(int i = 0;i<n+1;i++)
            edges[i] = new LinkedList<Integer>();
        
        for(int i = 0;i < m;i++) {
            int a = edge_list[i][0];
            int b = edge_list[i][1];
            edges[a].add(b);
            edges[b].add(a);
        }
        
        return bfs(gps_log);
    }
    
    static int bfs(int[] gps_log) {
        PriorityQueue<Node> q = new PriorityQueue<Node>();
        q.add(new Node(gps_log[0], 1, 0));
        
        boolean[][] visit = new boolean[edges.length][gps_log.length+1];
        // x의 t에 fixcnt저장
        visit[gps_log[0]][1] = true;
        
        while(!q.isEmpty()) {
            Node n = q.poll();
            
            if(n.time == gps_log.length) {
                if(n.x != gps_log[n.time-1])
                    continue;
                return n.fixCnt;
            }
            for(int i : edges[n.x]) {
                if(visit[i][n.time])
                    continue;
                visit[i][n.time] = true;
                if(i == gps_log[n.time]) {
                    q.add(new Node(i,n.time+1,n.fixCnt));
                }
                else {
                    if(n.time+1 != gps_log.length)
                        q.add(new Node(i,n.time+1,n.fixCnt+1));
                }
            }
        }
        
        return -1;
    }
}