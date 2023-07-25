class Node:
    def __init__(self, idx, x, y):
        self.idx = idx
        self.x = x
        self.y = y

def dfs(idx, cnt, choice):
    global ans
    if cnt == K:
        max_d = 0
        for n in list:
            min_d = 10000000
            for next in choice:
                v = abs(n.x - next.x) + abs(n.y - next.y)
                min_d = min(min_d, v)
            if min_d != 10000000:
                max_d = max(max_d, min_d)
        ans = min(ans, max_d)
        return
    if idx == N:
        return

    choice[cnt] = list[idx]
    dfs(idx + 1, cnt + 1, choice)
    dfs(idx + 1, cnt, choice)



N, K = map(int, input().split())

list = []
for i in range(N):
	a, b = map(int, input().split())
	list.append(Node(i, a, b))

ans = 10000000
dfs(0, 0, [None] * K)
print(ans)