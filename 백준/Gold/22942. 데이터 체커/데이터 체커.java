import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;

    static class Node {
        boolean side;
        int x, idx;

        Node(boolean side, int x, int idx) {
            this.side = side;
            this.x = x;
            this.idx = idx;
        }

        @Override
        public String toString() {
            return "["+(this.side ? ")," : "(,")+this.idx+"]";
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        ArrayList<Node> sideList = new ArrayList<>();
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sideList.add(new Node(false, a - b, N));
            sideList.add(new Node(true, a + b, N));
        }

        Collections.sort(sideList, (o1, o2) -> o1.x == o2.x ? !o1.side ? -1 : 1 : o1.x - o2.x);

        System.out.println(solve(sideList) ? "YES" : "NO");

    }

    static boolean solve(ArrayList<Node> list) {
        Stack<Node> stack = new Stack<>();

        for (Node n : list) {
            if (!n.side)
                stack.add(n);
            else {
                if (stack.isEmpty())
                    return false;
                if(stack.peek().idx != n.idx)
                    return false;
                stack.pop();
            }
        }

        return stack.isEmpty();
    }
}
