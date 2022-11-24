import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main {
	static BufferedWriter bw;

	static class Album {
		Album parentAlbum;
		TreeMap<String, Album> child = new TreeMap<>();
		TreeSet<String> pictures = new TreeSet<>();
		String aName;
		int albumCount, pictureCount; // Al : 자식 수 , Pic : 자신 + 자식 수

///////////////////// Constructor
		public Album(String s) {
			// TODO Auto-generated constructor stub
			this.aName = s;
			this.albumCount = 0;
			this.pictureCount = 0;
		}

		public Album(String s, Album p) {
			this.parentAlbum = p;
			this.aName = s;
			this.albumCount = 0;
			this.pictureCount = 0;
		}
/////////////////////

		void changeParentCnt(int anum, int pnum) { // 자신과 최상위까지의 Pic count를 갱신
			Album now = this;
			while (now.parentAlbum != null) {
				now.albumCount += anum;
				now.pictureCount += pnum;
				now = now.parentAlbum;
			}
			now.albumCount += anum;
			now.pictureCount += pnum;
		}

///////////////////// Album Section
		void mkalb(String aName) throws IOException { // 자식 앨범 추가
			if (this.child.containsKey(aName)) {
				print("duplicated album name");
			} else {
				this.changeParentCnt(1, 0);
				this.child.put(aName, new Album(aName, this));
			}
		}

		void rmalb(String aName) throws IOException { // 앨범과 picture 모두 삭제
			if (aName.equals("-1")) { // 사전 순 가장 빠른 앨범 삭제
				if (this.child.size() != 0) {
					rmalb(String.valueOf(child.firstKey()));

				} else {
					print("0 0");

				}

			} else if (aName.equals("0")) { // 자식 앨범 모두 삭제
				if (this.child.size() != 0) {
					int a = 0;
					int b = 0;
					for (String s : this.child.keySet()) {
						Album al = this.child.get(s);
						a += al.albumCount + 1;
						b += al.pictureCount;
					}
					print(a + " " + b);

					this.changeParentCnt(-a, -b);
					this.child = new TreeMap<>();

				} else {
					print("0 0");
				}

			} else if (aName.equals("1")) { // 사전 순 가장 느린 앨범 삭제
				if (this.child.size() != 0) {
					rmalb(String.valueOf(child.lastKey()));

				} else {
					print("0 0");
				}

			} else if (this.child.containsKey(aName)) {// S의 이름을 가진 앨범 삭제.
				Album nowAlbum = this.child.get(aName);
				print((nowAlbum.albumCount + 1) + " " + nowAlbum.pictureCount);

				this.changeParentCnt(-(nowAlbum.albumCount + 1), -nowAlbum.pictureCount);
				this.child.remove(aName);
				
			} else {
				print("0 0");
			}
		}
/////////////////////

///////////////////// Picture Section
		void insertPicture(String s) throws IOException { // 앨범에 사진 추가
			if (this.pictures.contains(s)) {
				print("duplicated photo name");

			} else {
				this.changeParentCnt(0, 1);
				this.pictures.add(s);
			}
		}

		void deletePicture(String s) throws IOException { // 사진 삭제
			if (s.equals("-1")) { // 사전 순 가장 빠른 사진 삭제
				if (this.pictures.size() != 0) {
					
					this.changeParentCnt(0, -1);
					this.pictures.remove(pictures.first());
					print("1");
				} else {
					print("0");
				}
				
			} else if (s.equals("0")) { // 사진 모두 삭제
				int size = this.pictures.size();
				this.changeParentCnt(0, -size);
				print(String.valueOf(size));
				this.pictures = new TreeSet<>();
				
			} else if (s.equals("1")) { // 사전 순 가장 느린 사진 삭제
				if (this.pictures.size() != 0) {
					
					this.pictures.remove(pictures.last());
					this.changeParentCnt(0, -1);
					print("1");
				} else {
					print("0");
				}
				
			} else if (pictures.contains(s)) { // s 이름을 가지는 사진 삭제
				pictures.remove(s);
				this.changeParentCnt(0, -1);
				print("1");
			} else {
				print("0");
			}
		}
/////////////////////

///////////////////// ca Section
		Album moveCa(String s) throws IOException {
			if (s.equals("..")) { // 상위 앨범으로 이동
				if (this.parentAlbum != null) {
					print(this.parentAlbum.aName);
					return this.parentAlbum;
				} else {
					print("album");
					return null;
				}
			} else if (s.equals("/")) { // 최상위 앨범으로 이동
				print("album");
				return null;
			} else if (this.child.containsKey(s)) { // s 이름을 가진 앨범으로 이동
				print(this.child.get(s).aName);
				return this.child.get(s);
			}
			// s 이름을 가진 앨범이 없음
			print(this.aName);
			return this;
		}
	}
/////////////////////

	static void print(String s) throws IOException {
		bw.write(s);
		bw.newLine();
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		Album now = new Album("album");
		Album root = now;

		for (int tc = 0; tc < N; tc++) {
			st = new StringTokenizer(br.readLine());
			switch (st.nextToken()) {
			case "mkalb":
				now.mkalb(st.nextToken());
				break;
			case "rmalb":
				now.rmalb(st.nextToken());
				break;
			case "insert":
				now.insertPicture(st.nextToken());
				break;
			case "delete":
				now.deletePicture(st.nextToken());
				break;
			case "ca":
				now = now.moveCa(st.nextToken());
				if (now == null)
					now = root;
				break;
			}
		}
		bw.flush();
		bw.close();
	}
}
