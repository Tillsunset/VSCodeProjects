import numpy as np
from scipy.spatial.transform import Rotation as R
from scipy import linalg

def solveTx( RA, tA, RB, tB, RX ):
	# RA: a 3x3xN matrix with all the rotations matrices RA_i
	# tA: a 3xN matrix with all the translation vectors tA_i
	# RB: a 3x3xN matrix with all the rotations matrices RB_i
	# tB: a 3xN matrix with all the translation vectors tB_i
	# RX: the 3x3 rotation matrix Rx
	m1 = np.zeros((3* len(RA), 3))
	for i in range(len(RA)):
		m1[3 * i:3 * i+3,:3] = np.eye(3) - RA[i]

	m2 = np.zeros((3* len(RA), 1))
	for i in range(len(RA)):
		m2[3 * i:3 * i+3, 0] = tA[i] - np.matmul(RX, tB[i])

	tx = np.matmul(linalg.pinv(m1), m2)
	return tx
	# return tx: the 3x1 translation vector tx
