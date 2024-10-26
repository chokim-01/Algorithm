import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    static int A, B;
    static int[] arrA, arrB;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input(br);

        Comparator<int[]> comparator = (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0];

        PriorityQueue<int[]>[] pqA = new PriorityQueue[A + 1];
        PriorityQueue<int[]>[] pqB = new PriorityQueue[B + 1];
        for (int i = 0; i <= A; i++)
            pqA[i] = new PriorityQueue<>(comparator);
        for (int i = 0; i <= B; i++)
            pqB[i] = new PriorityQueue<>(comparator);

        for (int i = A; i > 0; i--) {
            int a = arrA[i];
            for (int j = i - 1; j >= 0; j--)
                pqA[j].add(new int[]{a, i});
        }
        for (int i = B; i > 0; i--) {
            int b = arrB[i];
            for (int j = i - 1; j >= 0; j--)
                pqB[j].add(new int[]{b, i});
        }

        ArrayList<Integer> ans = new ArrayList<>();

        int aIdx = 0;
        int bIdx = 0;
        while (true) {
            if (pqA[aIdx].isEmpty() || pqB[bIdx].isEmpty())
                break;
            if (pqA[aIdx].peek()[0] == pqB[bIdx].peek()[0]) {
                int[] v = pqA[aIdx].poll();
                ans.add(v[0]);
                aIdx = v[1];
                bIdx = pqB[bIdx].poll()[1];
            } else if(pqA[aIdx].peek()[0] < pqB[bIdx].peek()[0]){
                pqB[bIdx].poll();
            } else
                pqA[aIdx].poll();
        }

        System.out.println(ans.size());
        for(int a : ans)
            System.out.print(a + " ");
    }

    static ArrayList<Integer> ans;

    static void input(BufferedReader br) throws IOException {
        A = Integer.parseInt(br.readLine());
        arrA = Stream.of(("0 " + br.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();
        B = Integer.parseInt(br.readLine());
        arrB = Stream.of(("0 " + br.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();
     }
}
