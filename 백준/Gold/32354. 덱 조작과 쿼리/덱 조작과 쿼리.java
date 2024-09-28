import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int N;
    static ArrayList<Query> query;
    static long[] sum;

    static Node[] save;

    static class Query {
        String order;
        long value;

        Query(String o, long v) {
            this.order = o;
            this.value = v;
        }
    }

    static class Node {
        Node prev;
        long value;

        Node(int v) {
            this.value = v;
        }

        Node(Node p, long v) {
            this.prev = p;
            this.value = v;
        }

        @Override
        public String toString() {
            return " " + this.value + " ";
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input(br);

        sum = new long[N + 1];

        StringBuilder sb = new StringBuilder();
        Node prev = new Node(-1);

        for (int index = 0; index < N; index++) {
            Query q = query.get(index);
            sum[index] = sum[(index == 0 ? 1 : index) - 1];


            switch (q.order) {
                case "push":
                    prev = save[index] = new Node(prev, q.value);

                    sum[index] += q.value;
                    break;
                case "pop":
                    if (index != 0 && prev.value != -1) {
                        sum[index] -= prev.value;
                        prev = save[index] = prev.prev;
                    }
                    break;
                case "restore":
                    if (q.value == 0) {
                        sum[index] = 0;
                        save[index] = new Node(-1);
                    }
                    else {
                        sum[index] = sum[(int) q.value - 1];
                        prev = save[index] = save[(int) q.value - 1];
                    }
                    break;
                case "print":
                    sb.append(sum[index]).append("\n");
                    break;
            }
        }
        System.out.println(sb);
    }

    static void input(BufferedReader br) throws IOException {
        N = Integer.parseInt(br.readLine());

        query = new ArrayList<>();

        save = new Node[N];

        for (int i = 0; i < N; i++) {
            String[] s = br.readLine().split(" ");
            query.add(new Query(s[0], s.length == 1 ? -1 : Integer.parseInt(s[1])));

        }
//        for (int i = 0; i < N; i++)
//            System.out.print("[ " + i + save[i] + " ] ");
//        System.out.println();
    }
}