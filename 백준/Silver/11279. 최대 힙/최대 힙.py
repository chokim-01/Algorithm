import heapq as hq
import sys
input = sys.stdin.readline

n = int(input())
q = []

for _ in range(n):
    a = int(input())
    if a == 0:
        print(-hq.heappop(q) if q else 0)
    else:
        hq.heappush(q, -a)
