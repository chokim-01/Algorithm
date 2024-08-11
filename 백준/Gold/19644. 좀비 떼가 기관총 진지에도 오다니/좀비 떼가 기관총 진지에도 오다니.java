import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int L, Ml, Mk, C;
    static int[] zombie, save;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input(br);

        int attack = 0;
        save = new int[L + 2];

        for (int i = 1; i <= L; i++) {
            mGun(i, Ml, Mk);
            int revert = attack;
            attack += save[i];
            
            if (attack + zombie[i] > 0) {
                if (--C < 0) {
                    System.out.println("NO");
                    return;
                }

                mGun(i, Ml, -Mk);
                attack = revert + save[i];
            }

        }
        System.out.println("YES");
    }

    static void mGun(int index, int ml, int mk) {
        save[index] -= mk;
        save[(index + ml) >= L + 2 ? L + 1 : index + ml] += mk;
    }

    static void input(BufferedReader br) throws IOException {

        StringTokenizer st;

        L = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        Ml = Integer.parseInt(st.nextToken());
        Mk = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(br.readLine());

        zombie = new int[L + 1];
        for (int i = 1; i <= L; i++)
            zombie[i] = Integer.parseInt(br.readLine());
    }

}