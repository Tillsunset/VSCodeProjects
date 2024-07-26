import random
import time
start = time.time()
testArray = []

def quicksort(x):
	if len(x) > 2:# quickSort
		lowerArray = []
		upperArray = []
		sameArray = []
		pivot1 = x[0]
		pivot2 = x[1]
		pivot3 = x[2]

		if pivot1 < pivot2 < pivot3 or pivot1 > pivot2 > pivot3:
			pivot = pivot2
		elif pivot2 < pivot1 < pivot3 or pivot2 > pivot1 > pivot3:
			pivot = pivot1
		elif pivot1 < pivot3 < pivot2 or pivot1 > pivot3 > pivot2:
			pivot = pivot3
		elif pivot1 == pivot3 or pivot1 == pivot2:
			pivot = pivot1
		else:
			pivot = pivot2

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
	elif len(x) > 1:
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
#print(testArray)
print(time.time() - start)
