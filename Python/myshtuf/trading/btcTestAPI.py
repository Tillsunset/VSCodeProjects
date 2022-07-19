import robin_stocks as r
import time
import datetime
import csv
import math


filePath = 'BTCHist.csv'
writer = csv.writer(open(filePath, 'a'))
reader = csv.reader(open(filePath))

statArray = []
for row in reader:
	statArray.append(row)

def test(test, smalltest,cash,crypto, totalvalue):
	global price
	date = datetime.datetime.now()
	newRow = [price, date]
	if statArray[len(statArray) -1][0] != newRow[0]:
		statArray.append(newRow)
	

	shortVelocity = 0
	longVelocity = 0

	for i in range(0, 6):  # 50 sec
		shortVelocity += (price - float(statArray[len(statArray)-i-1][0]))
	for i in range(0, 32):  # 50 sec
		longVelocity += (price - float(statArray[len(statArray)-i-1][0]))

	if shortVelocity < test and crypto != 0:
		#cash = math.floor(((price-0) * crypto)*100.0)/100.0
		cash = price * crypto
		crypto = 0
	elif shortVelocity > test and cash != 0:
		crypto = cash / (price+0)
		cash = 0

	totalValue = cash + crypto * price
	return [test, smalltest, cash, crypto, totalValue]


cash = 10000
crypto = 0
totalValue = cash
price = 0

array  = [-7, 6, cash, crypto, totalValue]
array1 = [-7, 6, cash, crypto, totalValue]
array2 = [-7, 6, cash, crypto, totalValue]

hold = cash/float(r.crypto.get_crypto_quote('BTC')['mark_price'])

while True:

	price = float(r.crypto.get_crypto_quote('BTC')['mark_price'])

	date = datetime.datetime.now()
	newRow = [price, date]
	writer.writerow(newRow)
	
	array = test(array[0],array[1],array[2],array[3], array[4])
	array1 = test(array1[0],array1[1],array1[2],array1[3],array1[4])
	array2 = test(array2[0],array2[1],array2[2],array2[3],array2[4])

	price = float(r.crypto.get_crypto_quote('BTC')['mark_price'])
	print(array[4], array1[4], array2[4], hold * price, price)
	time.sleep(1)