import random
import time
startTime = time.time()

array = []
bigArray = []

def split(x):
    global bigArray
    for i in range(len(x)):
        temp = [x[i]]
        bigArray.append(temp)

def merges(x, y = []):
    tempArray = []
    size = len(x) + len(y)

    xIndex = 0
    yIndex = 0

    for i in range(size):
        if yIndex == len(y):
            for i in range(xIndex,len(x)):
                tempArray.append(x[i])
            return tempArray
        elif xIndex == len(x):
            for i in range(yIndex,len(y)):
                tempArray.append(y[i])
            return tempArray
        else:
            if x[xIndex] >= y[yIndex]:
                tempArray.append(y[yIndex])
                yIndex += 1
            else :
                tempArray.append(x[xIndex])
                xIndex += 1
    return tempArray

def merge(x):
    tempArray = []
    size = len(x)
    for i in range(0,size,2):
        if i < len(x) - 1:
            tempArray.append(merges(x[i], x[i + 1]))
        else :
            tempArray.append(merges(x[i]))
    return tempArray

while len(array) < 100000:
    array.append(random.randint(0,947483647))
split(array)

#print(time.time() - startTime)
while len(bigArray) > 1:
    bigArray = merge(bigArray)
#print(bigArray[0])
print(time.time() - startTime)