import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

class Main {
	static char[][] map;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		while (true) {
			String[] XY = sc.nextLine().split(" ");
			if (XY[0].equals("0"))
				return;
			mapSet(Integer.parseInt(XY[1]), Integer.parseInt(XY[0]));

			outer: while (true) {
				String[] order = sc.nextLine().split(" ");
				switch (order[0]) {
				case "PRINT":
					PRINT();
					break outer;
				case "POINT":
					POINT(order);
					break;
				case "TEXT":
					TEXT(order);
					break;
				case "LINE":
					LINE(order);
					break;
				case "CLEAR":
					CLEAR(order);
					break;
				}

			}

		}

	}

	static void mapSet(int x, int y) {
		map = new char[x + 2][y + 2];
		mapWide(0);
		mapWide(x + 1);
		mapHeight(0);
		mapHeight(y + 1);
	}

	static void mapWide(int x) {
		for (int i = 0; i < map[0].length; i++)
			map[x][i] = '-';
	}

	static void mapHeight(int x) {
		for (int i = 0; i < map.length; i++) {
			if (map[i][x] == '-') {
				map[i][x] = '+';
				continue;
			}
			map[i][x] = '|';
		}
	}

	static void POINT(String[] order) {
		int x = Integer.parseInt(order[2]);
		int y = Integer.parseInt(order[1]);
		if (map[x][y] == '\0')
			map[x][y] = 'o';
		else if (map[x][y] != 'o')
			map[x][y] = '*';

	}

	static void TEXT(String[] order) {
		int x = Integer.parseInt(order[2]);
		int y = Integer.parseInt(order[1]);
		char[] str = order[3].toCharArray();
		for (int i = y; i < y + str.length; i++) {
			if (i == map[0].length - 1)
				break;
			if (map[x][i] == '\0')
				map[x][i] = str[i - y];
			else if (map[x][i] != str[i - y])
				map[x][i] = '*';
		}

	}

// 3 10 11 2
	static void LINE(String[] order) {
		int x1 = Integer.parseInt(order[2]);
		int y1 = Integer.parseInt(order[1]);
		int x2 = Integer.parseInt(order[4]);
		int y2 = Integer.parseInt(order[3]);

		// diagonal check
		char setChar = ' ';
		if (x2 - x1 == y2 - y1) {
			setChar = '\\';

		} else if (x2 - x1 == -(y2 - y1)) {
			setChar = '/';
		} else if (x1 == x2) {
			setChar = '-';
		} else {
			setChar = '|';
		}
		drawLine(x1, x2, y1, y2, setChar);
	}

	static void drawLine(int x1, int x2, int y1, int y2, char c) {
		int tmp1 = 0;
		int tmp2 = 0;

		if (c == '\\' || c == '/') {
			if (x1 > x2) {
				tmp1 = x2;
				tmp2 = y2;
				x2 = x1;
				y2 = y1;
				x1 = tmp1;
				y1 = tmp2;
			}

			if (c == '\\') {
				int j = y1;
				for (int i = x1; i <= x2; i++) {
					if (map[i][j] == '\0')
						map[i][j] = c;
					else if (map[i][j] == '/')
						map[i][j] = 'x';
					else if (map[i][j] != '\\' && map[i][j] != 'x')
						map[i][j] = '*';
					j++;
				}

			} else {
				int j = y1;
				for (int i = x1; i <= x2; i++) {
					if (map[i][j] == '\0')
						map[i][j] = c;
					else if (map[i][j] == '\\')
						map[i][j] = 'x';
					else if (map[i][j] != '/' && map[i][j] != 'x')
						map[i][j] = '*';
					j--;
				}
			}
		} else if (c == '-') {
			if (y1 > y2) {
				tmp1 = y1;
				y1 = y2;
				y2 = tmp1;
			}
			for (int i = y1; i <= y2; i++) {
				if (map[x1][i] == '\0')
					map[x1][i] = c;
				else if (map[x1][i] == '|')
					map[x1][i] = '+';
				else if (map[x1][i] != '-' && map[x1][i] != '+')
					map[x1][i] = '*';
			}
		} else {
			if (x1 > x2) {
				tmp2 = x1;
				x1 = x2;
				x2 = tmp2;
			}
			for (int i = x1; i <= x2; i++) {
				if (map[i][y1] == '\0')
					map[i][y1] = c;
				else if (map[i][y1] == '-')
					map[i][y1] = '+';
				else if (map[i][y1] != '|' && map[i][y1] != '+')
					map[i][y1] = '*';
			}
		}
	}

	static void CLEAR(String[] order) {
		int x1 = Integer.parseInt(order[2]);
		int y1 = Integer.parseInt(order[1]);
		int x2 = Integer.parseInt(order[4]);
		int y2 = Integer.parseInt(order[3]);
		int tmp = 0;
		if (x1 > x2) {
			tmp = x1;
			x1 = x2;
			x2 = tmp;
		}
		if (y1 > y2) {
			tmp = y1;
			y1 = y2;
			y2 = tmp;
		}
		for (int i = x1; i <= x2; i++)
			for (int j = y1; j <= y2; j++)
				map[i][j] = '\0';
	}

	static void PRINT() throws IOException {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] == '\0')
					sb.append(" ");
				else
					sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());

	}

}
// 모든 좌표는 범위 내에 존재.
// Point x y : [x,y]에 소문자 o
// TEXT x y txt : [x,y]에서 시작해서 오른쪽으로 txt. 범위 넘어가는것은 무시
// LINE x1 y1 x2 y2 : 가로(-), 세로(|), 대각선(45도), 두 점이 같은 경우는 존재하지 않음.
// CLEAR x1 y1 x2 y2 : 직사각형 모두 지움
// PRINT : 출력

// 동일한 문자가 입력되려 할 시.
// 모든 문자 : \0 -> 입력
// - : - + -> 그대로, 나머지 *
// | : | + -> 그대로, 나머지 *
// \ : \ x -> 그대로, 나머지 *
// / : / x -> 그대로, 나머지 *
// 문자 : 같은 것 그대로, 나머지 *