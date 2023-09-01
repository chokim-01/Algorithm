T = int(input())

N = 10
lst = [[] for _ in range(N)]
for i in range(1,N):
	num = i
	while num%10 != i or len(lst[i]) == 0:
		lst[i].append(num%10)
		num *= i

for _ in range(T):
	a,b = map(int,input().split())
	if a%10 == 0:
		print(10)
	else:
		print(lst[a%10][(b-1)%len(lst[a%10])])
	