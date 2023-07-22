n,m = list(map(int,input().split()))

l = [list(map(int,input().split())) for _ in range(n)]
r = [list(map(int,input().split())) for _ in range(n)]
for i in range(n):
    for j in range(m):
        print(l[i][j] + r[i][j],end = " ")
    print()