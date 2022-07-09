import random
import time
start = time.time()
array = []

def checkSorted(x):
	for i in range(1,len(x)):
		if x[i - 1] > x[i]:
			return False
	return True

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



while len(array) < 100000:
    array.append(random.randint(0,100000))
array = introsort(array)
#print(testArray)
print(time.time() - start)
print(checkSorted(array))
