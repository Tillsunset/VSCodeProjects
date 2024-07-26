import numpy as np
from scipy.spatial.transform import Rotation as R
from scipy import linalg

def solveRx(alphas, betas):
	# alphas: A 3xN matrix representing the skew symmetric matrices.
	# That is, alphas = [a1 ... aN]
	# betas: A 3xN matrix representing the skew symmetric matrices.
	# That is, betas = [b1 ... bN]
	m = np.zeros((3,3))
	for i in range(len(alphas)):
		m += np.outer(betas[i], np.transpose(alphas[i]))

	Rx = np.matmul(np.transpose(m), m)
	Rx = linalg.sqrtm(Rx)
	Rx = linalg.inv(Rx)
	Rx = np.matmul(Rx, np.transpose(m))

	return Rx
	# return Rx: The least squares solution to the matrix Rx.
