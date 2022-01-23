iteration = -1
sum = 0
trial = 0
while trial < 100000:
    iteration = iteration + 2
    sum += 4/iteration
    print(sum)

    iteration = iteration + 2
    sum -= 4/iteration
    print(sum)

    trial += 1
