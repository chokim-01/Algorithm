import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main {
	static class Album {
		Album parent;
		TreeMap<String, Album> albums;
		TreeSet<String> pictures;
		String name;
		int albumCount;
		int pictureCount;

		public Album() {
			// TODO Auto-generated constructor stub
		}

		public Album(Album parent, String s) {
			this.parent = parent;
			this.albums = new TreeMap<>();
			this.pictures = new TreeSet<>();
			this.albumCount = 1;
			this.pictureCount = 0;
			this.name = s;
		}

/////////////// mkalb
		boolean mkalb(String s) {
			if (this.albums.containsKey(s))
				return false;

			Album al = new Album(this, s);
			updateAlbumCount(this, 1);
			this.albums.put(s, al);

			return true;
		}

///////////////
/////////////// rmalb
		int[] rmalb(String s) {
			int a = 0;
			int p = 0;
			if (this.albums.containsKey(s)) {
				Album target = this.albums.get(s);
				a = target.albumCount;
				p = target.pictureCount;

				this.albums.remove(s);
				updateAlbumCount(this, -a);
				if (p != 0)
					updatePictureCount(this, -p);
			}
			return new int[] { a, p };
		}

		int[] rmalb(int order) {
			int[] ret = new int[]{ 0, 0 };
			if (order == -1) {
				if (this.albums.size() != 0) {
					String s = this.albums.firstKey();
					ret = rmalb(s);
				}
			} else if (order == 0) {
				while (albums.size() != 0) {
					String s = albums.firstKey();
					int[] tmp = rmalb(s);
					ret[0] += tmp[0];
					ret[1] += tmp[1];
				}
			} else {
				if (this.albums.size() != 0) {
					String s = this.albums.lastKey();
					ret = rmalb(s);
				}
			}
			return ret;
		}

///////////////
/////////////// insert picture
		boolean insert(String s) {
			if (this.pictures.contains(s))
				return false;

			this.pictures.add(s);
			updatePictureCount(this, 1);
			return true;
		}

///////////////
/////////////// delete
		int delete(String s) {
			if (this.pictures.contains(s)) {
				this.pictures.remove(s);
				updatePictureCount(this, -1);
				return 1;
			}
			return 0;
		}

		int delete(int order) {
			int ret = 0;
			if (order == -1) {
				if (this.pictures.size() != 0) {
					delete(this.pictures.first());
					ret = 1;
				}
			} else if (order == 0) {
				int size = this.pictures.size();
				this.pictures = new TreeSet<>();
				updatePictureCount(this, -size);
				ret = size;
			} else {
				if (this.pictures.size() != 0) {
					delete(this.pictures.last());
					ret = 1;
				}
			}
			return ret;
		}

///////////////
/////////////// ca
		Album ca(String s) {
			Album ret = this;
			switch (s) {
			case "..":
				if (this.parent != null)
					ret = this.parent;
				break;
			case "/":
				ret = top;
				break;
			default:
				if (this.albums.containsKey(s)) {
					Album now = this.albums.get(s);
					ret = now;
				}
				break;
			}
			return ret;
		}

/////////////// 
/////////////// update method
		void updateAlbumCount(Album now, int count) {
			while (now != null) {
				now.albumCount += count;
				now = now.parent;
			}
		}

		void updatePictureCount(Album now, int count) {
			while (now != null) {
				now.pictureCount += count;
				now = now.parent;
			}
		}
///////////////
	}

	static Album top;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		Album now = new Album(null, "album");
		top = now;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String order = st.nextToken();
			String detail = st.nextToken();
			int detailNum = -2;
			try {
				detailNum = Integer.parseInt(detail);
			} catch (Exception e) {
			}

			switch (order) {
			case "mkalb":
				if (!now.mkalb(detail))
					bw.write("duplicated album name\n");
				break;
			case "rmalb":
				int[] ans = null;
				if (detailNum != -2)
					ans = now.rmalb(detailNum);
				else
					ans = now.rmalb(detail);
				bw.write(ans[0] + " " + ans[1] + "\n");
				break;
			case "insert":
				if (!now.insert(detail))
					bw.write("duplicated photo name\n");
				break;
			case "delete":
				int a = 0;
				if (detailNum != -2)
					a = now.delete(detailNum);
				else
					a = now.delete(detail);
				bw.write(a + "\n");
				break;
			case "ca":
				now = now.ca(detail);
				bw.write(now.name + "\n");
				break;
			}
		}
		bw.flush();
		bw.close();
	}
}
