import java.io.*;
import java.util.*;

public class Main
{
    

    public static void main(String[] args) throws Exception
    {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true)
        {
            String[] line = br.readLine().split(" ");
            int k =  Integer.parseInt(line[0]);

            if (k == 0) break;
            int[] S = new int[k];

            for(int i = 1;i<line.length;i++)
                S[i-1] = Integer.parseInt(line[i]);
            
            
            num_list = new ArrayList<>();
            combi(S, new boolean[k], k, 0, 0);
            // 리스트에 정렬된 체로 다 받아졌다리.
            for (int i = 0; i < num_list.size(); i++)
            {
                ArrayList<Integer> num = num_list.get(i).nums;
                for (int j = 0; j < 6; j++)
                {
                    sb.append(num.get(j)).append(" ");
                }
                sb.append("\n");
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        
        bw.flush();
    }
    private static ArrayList<Num> num_list = null;

    private static class Num
    {
        ArrayList<Integer> nums = null;
        Num(ArrayList<Integer> nums)
        {
            this.nums = nums;
        }
    }

    private static void combi(int[] S, boolean[] sel, int k, int n, int r)
    {
        if( r == 6 )
        {
            // 배열에 선택된 것을 넣는다.
            ArrayList<Integer> temp = new ArrayList<>();
            for (int i = 0; i < k; i++)
            {
                if (sel[i])
                {
                    temp.add(S[i]);
                }
            }
            // 우선 리스트에 넣어둔다. 정렬은 다음에!
            num_list.add(new Num(temp));
            return;
        }
        if (n == k)
        {
            // 나가리!
            return;
        }
        sel[n] = true;
        combi(S, sel, k, n+1, r+1);
        sel[n] = false;
        combi(S, sel, k, n+1, r);
    }
}