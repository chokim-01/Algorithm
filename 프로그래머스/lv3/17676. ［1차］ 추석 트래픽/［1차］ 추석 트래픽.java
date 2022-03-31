import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

class Solution {
    static class Node {
		long startTime; // 시간 분 초를 모두 초로
		long completeTime; // 계산

		public Node(String ymd, String arriveTime, String executeTime) {
			// TODO Auto-generated constructor stub
			long year = Long.parseLong(ymd.substring(0, 4)) * 60 * 60 * 24 * 365 * 1000;
			long month = Long.parseLong(ymd.substring(5, 7)) * 60 * 60 * 24 * 30 * 1000;
			long date = Long.parseLong(ymd.substring(8, 10)) * 60 * 60 * 24 * 1000;
			long time = Long.parseLong(arriveTime.substring(0, 2)) * 60 * 60 * 1000;
			long minute = Long.parseLong(arriveTime.substring(3, 5)) * 60 * 1000;
			int second = (int) (Double.parseDouble(arriveTime.substring(6, arriveTime.length())) * 1000);
			int exTime = (int) (Double.parseDouble(executeTime.substring(0, executeTime.length() - 1)) * 1000);

			this.completeTime = year + month + date + time + minute + second;
			this.startTime = completeTime - exTime + 1;
			System.out.println(this.startTime + " " + this.completeTime);

		}
	}
    
    public int solution(String[] lines) {
        
		List<Node> list = new LinkedList<>();
		LinkedHashMap<Long, Integer> map = new LinkedHashMap<>();

		for (int i = 0; i < lines.length; i++) {
			String splitLines[] = lines[i].split(" ");
			list.add(new Node(splitLines[0], splitLines[1], splitLines[2]));
			map.put(list.get(i).startTime, 0);
		}
		// hashMap 시작 시간 만 기록하고 끝시간은 +1초
		//
		for (Long l : map.keySet()) {
			for (int i = 0; i < list.size(); i++) {
				Node n = list.get(i);
				if (l - 1000 < n.completeTime && l >= n.startTime)
					map.put(l, map.get(l) + 1);
			}
		}
		int answer = 0;
		for (Long l : map.keySet()) {
			int count = map.get(l);
			answer = answer < count ? count : answer;
		}
		return answer;
	}
}