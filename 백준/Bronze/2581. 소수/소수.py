import math
n = 10001
p = [True for _ in range(n)] 
p[1] = False
for i in range(2,int(math.sqrt(n))):
	if not p[i]:
		continue
	for k in range(i*i,n,i):
		p[k] = False
a = int(input())
b = int(input())

a1 = 0
b1 = 10000
for c in range(a,b+1):
	if not p[c]:
		continue
	a1 += c
	b1 = b1 if b1 < c else c
print(-1 if a1==0 else a1)
print('' if a1==0 else b1)