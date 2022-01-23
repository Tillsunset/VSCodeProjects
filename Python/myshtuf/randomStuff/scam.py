# import requests
import random
import string
import json

chars = string.ascii_letters + string.digits + '!@#$%^&*()'
ending = [	'@yahoo.com',
			'@gmail.com',
		  	'@aol.com',]

# url = 'http://craigslist.pottsfam.com/index872dijasydu2iuad27aysdu2yytaus6d2ajsdhasdasd2.php'
names = json.loads(open('./misc/names.json').read())

for name in names:
	name_extra = ''.join(random.choices(string.digits, k= random.randint(0,4)))

	username = name.lower() + name_extra + random.choice(ending)
	password = ''.join(random.choices(chars, k = 8 + random.randint(0,8)))

	# requests.post(url, allow_redirects=False, data={
	# 	'auid2yjauysd2uasdasdasd': username,
	# 	'kjauysd6sAJSDhyui2yasd': password
	# })

	print('sending username %s and password %s' % (username, password))
