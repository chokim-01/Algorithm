n = int(input())-1

num = 1
print(num,end=" ")
a = 1
while(n>0):
    num = num + n*a
    print(num,end=" ")
    a*=-1
    n-=1