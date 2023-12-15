d = sorted([int(input()) for _ in range(int(input()))])
print(max((len(d)-i)*x for i, x in enumerate(d)))