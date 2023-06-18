import math
n = int(input())
if n==1:
	exit(0)

for i in range(2,int(math.sqrt(n)+1)):
	while n%i == 0:
		print(i)
		n/=i
if n!=1:
	print(int(n))