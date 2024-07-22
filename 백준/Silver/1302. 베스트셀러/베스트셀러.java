import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();


        Comparator<Map.Entry<String, Integer>> comparator = (x1, x2) -> {
            int compare = x2.getValue() - x1.getValue();
            if (compare == 0)
                return x1.getKey().compareTo(x2.getKey());
            return compare;
        };

        Set<Map.Entry<String, Integer>> sorted = new TreeSet<>(comparator);
        Map<String, Integer> map = new HashMap<>();

        while (N-- > 0) {
            String str = sc.next();
            map.put(str, map.getOrDefault(str, 0) + 1);
        }
        sorted.addAll(map.entrySet());
        System.out.println(sorted.iterator().next().getKey());
    }
}
