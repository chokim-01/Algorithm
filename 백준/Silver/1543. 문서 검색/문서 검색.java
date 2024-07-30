import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

class Main {
    static ArrayList<Integer>[] startList;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String context = sc.nextLine();
        String subContext = sc.nextLine();

        makeLink(context);
        System.out.println(find(context, subContext));
    }

    static int find(String context, String subContext) {
        int count = 0;
        int end = -1;
        outer:
        for (int start : startList[subContext.charAt(0) - 'a']) {
            if (end > start)
                continue;
            if (start + subContext.length() > context.length())
                continue;
            for (int i = 0; i < subContext.length(); i++)
                if (context.charAt(i + start) != subContext.charAt(i))
                    continue outer;

            end = start + subContext.length();
            count++;
        }
        return count;
    }

    static void makeLink(String context) {
        startList = new ArrayList[27];
        IntStream.range(0, 27).forEach(x -> startList[x] = new ArrayList<>());
        for (int i = 0, v; i < context.length(); i++) {
            v = context.charAt(i) - 'a';
            startList[v == -65 ? 26 : v].add(i);
        }
    }
}
