n = int(input())

ans = 0
for num in range(n):
    save = num
    cnt = num

    while num != 0:
        cnt += num%10
        num = (int)(num/10)

    if cnt == n:
        ans = save
        break
print(ans)