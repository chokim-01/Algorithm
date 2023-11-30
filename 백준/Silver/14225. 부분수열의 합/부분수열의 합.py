import sys
from itertools import combinations

n, lst = int(input()), list(map(int, input().split()))
ans = [False] * 2000001

for i in range(1, n + 1):
    for x in combinations(lst, i):
        ans[sum(x)] = True

print(next(a for a in range(1, 2000001) if not ans[a]))