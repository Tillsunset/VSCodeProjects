import os

os.system('clear')
bigArray = []
size = 7
solved = False
moves = []
possibleMoves = []

for i in range(size):
	smallArray = ["E", "E", "E", "E"]
	bigArray.append(smallArray)

# Blue
# Cyan
# Red
# Orange
# white
# Pink
# Violet
# Lime
# Green
# Empty

# for i in range(size-2):
#     for j in bigArray[i]:
#         j = input("enter color")

def swap(move, arr):
	return arr

def checkMoves(arr):
	global possibleMoves

def checkSolved(array):
	global solved
	solved = True
	for i in array:
		for k in range(3):
			if i[k] != i[k+1]:
				solved = False

def solve(arr):
	checkSolved(arr)
	if not solved:
		currentMove = []
		for move in checkMoves(arr):
			currentMove.append(move)
			array = swap(move, arr)
			checkSolved(array)
			if solved:
				break
			else:
				solve(array)
			currentMove.pop()
		return currentMove
	else:
		return []

moves.append(solve(bigArray))

print("finished")
print(moves)