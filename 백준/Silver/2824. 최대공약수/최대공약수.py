from functools import reduce
import math
input()
a = list(map(int,input().split()))
input()
b = list(map(int,input().split()))

r = math.gcd(reduce(lambda x,y:x*y,a),reduce(lambda x,y:x*y,b))
print(str(r)[-9:])