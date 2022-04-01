import java.util.*;
class Solution
{
    public int solution(String s)
    {
        int answer = 1;
        Stack<Character> stack = new Stack<>();
        
        char[] word = s.toCharArray();
        
        for(int i = 0;i<word.length;i++) {
            stack.add(word[i]);

            int cnt = 0;    
            Stack<Character> stackTmp = (Stack<Character>) stack.clone();
            int saveI = i;
            // 3
            stack.pop();
            if(!stack.isEmpty()) {
                while(i < word.length-1 && stack.peek() == word[i+1]) {
                    stack.pop();
                    i +=1;
                    cnt += 1;
                    if(stack.isEmpty())
                        break;
                }
            }
            answer = answer < cnt*2+1 ? cnt*2+1 : answer;
            // 2
            i = saveI;
            cnt = 0;
            stack = (Stack<Character>) stackTmp.clone();
            if(!stack.isEmpty()) {
                while(i < word.length-1 && stack.peek() == word[i+1]) {
                    stack.pop();
                    i +=1;
                    cnt += 1;
                    if(stack.isEmpty())
                        break;
                }
            }
            answer = answer < cnt*2 ? cnt*2 : answer;

            i = saveI;
            stack = (Stack<Character>) stackTmp.clone();
        }

        return answer;
    }
}


// a
// b
// c
//     d !!
// c
// b
// a

// a b c d c b a
