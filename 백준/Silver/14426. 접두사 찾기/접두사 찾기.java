import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Trie head;

    static class Trie {
        Trie[] children;

        Trie() {
            this.children = new Trie[26];
        }

        void add(String s) {
            Trie now = head;
            for (char c : s.toCharArray())
                now = now.children[c - 'a'] == null ? now.children[c - 'a'] = new Trie() : now.children[c - 'a'];
        }

        boolean contains(String s) {
            Trie now = head;
            for (char c : s.toCharArray())
                if ((now = now.children[c - 'a']) == null)
                    return false;
            return true;
        }

    }

    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Trie trie = new Trie();
        head = trie;

        while (N-- > 0)
            trie.add(br.readLine());

        int ans = 0;
        while (M-- > 0)
            ans += trie.contains(br.readLine()) ? 1 : 0;

        System.out.println(ans);

    }
}