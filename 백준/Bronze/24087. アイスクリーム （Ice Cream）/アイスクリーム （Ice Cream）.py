import math

S, A, B = int(input()), int(input()), int(input())
print(250 + (math.ceil((S - A) / B) * 100 if S > A else 0))