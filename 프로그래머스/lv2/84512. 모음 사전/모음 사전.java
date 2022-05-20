import java.util.HashMap;
import java.util.Map;

class Solution {
	static String str;
	static Map<String,Integer> words;
	static String aeiou = "AEIOU";
    
    public int solution(String word) {
        str = "";
		words = new HashMap<String, Integer>();
        
        recur();
        return words.get(word);
	}
    static void recur() {
		int i;
		
		words.put(str, words.size());
		
		if(str.length() == 5)
			return;
		for(i = 0;i<5;i++) {
			str += aeiou.charAt(i);
			recur();
			str = str.substring(0,str.length()-1);
		}
	}
}