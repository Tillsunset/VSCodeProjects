import numpy as np
from math import *

def getTransformation(r1, t1) :
	return np.array([
	[r1[0][0], r1[0][1], r1[0][2], t1[0]],
	[r1[1][0], r1[1][1], r1[1][2], t1[1]],
	[r1[2][0], r1[2][1], r1[2][2], t1[2]],
	[0,			0,			0,			1]
	])

def formatMatrix(T1):
	print("[{0}\t,{1}\t,{2}]".format(
		round(T1[0][3], 3),
		round(T1[1][3], 3),
		round(T1[2][3], 3)))

w3 = 0.5
w2 = pi/2 + -0.3
w1 = 0.19
#####################################################################################

r1 = np.array([
[1, 		0, 			0],
[0, 		1, 			0],
[0,			0,			1]
])

t1 = np.array([
0,
0,
0
])

T1 = getTransformation(r1, t1) # base_link

#####################################################################################

r2 = np.array([
[cos(w1), 	-sin(w1), 	0],
[sin(w1), 	cos(w1), 	0],
[0,			0,			1]
])

t2 = np.array([
0,
0,
0.089159
])

T2 = getTransformation(r2, t2) # shoulder_link

#####################################################################################

r3 = np.array([
[cos(w2),	0, 			sin(w2)],
[0, 		1, 			0],
[-sin(w2), 	0, 			cos(w2)]
])

t3 = np.array([
0,
0.13585,
0
])

T3 = getTransformation(r3, t3) # upper_arm_link

#####################################################################################

r4 = np.array([
[cos(w3),	0, 			sin(w3)],
[0, 		1, 			0],
[-sin(w3), 	0, 			cos(w3)]
])

t4 = np.array([
0,
-0.1197,
0.425
])

T4 = getTransformation(r4, t4) # forearm_link

#####################################################################################

r5 = np.array([
[1,	0, 0],
[0, 1, 0],
[0, 0, 1]
])

t5 = np.array([
-0.212543,
0.092989,
0.485779
])

T5 = getTransformation(r5, t5) # gripper_pick

#####################################################################################

formatMatrix(T1)
formatMatrix(np.matmul(T1, T2))
formatMatrix(np.matmul(T1, np.matmul(T2, T3)))
formatMatrix(np.matmul(T1, np.matmul(T2, np.matmul(T3, T4))))
formatMatrix(np.matmul(T1, np.matmul(T2, np.matmul(T3, np.matmul(T4, T5)))))
