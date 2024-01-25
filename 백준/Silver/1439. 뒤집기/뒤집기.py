import re
print(len(re.sub(r"(.)\1+", r"\1", input()))//2)