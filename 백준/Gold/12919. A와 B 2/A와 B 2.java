import java.util.Scanner;

public class Main {
    static int size;
    static String end;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        end = sc.next();
        StringBuilder start = new StringBuilder(sc.next());
        size = end.length();

        System.out.println(dfs(start, start.length()) ? 1 : 0);
    }

    static boolean dfs(StringBuilder now, int len) {
        if (len == size)
            return now.toString().equals(end);

        StringBuilder next = null;
        boolean f1 = false,f2 = false;
        if (now.substring(now.length() - 1, now.length()).equals("A")) {
            next = new StringBuilder(now.substring(0, now.length() - 1));
            f1 = dfs(next, len - 1);
        }
        if (now.substring(0, 1).equals("B")) {
            next = new StringBuilder(new StringBuilder(now.reverse()).substring(0, now.length() - 1));
            f2 = dfs(next, len - 1);
        }
        return f1 || f2;
    }
}