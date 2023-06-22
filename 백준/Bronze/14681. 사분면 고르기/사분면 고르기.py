a = int(input())
b = int(input())

print(3 if a < 0 and b < 0 else 2 if a < 0 else 4 if b < 0 else 1)