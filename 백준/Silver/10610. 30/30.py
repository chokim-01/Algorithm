n = int(input())
a = 0
nn = n
while nn != 0:
	a += nn%10
	nn //= 10
if a%3 != 0:
	print(-1)
	exit(0)

lst = list(map(int,str(n)))

if 0 not in lst:
	print(-1)
	exit(0)

lst.sort(reverse = True)
print(''.join(str(x) for x in lst))