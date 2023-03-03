a=int(input())
b=int(input())
num=len(str(b))
n=b
for i in range(1,num+1):
    m=n%10
    n=n//10
    print("%d"%(a*m))
print("%d"%(a*b))