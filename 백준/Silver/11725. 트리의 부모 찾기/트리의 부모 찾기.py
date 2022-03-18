from collections import deque

N = int(input())

connect = [[] for _ in range(N+1)]
parent = [0 for _ in range(N+1)]
visit = [False for _ in range(N+1)]

for _ in range(N-1):
    tmp = list(map(int,input().split()))
    connect[tmp[0]].append(tmp[1])
    connect[tmp[1]].append(tmp[0])

# 1부터 시작해서 다 탐색
que = deque([1])
parent[1] = 1
visit[1] = True

while len(que) > 0:
    node = que.popleft()

    for con in connect[node]:

        if visit[con]:
            continue

        if parent[con] == 0:
            parent[con] = node
            visit[con] = True
            que.append(con)

del parent[0],parent[0]

for p in parent:
    print(p)
