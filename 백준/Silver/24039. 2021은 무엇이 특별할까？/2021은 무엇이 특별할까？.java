import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
    static final int MAX = 200;
    static boolean[] prime;
    static ArrayList<Integer> primes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        era();

        PriorityQueue<Integer> pq = calc();


        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        while(pq.peek() <= N)
            pq.poll();
        System.out.println(pq.poll());
    }

    static PriorityQueue<Integer> calc() {
        PriorityQueue<Integer> ret = new PriorityQueue<>();
        for (int i = 1; i < primes.size(); i++)
            ret.add(primes.get(i) * primes.get(i - 1));
        return ret;
    }

    public static void era() {
        prime = new boolean[MAX + 1];
        for (int i = 2; i <= MAX; i++)
            prime[i] = true;

        for (int p = 2; (long) p * p <= MAX; p++) {
            if (!prime[p])
                continue;
            for (int i = p * p; i <= MAX; i += p)
                prime[i] = false;
        }

        primes = new ArrayList<>();
        for (int i = 2; i < MAX; i++)
            if (prime[i])
                primes.add(i);
    }
}
