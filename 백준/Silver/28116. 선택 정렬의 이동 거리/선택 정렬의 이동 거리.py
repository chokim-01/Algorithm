import sys
input = sys.stdin.readline
n = int(input())
a = list(map(int, input().split()))
sa = sorted(a)
d = {}

for i in range(n):
    d[a[i]] = i
d2 = dict(map(reversed,d.items()))

ans = [0 for _ in range(n+1)]
for i in range(n-1):
    if sa[i] == d2[i]:
        continue

    diff = d[sa[i]] - i

    ans[sa[i]] += diff 
    ans[d2[i]] += diff

    num = sa[i]
    num2 = d2[i]
    idx = i
    idx2 = d[sa[i]]
    
    d[num] = idx
    d[num2] = idx2

    d2[idx] = num
    d2[idx2] = num2

print(*ans[1:])
