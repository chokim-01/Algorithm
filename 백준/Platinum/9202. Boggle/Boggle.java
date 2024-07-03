import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.TreeSet;

class Main {

    static class Trie {
        Trie[] child;
        boolean endFlag;

        Trie(char c, boolean flag) {
            this.child = new Trie[26];
            this.endFlag = false;
        }

        void add(String s) {
            Trie now = this;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                if (now.child[c - 'A'] == null)
                    now.child[c - 'A'] = new Trie(c, false);
                if (i == s.length() - 1)
                    now.child[c - 'A'].endFlag = true;

                now = now.child[c - 'A'];
            }
        }

    }

    static int W, B;

    static String[] words;
    static char[][][] boggles;
    static TreeSet<String> ansList;
    static int[] score = {0, 0, 0, 1, 1, 2, 3, 5, 11};

    static boolean[][] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input(br);

        Trie head = new Trie('-', false);

        for (String w : words)
            head.add(w);

        ansList = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() == o2.length())
                    return o1.compareTo(o2);
                return o2.length() - o1.length();
            }
        });

        v = new boolean[4][4];

        StringBuilder sb = new StringBuilder();

        for (int b = 0; b < B; b++) {
            ansList.clear();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    char now = boggles[b][i][j];
                    if (head.child[now - 'A'] != null)
                        dfs(1, b, i, j, head.child[now - 'A'], new StringBuilder(String.valueOf(now)));
                }
            }

            int sum = 0;
            if (ansList.size() == 0)
                sb.append("0 0").append("\n");

            else {
                for (String s : ansList)
                    sum += score[s.length()];
                sb.append(sum).append(" ").append(ansList.first()).append(" ").append(ansList.size()).append("\n");
            }
        }
        System.out.println(sb);
    }

    static void dfs(int depth, int b, int x, int y, Trie now, StringBuilder word) {
        if (depth > 9)
            return;
        v[x][y] = true;

        if (now.endFlag)
            ansList.add(word.toString());

        for (int d = 0; d < 8; d++) {
            int nx = x + dxy[d][0];
            int ny = y + dxy[d][1];

            if (!mapChk(nx, ny) || v[nx][ny])
                continue;

            char next = boggles[b][nx][ny];
            if (now.child[next - 'A'] == null)
                continue;
            dfs(depth + 1, b, nx, ny, now.child[next - 'A'], new StringBuilder(word).append(next));
        }
        v[x][y] = false;
    }

    static int[][] dxy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

    static boolean mapChk(int x, int y) {
        if (x < 0 || y < 0 || x >= 4 || y >= 4)
            return false;
        return true;
    }

    static void input(BufferedReader br) throws IOException {
        W = Integer.parseInt(br.readLine());
        words = new String[W];
        for (int i = 0; i < W; i++)
            words[i] = br.readLine();

        br.readLine();

        B = Integer.parseInt(br.readLine());
        boggles = new char[B][4][];
        for (int i = 0; i < B; i++) {
            for (int j = 0; j < 4; j++)
                boggles[i][j] = br.readLine().toCharArray();
            if (i != B - 1)
                br.readLine();
        }
    }
}
//10
//