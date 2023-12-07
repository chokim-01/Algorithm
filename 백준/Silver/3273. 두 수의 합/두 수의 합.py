N = int(input())
lst = list(map(int,input().split()))
dest = int(input())
lst.sort()

res = 0
l = 0
r = len(lst)-1
while l < r:
    a = lst[l]+lst[r]
    if a >= dest:
        if a == dest:
            res += 1
        r -= 1
    elif a < dest:
        l += 1
print(res)