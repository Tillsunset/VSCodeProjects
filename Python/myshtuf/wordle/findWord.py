import json
import string

def ridXFromY(x, y = string.ascii_lowercase):
	for i in x:
		if i in y:
			y = y.replace(i,'')
	return y

def uniqueStr(x):
	temp = ''
	for i in x:
		if i not in temp:
			temp += i
	return temp
	
def calcLetterFreq():
	global letterAndPosFreq
	for i in letterAndPosFreq:
		letterAndPosFreq[i] = 0
	global matchedWords
	for i in matchedWords:
		for j in range(len(i)):
			temp = str(i[j]) + str(j)
			letterAndPosFreq[temp] += 1


letterAndPosFreq = 'wordle/letterOccurrence.json'
letterAndPosFreq = json.loads(open(letterAndPosFreq).read())
def calcFreq(x, y = False):
	global letterAndPosFreq
	wordBank = 'wordle/wordBankFromSite.json'
	wordBank = json.loads(open(wordBank).read())
	NotAccepted = 'wordle/wordsNotAccepted.json'
	NotAccepted = json.loads(open(NotAccepted).read())

	freq = {}
	for i in wordBank:
		temp3 = i
		if(y):
			temp3 = uniqueStr(temp3)
		for j in range(len(temp3)):
			temp2 = str(temp3[j])
			temp = temp2 + str(j)
			if temp2 not in x and i not in NotAccepted:
				if i in freq:
					freq[i] += letterAndPosFreq[temp]
				else :
					freq[i] = letterAndPosFreq[temp]
	return freq

def StrEquals(x,y):
	if len(x) != len(y):
		return False
	for i in range(len(x)):
		if x[i] != y[i]:
			return False

	return True

matchedWords = []

findLetterWord = ''
findMaxVal = 0

"""
knownLettersAndPosition = 		['','','','','']
knownLettersButNotPosition = 	['','','','','']
doesNotContains = 				'''
'''
"""

knownLettersAndPosition = 		['','','','l','']
knownLettersButNotPosition = 	['','','','et','s']
doesNotContains = 				'''caresdoilypunto
'''

doesContains = ''
for i in knownLettersAndPosition:
	doesContains += i
for i in knownLettersButNotPosition:
	doesContains += i
doesContains = ''.join(set(doesContains))
doesNotContains = ridXFromY(doesContains, doesNotContains)

lettersToCheck = ''
for i in knownLettersButNotPosition:
	lettersToCheck += i
for i in doesNotContains:
	lettersToCheck += i

wordsFreq = calcFreq(lettersToCheck)

matches = 0
for i in wordsFreq:
	validWord = True

	for j in doesContains:
		if j not in i:
			validWord = False
			break

	if validWord:
		for j in doesNotContains:
			if j in i:
				validWord = False
				break

	if validWord:
		for j in range(5):
			if len(knownLettersAndPosition[j]) > 0 and i[j] != knownLettersAndPosition[j]:
				validWord = False
				break

	if validWord:
		for j in range(5):
			if len(knownLettersButNotPosition[j]) > 0:
				for k in knownLettersButNotPosition[j]:
					if i[j] == k:
						validWord = False
						break

	if validWord:
		matches += 1
		matchedWords.append(i)

print(matches)

if matches < 10:
	print(matchedWords)

temp = ''
for i in matchedWords:
	temp +=i

temp = ridXFromY(uniqueStr(temp))

# calcLetterFreq()
wordsFreq = calcFreq(lettersToCheck + temp, True)
for i in wordsFreq:
	if wordsFreq[i] > findMaxVal:
		findMaxVal = wordsFreq[i]
		findLetterWord = i

print(findLetterWord)
print(findMaxVal)

findMaxVal = 0
findLetterWord = ''
calcLetterFreq()
wordsFreq = calcFreq(lettersToCheck + temp, True)
for i in wordsFreq:
	if wordsFreq[i] > findMaxVal:
		findMaxVal = wordsFreq[i]
		findLetterWord = i

print(findLetterWord)
print(findMaxVal)
