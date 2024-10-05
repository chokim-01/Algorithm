import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static double R;
    static double[][] fish;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        R = Double.parseDouble(br.readLine());

        N = Integer.parseInt(br.readLine());
        if (N == 1) {
            System.out.println(1);
            return;
        }
        fish = new double[N][2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            fish[i][0] = Double.parseDouble(st.nextToken());
            fish[i][1] = Double.parseDouble(st.nextToken());
        }

        int max = 1;

        for (int i = 0; i < N; i++) {
            double fx = fish[i][0];
            double fy = fish[i][1];

            for (int j = i + 1; j < N; j++) {
                double f2x = fish[j][0];
                double f2y = fish[j][1];

                double cx = (fx + f2x) / 2;
                double cy = (fy + f2y) / 2;

                double d = R * R - (cx - fx) * (cx - fx) - (cy - fy) * (cy - fy);
                if (d < 0) continue;
                d = Math.sqrt(d);

                double dirX = f2x - fx;
                double dirY = f2y - fy;

                double length = Math.sqrt(dirX * dirX + dirY * dirY);

                dirX /= length;
                dirY /= length;

                double x = -dirY;
                double y = dirX;

                double[] a1 = {cx + x * d, cy + y * d};
                double[] a2 = {cx - x * d, cy - y * d};

                max = Math.max(max, Math.max(Circle(a1[0], a1[1]), Circle(a2[0], a2[1])));
            }
        }

        System.out.println(max);
    }

    private static int Circle(double x, double y) {
        int ret = 0;
        double RR = R * R + 1e-12;

        for (int f = 0; f < N; f++)
            ret += ((fish[f][0] - x) * (fish[f][0] - x) + (fish[f][1] - y) * (fish[f][1] - y)) <= RR ? 1 : 0;
        return ret;
    }
}
