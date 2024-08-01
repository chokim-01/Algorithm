import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String ipv6 = sc.next();
        System.out.println(solve(ipv6));
    }

    public static String solve(String ipv6) {
        String[] parts = ipv6.split("::");

        String[] left = parts[0].split(":");
        String[] right = parts.length > 1 ? parts[1].split(":") : new String[0];

        int max = 8;
        int missingGroups = max - (left.length + right.length);

        String[] result = new String[max];
        int index = 0;

        for (String group : left)
            result[index++] = group;

        for (int i = 0; i < missingGroups; i++)
            result[index++] = "0000";

        for (String group : right)
            result[index++] = group;

        for (int i = 0; i < result.length; i++)
            result[i] = String.format("%4s", result[i]).replace(' ', '0');

        return String.join(":", result);
    }
}
