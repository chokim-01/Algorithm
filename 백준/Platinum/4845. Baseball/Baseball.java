import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static Team[] teams;

    static class Team {
        String name;
        int pNum, score, outCount;
        Player[] players;
        int[] base;

        Team(String name) {
            this.name = name;
            this.score = this.outCount = this.pNum = 0;
            this.players = new Player[9];
            base = new int[4];
            Arrays.fill(base, -1);
        }

        int go(int pNum) {
            for (int i = 3; i > 0; i--)
                base[i] = base[i - 1];

            base[0] = pNum;
            int ret = base[3];
            base[3] = -1;
            return ret;
        }

        void inningEnd() {
            Arrays.fill(base, -1);
            this.outCount = 0;
        }

    }

    static class Player {
        String name;
        double hit, bunt;

        Player(String name, double hit, double bunt) {
            this.name = name;
            this.hit = hit;
            this.bunt = bunt;
        }
    }

    static class Print {
        String batterName;
        String teamName;

        Print(String b, String t) {
            this.batterName = b;
            this.teamName = t;
        }
    }

    static int rNum;
    static int[][] counts;

    static int getRNum() {
        return rNum = (rNum * 25173 + 13849) % 65536;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder ans = new StringBuilder();

        Queue<Print> hits;
        Queue<Print> runs;

        int games = Integer.parseInt(br.readLine());
        for (int game = 1; game <= games; game++) {
            teams = new Team[2];
            counts = new int[2][2];
            rNum = 1;

            hits = new ArrayDeque<>();
            runs = new ArrayDeque<>();

            for (int t = 0; t < 2; t++) {
                teams[t] = new Team(br.readLine());
                for (int i = 0; i < 9; i++) {
                    st = new StringTokenizer(br.readLine());
                    teams[t].players[i] = new Player(st.nextToken(), Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
                }
            }

            ans.append("Game ").append(game).append(": ").append(teams[0].name).append(" vs. ").append(teams[1].name).append("\n\n");

            for (int inning = 1; ; inning++) {
                if (inning >= 10 && teams[0].score != teams[1].score)
                    break;
                ans.append("Inning ").append(inning).append(":\n");

                for (int t = 0; t < 2; t++) {
                    Team team = teams[t];

                    while (team.outCount < 3) {
                        int batter = teams[t].pNum;

                        // bunt
                        if ((team.outCount == 0 && team.base[1] != -1) || (team.outCount <= 1 && team.base[2] != -1)) {
                            team.outCount++;
                            if (getRNum() / 65536.0 <= team.players[batter].bunt) {
                                int pNum = team.go(-1);
                                if (pNum != -1) {
                                    team.score++;
                                    counts[t][0]++;
                                    runs.add(new Print(team.players[pNum].name, team.name));
                                }
                            }
                        }
                        // hit
                        else {
                            if (getRNum() / 65536.0 <= team.players[batter].hit) {
                                counts[t][1]++;
                                hits.add(new Print(team.players[batter].name, team.name));
                                int pNum = team.go(batter);
                                if (pNum != -1) {
                                    team.score++;
                                    counts[t][0]++;
                                    runs.add(new Print(team.players[pNum].name, team.name));
                                }
                            } else
                                team.outCount++;
                        }
                        team.pNum = (team.pNum + 1) % 9;

                    }
                    
                    team.inningEnd();
                    if (inning >= 9 && t == 0 && teams[1].score > teams[0].score)
                        break;
                }

                ans.append("Hits:\n");
                if (hits.isEmpty())
                    ans.append("  none\n");
                while (!hits.isEmpty()) {
                    Print print = hits.poll();
                    ans.append(String.format("%17s%16s\n", print.batterName, print.teamName));
                }
                ans.append("\n");

                ans.append("Runs:\n");
                if (runs.isEmpty())
                    ans.append("  none\n");
                while (!runs.isEmpty()) {
                    Print print = runs.poll();
                    ans.append(String.format("%17s%16s\n", print.batterName, print.teamName));
                }
                ans.append("\n");

            }
            
            ans.append("End of Game:\n");
            ans.append(String.format("%17s ", teams[0].name)).append(counts[0][0]).append(" runs, ").append(counts[0][1]).append(" hits\n");
            ans.append(String.format("%17s ", teams[1].name)).append(counts[1][0]).append(" runs, ").append(counts[1][1]).append(" hits\n");
            
            if (game != games)
                ans.append("=".repeat(60)).append("\n");
        }
        System.out.println(ans);
    }
}