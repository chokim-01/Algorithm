import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    static int N;
    static long C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        int[] array = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        long ans = solve(array);

        System.out.println(ans);
    }

    static long solve(int[] array) {
        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);

        ArrayList<Long> leftList = new ArrayList<>();
        dfs(0, 0, left, leftList);

        ArrayList<Long> rightList = new ArrayList<>();
        dfs(0, 0, right, rightList);

        Collections.sort(leftList);
        Collections.sort(rightList);

        TreeMap<Long, Integer> leftFrequencyMap = new TreeMap<>();
        for (long num : leftList)
            leftFrequencyMap.put(num, leftFrequencyMap.getOrDefault(num, 0) + 1);

        long ans = 0;
        for (Map.Entry<Long, Integer> map : leftFrequencyMap.entrySet()) {
            if (map.getKey() > C)
                break;
            ans += map.getValue() * binSearch(C-map.getKey(), rightList);
        }

        return ans;
    }

    static long binSearch(long x, ArrayList<Long> list) {
        int l = 0;
        int r = list.size() - 1;
        long ret = 0;

        while (l <= r) {
            int mid = (l + r) >> 1;
            if (list.get(mid) <= x) {
                ret = mid;
                l = mid + 1;
            } else
                r = mid - 1;
        }

        return ret + 1;
    }

    static void dfs(int idx, long sum, int[] list, ArrayList<Long> made) {
        if(sum > C)
            return;
        if (idx == list.length) {
            made.add(sum);
            return;
        }

        dfs(idx + 1, sum + list[idx], list, made);
        dfs(idx + 1, sum, list, made);
    }
}
