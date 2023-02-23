import java.util.*;
class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
		// decimal to binary
		for (int i = 0; i < numbers.length; i++) {
			ArrayList<Boolean> list = decToBin(numbers[i]);
            int size = list.size();
            
            int n = 1;
            int n2 = 1;
            while(true) {
                if(n >= size) {
                    for(int j = 1;j<=n-size;j++)
                        list.add(0,false);
                    break;
                }
                n2 *= 2;
                n += n2;
            }
			
			answer[i] = dfs(list, list.size() / 2, true, (list.size() + 1) / 2);
		}
		return answer;
	}

	static int dfs(ArrayList<Boolean> list, int index, boolean parent, int size) {
		if (!parent && list.get(index))
			return 0;
        if(size == 0)
            return 1;
		if(dfs(list, index - size / 2, list.get(index), size / 2) == 0)
            return 0;

		if(dfs(list, index + size / 2, list.get(index), size / 2) == 0)
            return 0;
        
		return 1;

	}

static ArrayList<Boolean> decToBin(long number) {
		ArrayList<Boolean> tmp = new ArrayList<>();
		String s = Long.toBinaryString(number);
		for(int i = 0;i<s.length();i++)
			tmp.add(s.charAt(i)=='1'?true:false);

		return tmp;
	}
}