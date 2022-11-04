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

		// 레벨
		int level;
		// Loc
		int x, y;
		// 체력 공격력 방어력, 경험치, 기본 능력치
		int health, maxHealth, offenseP, deffenseP, experience;
		// 장비
		int weapon, armor;
		// 체력 회복, 부활, 공격력 두배, 경험치 1.2배, 데미지 1 (2와 함께착용 -> 3배 적용, 보스몬스터와 전투 시 최대치, 첫공격에 0의 데미지, 능력 없음 )
		// 네개 까지 착용
		int accCount;
		Boolean[] accessories;
		
		public User(int x, int y) {
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
		// 승리 후 경험치 획득
		public void increaseExperience(int e) {
			// 레벨 N에서 레벨 N+1이 되기 위한 경험치는 5*N;
			if (accessories[3])
				e = (int) (e * 1.2);
			this.experience += e;
			// 레벨업 시 능력치 상승 및 경험치 초기화
			if (this.level * 5 <= this.experience) {
				this.experience = 0;
				this.level += 1;
				this.offenseP += 2;
				this.deffenseP += 2;
				this.maxHealth += 5;
				this.health = this.maxHealth;
			}
		}
		// 아이템 박스 조우 시 장비 장착
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
		// 함정
		public void fallTrap() {
			this.health = this.health - (this.accessories[4] ? 1 : 5);
		}
		// 공격 시 공격력 계산
		public int calcAttack(boolean aCount) {
			int attack = this.offenseP + this.weapon;
			int verse = 1;
			if (aCount == true) { // 첫 번째 공격
				if (accessories[2]) { // 3번째 장신구 (두배) 
					if (accessories[4]) { // 5번째 장신구(3배) 
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
	}

	static class Obj {
		Character order;

		Monster monster;
		Box box;

		public Obj(char order) {
			// TODO Auto-generated constructor stub
			this.order = order;
		}
		// 몬스터 생성
		public void makeMonster(String s, int w, int a, int h, int e) {
			this.monster = new Monster(order == 'M' ? true : false, s, w, a, h, e);
		}
		// 아이템박스 생성
		public void makeBox(String T, String b) {
			this.box = new Box(T, b);
		}

	}

	static class Monster {

		boolean boss; // 몬스터 . 보스 구별
		String name;
		int offenseP, deffenseP, health, maxHealth, experience;
		
		public Monster(boolean boss, String name, int offenseP, int deffenseP, int health, int experience) {
			this.boss = boss;
			this.name = name;
			this.offenseP = offenseP;
			this.deffenseP = deffenseP;
			this.health = health;
			this.maxHealth = health;
			this.experience = experience;
		}
	}
	
	static class Box {
		// 무기 , 방어구 , 장신구 셋중 하나
		int offense, deffense;
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

	static User user;
	static String moveOrder, resultS;
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
			
			// 방향 세팅
			int d = dxySetting(o);
			
			nx = user.x + dxy[d][0];
			ny = user.y + dxy[d][1];
			
			// 벽 또는 격자 밖
			if (!mapChk(nx, ny)) {
				nx = user.x;
				ny = user.y;
			}
			
			// 몬스터
			switch (map[nx][ny].order) {
			// 한 쪽이 패배할 때 까지 진행
			case '&':
				// 패배
				if (!fight(nx, ny))
					endFlag = true;
				break;
			// 보스
			case 'M':
				// 보스 조우 시 장신구 효과
				if (user.accessories[5])
					user.HU();
				// 보스 패배
				if (!fight(nx, ny))
					endFlag = true;
				// 승리
				else
					victory = true;
				break;
			// 가시함정
			case '^':
				user.fallTrap();
				// 함정 패배
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
			// 패배 시 장신구 효과
			if (endFlag && user.accessories[1]) {
				user.RE();
				endFlag = false;
				// 몬스터 대결 패배 후 장신구 효과 
				if (map[nx][ny].order != '^')
					map[nx][ny].monster.health = map[nx][ny].monster.maxHealth;
			}
			// 패배 했거나 보스 전투 승리 시
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

	static boolean fight(int nx, int ny) {
		User u = user;
		Monster m = map[nx][ny].monster;
		// 공격력 - 방어력 만큼 데미지를 입힘.
		// 항상 유저 선공
		for (int c = 0;; c++) {
			// 유저 공격 ( max (1, 유저공격력 - 몬스터 방어력)), 장신구 장착 시 첫 공격 n배
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
			// 보스와 전투 시 첫 번째 공격 회피
			if (c == 0 && m.boss && u.accessories[5])
				continue;

			// 몬스터 공격 ( max (1, 몬스터 공격력 - 유저 방어력))
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
	// 방향 세팅
	static int dxySetting(char c) {
		int d = -1;
		switch (c) {
		case 'U':
			d = 0;
			break;
		case 'R':
			d = 1;
			break;
		case 'D':
			d = 2;
			break;
		case 'L':
			d = 3;
			break;
		}
		return d;
	}

	static boolean mapChk(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= M || map[x][y].order == '#')
			return false;
		return true;
	}

	static void printMap(BufferedWriter bw, boolean endFlag) throws IOException {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 승리 시 현재 위치
				if (user.x == i && user.y == j && !endFlag)
					bw.write('@');
				else
					bw.write(map[i][j].order);
			}
			bw.newLine();
		}
	}
	
	// 입력
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