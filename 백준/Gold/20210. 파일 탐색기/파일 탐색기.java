import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

class Main {
    static int N;

    static class File {
        int len;
        FileElement[] elements;

        File(int len, FileElement[] elements) {
            this.len = len;
            this.elements = elements;
        }
    }

    static class FileElement {
        int zeroCnt;
        private Character character;
        private String number;

        public FileElement(char character) {
            this.zeroCnt = 0;
            this.character = character;
            this.number = null;
        }

        public FileElement(int zeroCnt, String number) {
            this.zeroCnt = zeroCnt;
            this.character = null;
            this.number = number;
        }

        public boolean isCharacter() {
            return character != null;
        }

        public boolean isNumber() {
            return number != null;
        }

        public Character getCharacter() {
            return character;
        }

        public String getNumber() {
            return number;
        }

        public int getZeroCnt() {
            return zeroCnt;
        }

        @Override
        public String toString() {
            if (isCharacter())
                return character.toString();
            return number.toString();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = input(br);

        File[] files = sort(str);

        StringBuilder sb = new StringBuilder();
        for (File f : files) {
            for (FileElement fe : f.elements)
                sb.append(fe);
            sb.append("\n");
        }
        System.out.println(sb);

    }

    static File[] sort(String[] str) {
        File[] files = new File[str.length];
        for (int i = 0; i < str.length; i++) {
            String[] now = str[i].split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)|(?<=\\D)(?=\\D)");

            List<FileElement> list = new ArrayList<>();
            for (String n : now)
                if (n.matches("\\d+")) {
                    int zeroCnt = 0;
                    for (char c : n.toCharArray())
                        if (c - '0' == 0)
                            zeroCnt++;
                    list.add(new FileElement(zeroCnt, n));
                } else
                    list.add(new FileElement(n.charAt(0)));


            files[i] = new File(now.length, list.toArray(new FileElement[list.size()]));
        }
        Arrays.sort(files, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                int len = o1.len < o2.len ? o1.len : o2.len;
                for (int i = 0; i < len; i++) {
                    FileElement o1e = o1.elements[i];
                    FileElement o2e = o2.elements[i];
                    if (o1e.isNumber() && o2e.isNumber()) {
                        BigInteger o1eNum = new BigInteger(o1e.getNumber());
                        BigInteger o2eNum = new BigInteger(o2e.getNumber());
                        if (o1eNum.equals(o2eNum)) {
                            if (o1e.zeroCnt == o2e.zeroCnt)
                                continue;
                            return o1e.zeroCnt - o2e.zeroCnt;
                        }

                        return o1eNum.compareTo(o2eNum);
                    } else if (o1e.isNumber())
                        return -1;
                    else if (o2e.isNumber())
                        return 1;

                    float o1eCharacter = o1e.getCharacter();
                    float o2eCharacter = o2e.getCharacter();
                    if (o1eCharacter >= 97)
                        o1eCharacter -= 31.5;
                    if (o2eCharacter >= 97)
                        o2eCharacter -= 31.5;

                    if (o1eCharacter == o2eCharacter)
                        continue;

                    return Float.compare(o1eCharacter, o2eCharacter);
                }
                return o1.len - o2.len;
            }
        });
        return files;
    }

    static String[] input(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        String[] str = new String[N];
        while (N-- > 0)
            str[N] = br.readLine();

        return str;
    }
}
