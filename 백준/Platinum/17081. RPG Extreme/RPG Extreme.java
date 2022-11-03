import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static Obj[][] map;

	static class User {
		public User(int x, int y) {
			super();
			this.level = 1;
			this.health = 20;
			this.maxHealth = 20;
			this.offenseP = 2;
			this.deffenseP = 2;
			this.experience = 0;
			this.x = x;
			this.y = y;
			accCount = 0;
			this.accessories = new Boolean[7];
			Arrays.fill(this.accessories, false);
		}

		public void increaseExperience(int e) {
			// 레벨 N에서 레벨 N+1이 되기 위한 경험치는 5*N;
			if (accessories[3])
				e = (int) (e * 1.2);
			this.experience += e;
			if (this.level * 5 <= this.experience) {
				this.experience = 0;
				this.level += 1;
				this.offenseP += 2;
				this.deffenseP += 2;
				this.maxHealth += 5;
				this.health = this.maxHealth;
			}
		}

		public void equip(int w, int a, String s) {
			this.weapon = (w == 0 ? this.weapon : w);
			this.armor = (a == 0 ? this.armor : a);
			if (s != null) {
				switch (s) {
				case "HR":
					a = 0;
					break;
				case "RE":
					a = 1;
					break;
				case "CO":
					a = 2;
					break;
				case "EX":
					a = 3;
					break;
				case "DX":
					a = 4;
					break;
				case "HU":
					a = 5;
					break;
				case "CU":
					a = 6;
					break;
				}
				if (this.accCount >= 4)
					return;
				if (!this.accessories[a]) {
					this.accessories[a] = true;
					this.accCount += 1;
				}
			}
		}

		public void fallTrap() {
			this.health = this.health - (this.accessories[4] ? 1 : 5);
		}

		public int calcAttack(boolean aCount) {
			int attack = this.offenseP + this.weapon;
			int verse = 1;
			if (aCount == true) { // 첫 번째 공격
				if (accessories[2]) {
					if (accessories[4]) {
						verse = 3;
					} else {
						verse = 2;
					}
				}
			}
			return attack * verse;
		}

		// 승리 후 체력 회복 0
		public void HR() {
			if (this.maxHealth <= this.health + 3)
				this.health = this.maxHealth;
			else
				this.health += 3;
		}

		// 사망 시 소멸 후 첫 시작 위치로 이동. 1
		public void RE() {
			this.x = startX;
			this.y = startY;
			this.health = this.maxHealth;
			this.accessories[1] = false;
			this.accCount -= 1;
		}

		// 보스 몬스터와 전투에 돌입하는 순간 최대치까지 회복
		public void HU() {
			this.health = this.maxHealth;
		}

		// 레벨
		int level;
		// Loc
		int x, y;
		// 체력 공격력 방어력
		int health;
		int maxHealth;
		int offenseP;
		int deffenseP;
		// 경험치
		int experience;

		int weapon;
		int armor;
		// 체력 회복, 부활, 공격력 두배, 경험치 1.2배, 데미지 1 (2와 함께착용 -> 3배 적용, 보스몬스터와 전투 시 최대치, 첫공격에
		// 0의 데미지, 능력 없음 )
		// 네개 까지 착용
		int accCount;
		Boolean[] accessories;
	}

	static class Obj {
		Character order;

		Monster monster;
		Box box;

		public Obj(char order) {
			// TODO Auto-generated constructor stub
			this.order = order;
		}

		public void makeMonster(String s, int w, int a, int h, int e) {
			this.monster = new Monster(order == 'M' ? true : false, s, w, a, h, e);
		}

		public void makeBox(String T, String b) {
			this.box = new Box(T, b);
		}

	}

	static class Box {
		int offense;
		int deffense;
		String accesory;

		public Box(String T, String b) {
			// TODO Auto-generated constructor stub
			this.offense = 0;
			this.deffense = 0;
			if (T.equals("W")) {
				offense = Integer.valueOf(b);
			} else if (T.equals("A")) {
				deffense = Integer.valueOf(b);
			} else {
				this.accesory = b;
			}
		}

	}

	static class Monster {
		public Monster(boolean boss, String name, int offenseP, int deffenseP, int health, int experience) {
			super();
			this.boss = boss;
			this.name = name;
			this.offenseP = offenseP;
			this.deffenseP = deffenseP;
			this.health = health;
			this.maxHealth = health;
			this.experience = experience;
		}

		boolean boss;
		String name;
		int offenseP;
		int deffenseP;
		int health;
		int maxHealth;
		int experience;

	}

	static String moveOrder, resultS;
	static User user;
	static int startX, startY;
	static int[][] dxy = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		input();

		// 유저 생성
		user = new User(startX, startY);
		int t = 0;
		resultS = ""; // 패배 내용
		boolean endFlag = false;
		boolean victory = false;
		for (; t < moveOrder.length(); t++) {
			char o = moveOrder.charAt(t);
			int nx = user.x;
			int ny = user.y;

			switch (o) {
			case 'U':
				nx = user.x + dxy[0][0];
				ny = user.y + dxy[0][1];
				break;
			case 'R':
				nx = user.x + dxy[1][0];
				ny = user.y + dxy[1][1];
				break;
			case 'D':
				nx = user.x + dxy[2][0];
				ny = user.y + dxy[2][1];
				break;
			case 'L':
				nx = user.x + dxy[3][0];
				ny = user.y + dxy[3][1];
				break;
			}
			// 벽 또는 격자 밖
			if (!mapChk(nx, ny)) {
				nx = user.x;
				ny = user.y;
			}
			// 몬스터
			switch (map[nx][ny].order) {
			// 한 쪽이 패배할 때 까지 진행
			case '&':
				if (!fight(nx, ny)) {// 패배
					endFlag = true;
				}
				break;
			// 보스
			case 'M':
				if (user.accessories[5])
					user.HU();
				if (!fight(nx, ny)) { // 패배
					endFlag = true;
				} else {
					victory = true;
				}
				break;
			// 가시함정
			case '^':
				user.fallTrap();
				if (user.health <= 0) {
					resultS = "YOU HAVE BEEN KILLED BY SPIKE TRAP..";
					endFlag = true;
				} else {
					user.x = nx;
					user.y = ny;
				}
				break;
			// 아이템 상자
			case 'B':
				Box b = map[nx][ny].box;
				user.equip(b.offense, b.deffense, b.accesory);
				map[nx][ny].order = '.';
				user.x = nx;
				user.y = ny;
				break;
			case '.':
				user.x = nx;
				user.y = ny;
				break;
			}
			if (endFlag && user.accessories[1]) {
				user.RE();
				endFlag = false;
				if (map[nx][ny].order != '^') {
					map[nx][ny].monster.health = map[nx][ny].monster.maxHealth;
				}
			}
			if (endFlag || victory)
				break;
		}
		printMap(bw, endFlag);
		if (t == moveOrder.length())
			t--;
		bw.write("Passed Turns : " + (t + 1) + "\n");
		bw.write("LV : " + user.level + "\n");
		bw.write("HP : " + user.health + "/" + user.maxHealth + "\n");
		bw.write("ATT : " + user.offenseP + "+" + user.weapon + "\n");
		bw.write("DEF : " + user.deffenseP + "+" + user.armor + "\n");
		bw.write("EXP : " + user.experience + "/" + user.level * 5 + "\n");

		if (victory) {
			bw.write("YOU WIN!");
		} else if (endFlag) {
			bw.write(resultS);
		} else {
			bw.write("Press any key to continue.");
		}
		bw.flush();
		bw.close();
	}

	static void printUserstate(int t) {
		System.out.println("---------------------------");
		System.out.println("Passed Turns : " + t + "\n");
		System.out.println("LV : " + user.level + "\n");
		System.out.println("HP : " + (user.health < 0 ? 0 : user.health) + "/" + user.maxHealth + "\n");
		System.out.println("ATT : " + user.offenseP + "+" + user.weapon + "\n");
		System.out.println("DEF : " + user.deffenseP + "+" + user.armor + "\n");
		System.out.println("EXP : " + user.experience + "/" + user.level * 5 + "\n");
		System.out.println(Arrays.toString(user.accessories));
		System.out.println("===========================");
		System.out.println();
	}

	static boolean fight(int nx, int ny) {
		User u = user;
		Monster m = map[nx][ny].monster;
		// 공격력 - 방어력 만큼 데미지를 입힘.
		// 항상 유저 선공
		for (int c = 0;; c++) {
			// 유저 공격
			int uA = u.calcAttack(c == 0 ? true : false) - m.deffenseP;
			uA = Math.max(1, uA);
			m.health -= (uA < 0 ? 0 : uA);
			// 유저 승리
			if (m.health <= 0) {
				u.increaseExperience(m.experience);
				if (u.accessories[0])
					u.HR();
				u.x = nx;
				u.y = ny;
				map[nx][ny].order = '.';
				return true;
			}
			if (c == 0 && m.boss && u.accessories[5])
				continue;

			// 몬스터 공격
			int mA = m.offenseP - (u.deffenseP + u.armor);
			mA = Math.max(1, mA);
			u.health -= (mA < 0 ? 0 : mA);
			// 유저 패배
			if (u.health <= 0) {
				u.health = 0;
				if (!u.accessories[1])
					resultS = "YOU HAVE BEEN KILLED BY "+m.name+"..";
				return false;
			}
		}
	}

	static boolean mapChk(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= M || map[x][y].order == '#')
			return false;
		return true;
	}

	static void printMap(BufferedWriter bw, boolean endFlag) throws IOException {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (user.x == i && user.y == j && !endFlag)
					bw.write('@');
				else
					bw.write(map[i][j].order);
			}
			bw.newLine();
		}
	}

	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new Obj[N][M];

		int monsterCount = 0; // 보스 몬스터 포함
		int boxCount = 0; // 아이템 상자

		startX = -1;
		startY = -1;

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				char order = s.charAt(j);
				if (order == '@') {
					startX = i;
					startY = j;
					order = '.';
				} else if (order == '&' || order == 'M') {
					monsterCount++;
				} else if (order == 'B') {
					boxCount++;
				}
				map[i][j] = new Obj(order);
			}
		}
		moveOrder = br.readLine();

		for (int i = 0; i < monsterCount; i++) {
			// x, y, 이름, 공격력, 방어력, 체력, 경험치
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			String s = st.nextToken();
			int w = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			map[x][y].makeMonster(s, w, a, h, e);
		}

		for (int i = 0; i < boxCount; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			String T = st.nextToken();
			String S = st.nextToken();
			map[x][y].makeBox(T, S);
		}
	}
}