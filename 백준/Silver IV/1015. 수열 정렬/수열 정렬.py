n = int(input())
lst = list(map(int,input().split()))

s_lst = sorted(lst)

for a in lst:
    for i in range(len(lst)):
        if a == s_lst[i]:
            print(i,end=" ")
            s_lst[i] = 1001
            break