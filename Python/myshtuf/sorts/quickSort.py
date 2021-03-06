import random
import time
start = time.time()
testArray = []


def quicksort(x):
	if len(x) > 1:  # quickSort
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

		lowerArray = quicksort(lowerArray)
		upperArray = quicksort(upperArray)
		lowerArray = lowerArray + sameArray
		lowerArray = lowerArray + upperArray
		return lowerArray
	else:
		return x


while len(testArray) < 10000:
	testArray.append(random.randint(0, 100000))
testArray = quicksort(testArray)
# print(testArray)
print(time.time() - start)
