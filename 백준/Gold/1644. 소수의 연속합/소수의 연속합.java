import java.util.ArrayList;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        ArrayList<Integer> primeList = era();

        long[] prime = primeList.stream().mapToLong(x -> x).toArray();
        for (int i = 1; i < prime.length; i++)
            prime[i] += prime[i - 1];

        int count = 0;
        for (int i = 1; i < prime.length; i++) {
            int l = i;
            int r = prime.length;
            while (l < r) {
                int mid = (l + r) >> 1;
                if (prime[mid] - prime[i - 1] < number)
                    l = mid+1;
                else if (prime[mid] - prime[i - 1] > number)
                    r = mid;
                else {
                    count++;
                    break;
                }
            }
        }
        System.out.println(count);
    }

    public static ArrayList<Integer> era() {
        ArrayList<Integer> ret = new ArrayList<>();
        ret.add(0);
        int n = 4000000;
        boolean[] isPrime = new boolean[n + 1];
        for (int i = 2; i <= n; i++)
            isPrime[i] = true;

        for (int p = 2; (long)p * p <= n; p++) {
            if (isPrime[p])
                for (int i = p * p; i <= n; i += p)
                    isPrime[i] = false;
        }
        for (int i = 0; i < n; i++)
            if (isPrime[i])
                ret.add(i);
        return ret;
    }
}
