T = int(input())

for t in range(1, T+1):
    s = input()
    stack = []
    
    result = "YES"
    for c in s:
        if c == '(':
            stack.append(c)
        elif c == ')':
            if not stack:
                result = "NO"
                break
            else:
                stack.pop()
    if stack:
        result = "NO"
    
    print(f'#{t} {result}')