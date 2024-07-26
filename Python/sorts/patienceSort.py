import random

array = []
bigArray = []
finalArray = []

def patience():
	for i in range(len(array)):
		if len(bigArray) > 0:
			for j in range(len(bigArray)):
				if array[i] > bigArray[j][-1]:
					bigArray[j].append(array[i])
					break
			else:
				bigArray.append([array[i]])
		else:
			bigArray.append([array[i]])

def sort():
	i = 0
	while i < len(bigArray):
		if len(bigArray[i]) == 0:
			bigArray.pop(i)
			break
		i += 1
	layer1 = 0
	for i in range(len(bigArray)):
		for j in range(len(bigArray[i])):
			if bigArray[layer1][-1] < bigArray[i][j]:
				layer1 = i
	finalArray.insert(0, bigArray[layer1].pop(-1))

while len(array) < 1000:
	array.append(random.randint(0,2147483647))

patience()
for i in range(len(array)):
	sort()