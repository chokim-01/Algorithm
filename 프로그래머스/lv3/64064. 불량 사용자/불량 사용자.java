import java.util.regex.Pattern;
import java.util.HashSet;
import java.util.Arrays;
class Solution {
    static boolean[][] matches;
    
    static HashSet<String> answer;
    public int solution(String[] user_id, String[] banned_id) {
        // fr*d*, correct boolean
        matches = new boolean[banned_id.length][user_id.length];
        answer = new HashSet<>();
        
        for(int i = 0;i<banned_id.length;i++) {
            banned_id[i] = banned_id[i].replace("*",".");
            
            boolean[] match = new boolean[user_id.length];
            for(int j = 0;j<user_id.length;j++)
                if(Pattern.matches(banned_id[i],user_id[j]))
                    match[j] = true;
            matches[i] = match.clone();
        }
        dfs(0, "");

        return answer.size();
    }    
    static void dfs(int depth, String choice) {
        if(depth == matches.length) {
            char[] tmp = choice.toCharArray();
            Arrays.sort(tmp);
            answer.add(String.valueOf(tmp));
            return;
        }
        for(int i = 0;i<matches[depth].length;i++) {
            if(!matches[depth][i])
                continue;
            if(choice.contains(String.valueOf(i))) 
                continue;
            
            dfs(depth+1,choice + String.valueOf(i));
        }
    }
}