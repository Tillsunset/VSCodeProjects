import solveTx, solveRx, generatedata
axxb_closedform = __import__("axxb closedform")
import numpy as np
from scipy.spatial.transform import Rotation as R
from scipy import linalg

def axxb(e_bh, e_sc):
	# e_bh: a Nx7 matrix that contain N forward kinematics measurements obtained
	# from tf_echo. The format of each row must be [tx ty tz qx qy qz qw]
	# e_sc: a Nx7 matrix that contain N AR tag measurements obtained from tf_echo.
	# The format of each row must be [tx ty tz qx qy qz qw]

	# if given 3 points, use closed form
	if len(e_bh) < 4:
		return axxb_closedform.axxb_closedform(e_bh, e_sc)

	alphas = []
	ta = []
	Ra = []

	s = [e_bh, e_sc]
	e1 = R.from_quat(s[0][0][3:7])
	E1 = np.eye(4)
	E1[:3,:3] = e1.as_matrix()
	E1[:3, 3] = [s[0][0][0], s[0][0][1], s[0][0][2]]
	invE1 = linalg.inv(E1)

	for i in range(1,len(e_bh)):
		ei = R.from_quat(s[0][i][3:7])
		Ei = np.eye(4)
		Ei[:3,:3] = ei.as_matrix()
		Ei[:3, 3] = [s[0][i][0], s[0][i][1], s[0][i][2]]

		Ai = np.matmul(invE1, Ei)
		Ra.append(Ai[:3,:3])
		ta.append([Ai[0,3], Ai[1,3], Ai[2,3]])
		alphas.append(R.from_matrix(Ai[:3,:3]).as_rotvec())


	betas = []
	tb = []
	Rb = []

	s1 = R.from_quat(s[1][0][3:7])
	S1 = np.eye(4)
	S1[:3,:3] = s1.as_matrix()
	S1[:3, 3] = [s[1][0][0], s[1][0][1], s[1][0][2]]

	for i in range(1,len(e_sc)):
		si = R.from_quat(s[1][i][3:7])
		Si = np.eye(4)
		Si[:3,:3] = si.as_matrix()
		Si[:3, 3] = [s[1][i][0], s[1][i][1], s[1][i][2]]
		Bi = np.matmul(S1, linalg.inv(Si))
		Rb.append(Bi[:3,:3])
		tb.append([Bi[0,3], Bi[1,3], Bi[2,3]])
		betas.append(R.from_matrix(Bi[:3,:3]).as_rotvec())


	Rx = solveRx.solveRx(alphas, betas)

	tx = solveTx.solveTx(Ra, ta, Rb, tb, Rx)

	Tx = np.eye(4)

	Tx[:3, :3] = Rx
	Tx[0, 3] = tx[0]
	Tx[1, 3] = tx[1]
	Tx[2, 3] = tx[2]
	return Tx
	# return X: the 4x4 homogeneous transformation of the hand-eye calibration
