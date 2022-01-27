import string

dictionaryPath = '/Users/sebastian/Desktop/Dictionary.txt'
dictionary2Path = '/Users/sebastian/Desktop/Dictionary 2.txt'
commonPath = '/Users/sebastian/Desktop/words_alpha.txt'

dictionary = open(dictionaryPath, 'r+')
dictionary2 = open(dictionary2Path, 'r')
common = open(commonPath, 'r')

temp1 = []
dictionaryArray = []
dictionary2Array = []
commonArray = []

temp = ''

for line in common:
    temp = line
    for i in string.punctuation:
        temp = temp.replace(i,'')
    temp = temp.replace('\n','')
    commonArray.append(temp.lower())

commonArray = dict.fromkeys(commonArray)

for line in dictionary2:
    temp = line
    for i in string.punctuation:
        temp = temp.replace(i,'')
    temp = temp.replace('\n','')
    dictionary2Array.append(temp.lower())

dictionary2Array = dict.fromkeys(dictionary2Array)

for line in dictionary:
    temp = line
    for i in string.punctuation:
        temp = temp.replace(i,'')
    temp = temp.replace('\n','')
    temp1.append(temp.lower())

temp1 = dict.fromkeys(temp1)

for i in temp1:
    if not i in dictionary2Array and not i in commonArray :
        dictionaryArray.append(i)

dictionaryArray = list(dict.fromkeys(dictionaryArray))

length  = len(temp1) # unique words in vol 19
length2 = len(dictionaryArray) # unique words in vol 19 that are unknown
length3 = len(dictionary2Array) # unique words in vol 18 that are known
length4 = len(commonArray) # unique 370k most common words
print()

realDicPath = '/Users/sebastian/Desktop/realDic.txt'
realDic = open(realDicPath,'r+')

for i in dictionaryArray:
    realDic.write(i+'\n')