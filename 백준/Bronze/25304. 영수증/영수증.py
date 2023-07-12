a = int(input())
n = int(input())
sum = 0
for _ in range(n):
    b,c = list(map(int,input().split()))
    sum += b*c
if sum != a:
    print("No")
else:
    print("Yes")