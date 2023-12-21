import sys
input = sys.stdin.readline
while True:
    l = list(map(int,input().split()))
    if len(l) == 1:
        break
    f = [False for _ in range(301)]    
    for x in l:
        f[x] = True
    c = 0
    for x in l:
        if f[x*2]:
            c+=1
    print(c-1)