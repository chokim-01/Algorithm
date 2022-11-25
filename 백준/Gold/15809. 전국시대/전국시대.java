import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N, M;
    public static int[] arr, cost;
    public static boolean[] visited;
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        cost = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            cost[i] = Integer.parseInt(br.readLine());
            arr[i] = i;
        }
        for(int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());

            int command = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            union(from, to, command);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 1; i <= N; i++) {
            if(cost[i] > 0) {
                pq.add(cost[i]);
            }
        }
        int s = pq.size();
        bw.write(Integer.toString(s));
        bw.newLine();
        while(!pq.isEmpty()) {
            int current = pq.poll();
            boolean abs = current < 0;
            if(abs) {
                current *= -1;
                bw.write(45);
            }
            int size = current == 0 ? 0 : (int) Math.log10(current);
            while(size >= 0) {
                int nextSize = current == 0 ? 0 : (int) Math.log10(current);
                int div = (int) Math.pow(10, nextSize);
                while(size > nextSize) {
                    bw.write(48);
                    size--;
                }
                if(div != 0) {
                    bw.write((current / div) + 48);
                    current %= div;
                } else {
                    bw.write(48);
                }
                size--;
            }
            if(s > 1) {
                bw.write(32);
            }
            s--;
        }
        bw.flush();
        bw.close();
    }
    public static void union(int from, int to, int command) {
        int x = find(from);
        int y = find(to);

        if(command == 1) {
            if(x < y) {
                arr[y] = arr[x];
                cost[x] += cost[y];
                cost[y] = 0;
            } else {
                arr[x] = arr[y];
                cost[y] += cost[x];
                cost[x] = 0;
            }
        } else {
            if(cost[x] == cost[y]) {
                arr[x] = 0;
                arr[y] = 0;
                cost[x] = 0;
                cost[y] = 0;
            } else if(cost[x] < cost[y]) {
                arr[x] = arr[y];
                cost[y] -= cost[x];
                cost[x] = 0;
            } else {
                arr[y] = arr[x];
                cost[x] -= cost[y];
                cost[y] = 0;
            }
        }
    }
    public static int find(int target) {
        if(target == arr[target]) {
            return target;
        }
        return arr[target] = find(arr[target]);
    }

}