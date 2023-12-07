from collections import Counter
input()
for c, cnt in Counter(input().split()).most_common():
    while cnt > 0:
        print(c,end=" ")
        cnt-=1