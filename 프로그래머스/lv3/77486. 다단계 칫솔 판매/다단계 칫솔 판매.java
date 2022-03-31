import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        Map<String, Member> members = new HashMap<>();

        for (String name : enroll)
            members.put(name, new Member(name));
        
        for (int i=0;  i< members.size(); i++) {
            Member member = members.get(enroll[i]);
            
            if (!referral[i].equals("-"))
                member.ccin = members.get(referral[i]);
            else
                member.ccin = new Member("Minho");
            
        }
        
        for (int j=0; j<amount.length; j++) {
            Member m = members.get(seller[j]);
            m.plusMoney(amount[j] * 100);
        }
        int[] answer = {};

        answer = new int[enroll.length];
        for (int k =0; k<enroll.length; k++)
            answer[k] = members.get(enroll[k]).money;
        
        return answer;

    }


    static class Member {
        Member ccin;
        String name;
        int money;
        
        Member(String name) {
            this.name = name;
        };

        public void plusMoney(int money) {
            if (ccin != null) {
                int boonbae = money / 10;
                this.money = this.money + money - boonbae;
                if(boonbae != 0)
                    ccin.plusMoney(boonbae);
            } else {
                this.money = this.money + money;
            }
        }
    }
}