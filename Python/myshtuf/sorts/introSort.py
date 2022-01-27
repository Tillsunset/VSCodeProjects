import random
import time
start = time.time()
testArray = []

def introsort(x):
    if len(x) > 15:# quickSort
        lowerArray = []
        upperArray = []
        sameArray = []
        pivot = x[0]

        for e in x:
            if e < pivot:
                lowerArray.append(e)
            elif e > pivot:
                upperArray.append(e)
            else:
                sameArray.append(e)

        lowerArray = introsort(lowerArray)
        upperArray = introsort(upperArray)
        lowerArray = lowerArray + sameArray 
        lowerArray = lowerArray + upperArray 
        return lowerArray
    elif len(x) > 1:# insertionSort
        sortedArray = []

        for e in x:
            if len(sortedArray) > 1:
                for i in range(len(sortedArray)):
                    if e < sortedArray[i]:
                        sortedArray.insert(i,e)
                        break
                else:
                    sortedArray.append(e)
            else:
                sortedArray.append(e)


        return sortedArray
    else:
        return x


while len(testArray) < 10000:
    testArray.append(random.randint(0, 100000))
testArray = introsort(testArray)
#print(testArray)
print(time.time() - start)
