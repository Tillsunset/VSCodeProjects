import random
import time
import math

initial = time.time()
array = [69]
finalArray = []
index = 0

def siftUp(x):
    i = len(x)-1
    while i > 0:
        if i%2 == 0:
            if x[i-1] >= x[i] and x[i] < x[math.ceil(i/2)-1]:
                x[i], x[math.ceil(i/2)-1] = x[math.ceil(i/2)-1], x[i]
            if x[i-1] < x[i] and x[i-1] < x[math.ceil(i/2)-1]:
                x[i-1], x[math.ceil(i/2)-1] = x[math.ceil(i/2)-1], x[i-1]
            i= i - 2
        else:
            if x[i] < x[math.ceil(i/2)-1]:
                x[i], x[math.ceil(i/2)-1] = x[math.ceil(i/2)-1], x[i]
            i= i - 1
    return x

def checkHeap(x):
    global index
    if 2*index+1 < len(x) and x[index] > x[2*index+1]:
        return True
    if 2*index+2 < len(x) and x[index] > x[2*index+2]:
        return True
    return False

def siftDown(x):
    global index
    if 2*index + 2 < len(x) and x[2*index+1] > x[2*index+2]:
            x[index], x[2*index+2] = x[2*index+2], x[index]
            index = 2* index +2
    else:
        x[index], x[2*index+1] = x[2*index+1], x[index]
        index = 2* index +1
    return x

while len(array) < 1000:
    array.append(random.randint(0, 2147483647))

steps = math.floor(math.log2(len(array)))
for i in range(steps):
    array = siftUp(array)

for i in range(len(array)-1):
    index = 0
    finalArray.append(array.pop(0))
    array.insert(0, array.pop())

    while checkHeap(array):
        array = siftDown(array)

finalArray.append(array.pop(0))
timeTaken = time.time() - initial
print(timeTaken)
