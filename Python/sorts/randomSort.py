import random
import time


def checksort():
    b = len(array)
    for k in range(0, b-1):
        if not array[k] <= array[k+1]:
            print("bad shuffle")
            return False    
    print("good")
    return True


start_time = time.time()

array = []

d = 5
for i in range(0, d):
    a = random.randint(0, d)
    array.append(a)
print(array)

while not checksort():
    random.shuffle(array)
    print(array)

elapsed_time = time.time() - start_time
print(round(elapsed_time, 5))
