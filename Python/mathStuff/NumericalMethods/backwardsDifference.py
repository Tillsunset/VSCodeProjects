import math

x = [-0.1,0.0,0.2,0.3]
f = [5.3, 2.0, 3.19, 1.0]
f2 = []
f3 = []
f4 = []

for i in range(len(f) - 1):
	f2.append((f[i+1] - f[i])/(x[i+1] - x[i]))

for i in range(len(f2) - 1):
	f3.append((f2[i+1] - f2[i])/(x[i+2] - x[i]))

for i in range(len(f3) - 1):
	f4.append((f2[i+1] - f2[i])/(x[i+3] - x[i]))


print(f)
print(f2)
print(f3)
print(f4)
