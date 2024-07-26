import numpy as np
from math import pi
import sympy as sym
from sympy import cos, sin, Matrix, atan2, sqrt, asin, acos

def getTransformation(r1, t1) :
	return Matrix([
		[r1[0], r1[1],	r1[2],	t1[0]],
		[r1[3], r1[4],	r1[5],	t1[1]],
		[r1[6], r1[7],	r1[8],	t1[2]],
		[0,		0,		0,		1]
	])

def formatMatrix(T1):
	print("{0}\t\t,{1}\t\t,{2}\t\t, {3}\n\n".format(
		T1[0],T1[1],T1[2],T1[3]
	))
	print("{0}\t\t,{1}\t\t,{2}\t\t, {3}\n\n".format(
		T1[4],T1[5],T1[6],T1[7]
	))
	print("{0}\t\t,{1}\t\t,{2}\t\t, {3}\n\n".format(
		T1[8],T1[9],T1[10],T1[11]
	))
	print("{0}\t\t,{1}\t\t,{2}\t\t, {3}\n\n".format(
		T1[12],T1[13],T1[14],T1[15]
	))

q1 = sym.Symbol('q1')
q2 = sym.Symbol('q2')
q3 = sym.Symbol('q3')
q4 = sym.Symbol('q4')

trans1 = sym.Symbol('t1')
trans2 = sym.Symbol('t2')
trans3 = sym.Symbol('t3')
trans4 = sym.Symbol('vdt')

# q3 = 0.5
# q2 = pi/2 + -0.3
# q1 = 0.19
#####################################################################################
# q1: z, yaw
# q2: y, pitch
# q3: x, roll
r1 = Matrix([
[cos(q1) * cos(q2), 	cos(q1) * sin(q2) * sin(q3) - sin(q1) * cos(q3), 	cos(q1) * sin(q2) * cos(q3) + sin(q1) * sin(q3)],
[sin(q1) * cos(q2), 	sin(q1) * sin(q2) * sin(q3) + cos(q1) * cos(q3), 	sin(q1) * sin(q2) * cos(q3) - cos(q1) * sin(q3)],
[-sin(q2),			cos(q2) * sin(q3),			cos(q2) * cos(q3)]
])

t1 = Matrix([
trans1,
trans2,
trans3
])

T1 = getTransformation(r1, t1) # base_link

#####################################################################################

r2 = Matrix([
[cos(q4), 	-sin(q4), 	0],
[sin(q4), 	cos(q4), 	0],
[0,			0,			1]
])

t2 = Matrix([
trans4,
0,
0
])

T2 = getTransformation(r2, t2) # shoulder_link

#####################################################################################

T_ee = T1 * T2
sym.simplify(T_ee)
# formatMatrix(T_ee) # T_ee

# roll = atan2(T_ee[4], T_ee[0])
# pitch = asin( -1 * T_ee[8])
# yaw = atan2(T_ee[9], T_ee[10])

# sym.simplify(roll)
# sym.simplify(pitch)
# sym.simplify(yaw)

# print(roll)
# print(pitch)
# print(yaw)

# q1: z, yaw 
# q2: y, pitch
# q3: x, roll
print(sym.simplify(sym.diff(T_ee[11], 'q3'))) #dx/dq1
# print(sym.diff(roll, 'q1')) #dx/dq1
# print(sym.diff(T_ee[1], 'q1')) #dy/dq1
# print(sym.diff(T_ee[4], 'q1')) #dz/dq1
