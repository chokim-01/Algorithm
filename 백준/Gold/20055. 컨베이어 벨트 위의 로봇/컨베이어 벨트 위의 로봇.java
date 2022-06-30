import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static int N, K;

	static List<Integer> robot;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt(); // K개 이상이면 과정 종료
		robot = new LinkedList<Integer>();
		zeroCnt = 0;

		int[] belt = new int[2 * N]; // 내구도
		boolean[] robotLoc = new boolean[2 * N]; // 로봇 위치
		for (int i = 0; i < 2 * N; i++)
			belt[i] = sc.nextInt();

		int ans = 0;

		while (true) {
			// 1.
			ans += 1;
			rotate(belt, robotLoc); // 벨트 회전
			move(belt, robotLoc); // 로봇 이동
			raiseRobot(belt,robotLoc); // 로봇 올리기

			if (zeroCnt >= K)
				break;
		}
		System.out.println(ans);
	}

	static int zeroCnt;
	static void rotate(int[] belt, boolean[] robotLoc) {
		for (int i = 0; i < robot.size(); i++) {
			int now = robot.get(i);
			int next = (now + 1) % (2 * N);
			if(next == N-1) {
				robot.remove(i);
				robotLoc[now] = false;
				i--;
				continue;
			}
			robot.set(i, next);
			robotLoc[now] = false;
			robotLoc[next] = true;
		}
		int tmp = belt[2 * N - 1];
		for (int i = 2 * N - 1; i > 0; i--)
			belt[i] = belt[i - 1];
		belt[0] = tmp;
	}
	
	static void move(int[] belt, boolean[] robotLoc) {
		for (int i = 0; i < robot.size(); i++) {
			int now = robot.get(i);
			int next = (now + 1) % (2 * N);
			// 다음이 내리는 위치면
			if (next == N - 1 && belt[next] != 0) {
				robot.remove(i);
				robotLoc[now] = false;
				i--;
				belt[next] -= 1;
				if (belt[next] == 0)
					zeroCnt += 1;
			}
			// 옮길 수 있음.
			else if (belt[next] != 0 && !robotLoc[next]) {
				belt[next] -= 1;
				robotLoc[next] = true;
				robotLoc[now] = false;
				robot.set(i, next);
				if (belt[next] == 0)
					zeroCnt += 1;
			}
		}
	}
	
	static void raiseRobot(int[] belt, boolean[] robotLoc) {
		if (belt[0] != 0) {
			robot.add(0);
			belt[0] -= 1;
			if (belt[0] == 0)
				zeroCnt += 1;
			robotLoc[0] = true;
		}
	}


}