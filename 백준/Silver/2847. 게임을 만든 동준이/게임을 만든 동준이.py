n = int(input())
a = [int(input()) for _ in range(n)]
ans = 0
num = a[-1]
i = n - 1
while i > 0:
    i -= 1
    ans += a[i] - num if a[i] > (num := min(a[i], num - 1)) else 0
print(ans)