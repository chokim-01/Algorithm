a,b = map(int,input().split())
ab = a*b
while b>0:
    t = a
    a = b
    b = t%b
print(int(ab/a))