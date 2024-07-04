import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

class Main {
    static int N, max;
    static int[] nums;

    static class Trie {
        Trie[] child = new Trie[2];

        void add(int num) {
            Trie node = this;
            for (int i = 30; i >= 0; i--) {
                int bit = (num >> i) & 1;
                if (node.child[bit] == null)
                    node.child[bit] = new Trie();
                node = node.child[bit];
            }
        }

        int search(int num) {
            Trie now = this;
            int max = 0;
            for (int i = 30,bit,xorBit; i >= 0; i--) {
                bit = (num >> i) & 1;
                xorBit = bit ^ 1;
                if (now.child[xorBit] != null) {
                    max |= (1 << i);
                    now = now.child[xorBit];
                } else
                    now = now.child[bit];
            }
            return max;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input(br);

        max = 0;
        Trie trie = new Trie();

        for (int num : nums)
            trie.add(num);

        for (int num : nums)
            max = Math.max(max, trie.search(num));

        System.out.println(max);
    }

    static void input(BufferedReader br) throws IOException {
        N = Integer.parseInt(br.readLine());
        nums = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
