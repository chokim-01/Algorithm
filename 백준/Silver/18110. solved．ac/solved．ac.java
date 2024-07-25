import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;

class Main {
    static class CustomList {
        Deque<Integer> dq;

        CustomList() {
            this.dq = new ArrayDeque<>();
        }

        void addAll(ArrayList<Integer> lst) {
            Collections.sort(lst);
            dq.addAll(lst);
        }

        void truncation() {
            int len = (int) (dq.size() * 0.15 + 0.5);
            while (len-- > 0) {
                dq.pollFirst();
                dq.pollLast();
            }
        }

        int getAverage() {
            int size = dq.size();
            double avg = 0;
            while (!dq.isEmpty())
                avg += dq.poll();
            return (int) ((double) avg / size + 0.5);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> list = new ArrayList<>();
        while (N-- > 0)
            list.add(Integer.parseInt(br.readLine()));

        CustomList customList = new CustomList();
        customList.addAll(list);

        customList.truncation();
        System.out.println(customList.getAverage());
    }
}
