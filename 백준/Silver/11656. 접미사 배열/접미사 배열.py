S = input()
print('\n'.join(sorted([S[i:] for i in range(len(S))])))