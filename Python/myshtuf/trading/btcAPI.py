import robin_stocks as r
import datetime
import time
import csv
import math


filePath = '/Users/sebastian/Desktop/BTCHist.csv'
writer = csv.writer(open(filePath,'a'))
reader = csv.reader(open(filePath))

statArray = []
for row in reader:
    statArray.append(row)


cash = 150
totalValue = cash
crypto = 0
lastTradePrice = 0
lastTradeTime = 0
tradeFlag = False
lastShortVelocity = 0
shortVelocity = 0

hold = cash/float(r.crypto.get_crypto_quote('BTC')['mark_price'])

while (True):
    price = float(r.crypto.get_crypto_quote('BTC')['mark_price'])
    date = datetime.datetime.now()
    newRow = [price, date]
    writer.writerow(newRow)
    statArray.append(newRow)
    
    lastShortVelocity = shortVelocity

    if time.time() - lastTradeTime > 5 and abs(lastTradePrice - price) * totalValue > .015:
        tradeFlag = True

    for i in range(0,6):#30 sec
        shortVelocity +=  (price - float(statArray[len(statArray)-i-1][0]))

    if shortVelocity < 17.5 and crypto != 0:
        price = price - 20
        cash = math.floor((price * crypto)*100.0)/100.0
        crypto = 0
        lastTradePrice = price
        lastTradeTime = time.time()
        print('sold')
        tradeFlag = False
    elif shortVelocity > 17.5 and cash != 0:
        price = price + 20
        crypto = cash / price
        cash = 0
        lastTradePrice = price
        lastTradeTime = time.time()
        print('bought')
        tradeFlag = False

    totalValue = cash + crypto * price
    print(totalValue, hold * price, price, tradeFlag, shortVelocity)
    time.sleep(5)
