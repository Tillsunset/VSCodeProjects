x = [
	-2.0, -1.0, 0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0]
a = [-1, -0.5, 0.0, 1.0, 0.5, -1, -0.5, 0.0, 1.0, 0.5]

h = []
alpha = []
b = []
c = [0.0, -0.170255, 0.681021, -1.05383, -0.965705, 1.91665, -0.700888, 0.886903, -1.34673, 0.0]
d = []


for i in range(0, len(x)-1):
	h.append(x[i+1] - x[i])

for i in range(1, len(a)-1):
	alpha.append(((3.0 * (a[i+1] - a[i])) / h[i]) - ((3.0 * (a[i] - a[i-1])) / h[i-1]))

# print(h)
# print(alpha)

for i in range(0, len(a)-1):
	b.append((a[i+1] - a[i])/h[i] - h[i]*(c[i+1]+2.0*c[i])/3.0)
	d.append((c[i+1] - c[i])/(3.0*h[i]))

i = 8

print("%.4f + %.4f*(x-%.4f) + %.4f*(x-%.4f)^2 + %.4f*(x-%.4f)^3{%.4f <= x <= %.4f}" %  (a[i], b[i], x[i], c[i], x[i], d[i], x[i], x[i], x[i+1]))
