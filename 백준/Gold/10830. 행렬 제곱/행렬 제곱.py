N, B = map(int,input().split())

matrix = [list(map(int,input().split()))for i in range(N)]

for i in range(N):
    for j in range(N):
        matrix[i][j] = matrix[i][j] % 1000

def matrix_product(m1, m2):
    length = len(m1)
    result = [[0 for i in range(length)]for i in range(length)]
    for i in range(length):
        for j in range(length):
            for k in range(length):
                result[i][j] += m1[i][k] * m2[k][j]
            result[i][j] = result[i][j]%1000
    return result


reverse_bin_b = bin(B)[2:][::-1]


dp = [0 for i in range(len(reverse_bin_b) +1)]
dp[0] = matrix

for i in range(1,len(reverse_bin_b)+1):
    dp[i]= matrix_product(dp[i-1], dp[i-1])

result = [[0 for i in range(len(matrix))] for i in range(len(matrix))]
for i in range(len(matrix)):
    result[i][i] = 1

for i in range(len(reverse_bin_b)):
    if reverse_bin_b[i] =='1':
        
        result = matrix_product(result , dp[i])

for i in result:
    for j in i:
        print(j %1000, end = " ")
    print()
