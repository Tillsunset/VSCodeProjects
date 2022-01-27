a = 0
steps = 0


def multiply(x):
    global steps
    b = 1
    x = str(x)
    if len(x) < 2:
        return steps
    else:
        steps = steps + 1
        for i in range(0, len(x)):
            b = b * int(x[i])
        return multiply(b)


while a < 26:

    print(a)
    print(multiply(a))
    a = a + 1
    steps = 0
