import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Trie head;

    static class Trie {
        Trie[] children;
        boolean endFlag;

        Trie() {
            this.children = new Trie[26];
            endFlag = false;
        }

        void add(String s) {
            Trie now = head;
            for (char c : s.toCharArray()) {
                if (now.children[c - 'a'] == null)
                    now.children[c - 'a'] = new Trie();
                now = now.children[c - 'a'];
            }
            now.endFlag = true;
        }

        boolean contains(String s) {
            Trie now = head;
            for (char c : s.toCharArray()) {
                if (now.children[c - 'a'] == null)
                    return false;
                now = now.children[c - 'a'];
            }
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

        for (int i = 0; i < N; i++)
            trie.add(br.readLine());

        int ans = 0;
        for (int j = 0; j < M; j++)
            ans += trie.contains(br.readLine()) ? 1 : 0;

        System.out.println(ans);

    }
}