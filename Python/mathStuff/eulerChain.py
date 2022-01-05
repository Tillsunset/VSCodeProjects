import random

i = random.randint(0, 1000000)
len = 0

while i != 1:
    len += 1
    print(int(i))
    if i % 2 == 0:
        i = i/2
    else:
        i = 3 * i +1



print(int(i))
print("distance to 1: " + str(len))