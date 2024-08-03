case_number = 1

while True:
    data = list(map(int, input().split()))
    
    N = data[0]
    
    if N == 0:
        break
    
    print(f"Case {case_number}: Sorting... done!")
    
    case_number += 1