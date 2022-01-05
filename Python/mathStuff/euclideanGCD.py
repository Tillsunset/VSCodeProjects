import random


a = random.randint(0, 21474836470000)
b = random.randint(0, 21474836470000)
c = 1
gcd = 1

if a > b:
    c, gcd = a, b
else:
    c, gcd = b, a

while not c % gcd == 0:
    c, gcd = gcd, c % gcd

print(a)
print(b)
print(gcd)
