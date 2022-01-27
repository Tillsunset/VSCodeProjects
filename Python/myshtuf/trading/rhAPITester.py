import math
import robin_stocks as r



mark_price = float(r.crypto.get_crypto_quote('BTC')['mark_price'])
ask_price = float(r.crypto.get_crypto_quote('BTC')['ask_price'])
bid_price = float(r.crypto.get_crypto_quote('BTC')['bid_price'])

print(mark_price, ask_price, bid_price)


#print(r.order_buy_crypto_limit('BTC', math.floor(5/mark_price*100000000)/100000000,mark_price))
#print(r.order_buy_crypto_limit('BTC', math.floor(5/mark_price*100000000)/100000000,mark_price))
#print(r.get_all_open_crypto_orders())