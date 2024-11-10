import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final char[] list = {'P', 'A', 'P', 'P'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            stack.add(c);

            while (true) {
                int idx = 0;

                Stack<Character> newStack = new Stack<>();
                while(!stack.isEmpty() && idx<4 && stack.peek() == list[idx]){
                    newStack.add(stack.pop());
                    idx++;
                }
                if(idx != 4){
                    while(!newStack.isEmpty())
                        stack.add(newStack.pop());
                    break;
                }
                stack.add('P');
            }

        }

        if (stack.size() == 1 && stack.peek() != 'A')
            System.out.println("PPAP");
        else
            System.out.println("NP");

    }
}
