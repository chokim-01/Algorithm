input()
li = sorted(map(int,input().split()))
n = 1
for l in li:
    if n < l:
        break
    n += l
print(n)