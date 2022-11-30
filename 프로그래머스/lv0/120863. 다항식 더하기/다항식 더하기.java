class Solution {
    public String solution(String s) {
        String answer = "";
        s = s.replaceAll("\\+","");
        String[] str = s.split("  ");
        int I = 0;
        int X = 0;
        for(String z : str) {
            if(z.contains("x")) {
                if(z.length() == 1)
                    X++;
                else
                    X += Integer.parseInt(String.valueOf(z.substring(0,z.length()-1)));
            } else {
                I += Integer.parseInt(z);
            }
        }
        if(X == 0) {
            if(I != 0)
                answer = String.valueOf(I);
        } else if(X == 1) {
            if(I != 0)
                answer = "x + "+I;
            else
                answer = "x";
        } else {
            if(I!=0)
                answer = X+"x + "+I;
            else
                answer = X+"x";
        }
        
        return answer;
    }
}