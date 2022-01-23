import robin_stocks as r
import time
import datetime
import csv
import math


filePath = 'ETHHist.csv'
writer = csv.writer(open(filePath, 'a'))
reader = csv.reader(open(filePath))

statArray = []
for row in reader:
    statArray.append(row)

def test(test, smalltest, cash, crypto, totalvalue, EMAShort = 0, EMALong = 0):
    global price, buy_price, sell_price
    date = datetime.datetime.now()
    newRow = [price, date]
    if statArray[len(statArray) -1][0] != newRow[0]:
        statArray.append(newRow)

    smallEMA = int(test/2)

    if EMAShort != 0:
        EMAShort = float(statArray[len(statArray) - 1][0]) * (smalltest / (1 + smallEMA)) + EMAShort * (1 - (smalltest / (1 + smallEMA)))
    else:
        for i in range(1000, -1, -1):
            EMAShort = float(statArray[len(statArray) - 1 - i][0]) * (smalltest / (1 + smallEMA)) + EMAShort * (1 - (smalltest / (1 + smallEMA)))

    if EMALong != 0:
        EMALong = float(statArray[len(statArray) - 1][0]) * (smalltest / (1 + test)) + EMALong * (1 - (smalltest / (1 + test)))
    else:
        for i in range(1000, -1, -1):
            EMALong = float(statArray[len(statArray) - 1 - i][0]) * (smalltest / (1 + test)) + EMALong * (1 - (smalltest / (1 + test)))

    if EMAShort < EMALong and crypto != 0: #sell
        #cash = math.floor(((price-0) * crypto)*100.0)/100.0
        print("sold")
        cash = round(crypto * sell_price*100.00)/100.00
        crypto = 0
    elif EMAShort > EMALong and cash != 0: #Buy
        crypto = cash / buy_price
        cash = 0

    totalValue = cash + crypto * price
    return [test, smalltest, cash, crypto, totalValue, EMAShort, EMALong]


cash = 100
crypto = 0
totalValue = cash + crypto
price = 0

array  = [26, 14, cash, crypto, totalValue, 0, 0]

hold = cash/float(r.crypto.get_crypto_quote('ETH')['mark_price'])

while True:

    price = float(r.crypto.get_crypto_quote('ETH')['mark_price'])
    buy_price = float(r.crypto.get_crypto_quote('ETH')['ask_price'])#high
    sell_price = float(r.crypto.get_crypto_quote('ETH')['bid_price'])#low

    date = datetime.datetime.now()
    newRow = [price, date]
    writer.writerow(newRow)
    
    array = test(array[0],array[1],array[2],array[3], array[4],array[5], array[6])

    price = float(r.crypto.get_crypto_quote('ETH')['mark_price'])
    print(array[4], hold * price, price)
    time.sleep(3)