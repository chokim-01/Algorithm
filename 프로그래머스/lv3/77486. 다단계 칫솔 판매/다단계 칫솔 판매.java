import java.util.HashMap;

class Solution {
    static HashMap<String,Integer> humans;
	static int[] money;
	static int[] referParent;
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        // 번호 center 0, 나머지
		// 배열 습득한 돈
		money = new int[enroll.length + 1];

		// HashMap 사람 번호
		humans = new HashMap<>();
		int cnt = 1;
		humans.put("-", 0);
		for (int i = 0; i < enroll.length; i++)
			humans.put(enroll[i], cnt++);

		// refererral 배열
		referParent = new int[enroll.length + 1];
		for (int i = 0; i < referral.length; i++)
			referParent[i + 1] = humans.get(referral[i]);

		for (int i = 0; i < seller.length; i++) 
			dfs(humans.get(seller[i]), amount[i] * 100);
		
        int[] answer = new int[enroll.length];
        for(int i = 0;i<answer.length;i++)
            answer[i] = money[i+1];
        
        return answer;
        
    }
    static void dfs(int now, int amount) {
		if((amount / 10) < 1 || now == 0) {
			money[now] += amount;
			return;
		}
		money[now] += amount - amount/10;
		// next
		dfs(referParent[now], amount/10);
		
	}
}