b = [False for _ in range(42)]
for _ in range(10):
    n = int(input())
    b[n%42] = True
c = 0
for i in range(42):
    if b[i]:
        c += 1
print(c)
