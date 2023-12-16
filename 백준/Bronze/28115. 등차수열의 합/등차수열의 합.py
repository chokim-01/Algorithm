import sys
input = sys.stdin.readline
n = int(input())
m = list(map(int,input().split()))

a = []
b = []
diff = 0
c = 0
for i in range(1,n):
    if not diff == m[i]-m[i-1]:
        c+=1
        diff = m[i]-m[i-1]
    if c>1:
        print("NO")
        break
else:
    print("YES")
    for i in range(0,n):
        a.append(i+1)
        b.append(-(i+1-m[i]))
print(*a)
print(*b)