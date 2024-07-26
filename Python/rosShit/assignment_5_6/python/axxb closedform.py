from generatedata import *
import numpy as np
from scipy import linalg
from scipy.spatial.transform import Rotation as R

def axxb_closedform(e_bh, e_sc):
	# e_bh: a 3x7 matrix that contain 3 forward kinematics
	# The format of each row must be [tx ty tz qx qy qz qw]
	# e_sc: a 3x7 matrix that contain 3 camera measurements
	# The format of each row must be [tx ty tz qx qy qz qw]

	s = [e_bh, e_sc]

	e1 = R.from_quat(s[0][0][3:7])
	E1 = np.eye(4)
	E1[:3,:3] = e1.as_matrix()
	E1[:3, 3] = [s[0][0][0], s[0][0][1], s[0][0][2]]

	e2 = R.from_quat(s[0][1][3:7])
	E2 = np.eye(4)
	E2[:3,:3] = e2.as_matrix()
	E2[:3, 3] = [s[0][1][0], s[0][1][1], s[0][1][2]]

	e3 = R.from_quat(s[0][2][3:7])
	E3 = np.eye(4)
	E3[:3,:3] = e3.as_matrix()
	E3[:3, 3] = [s[0][2][0], s[0][2][1], s[0][2][2]]

	A1 = np.matmul(linalg.inv(E1), E2)

	A2 = np.matmul(linalg.inv(E1), E3)

	a1 = R.from_matrix(A1[:3,:3]).as_rotvec()
	a2 = R.from_matrix(A2[:3,:3]).as_rotvec()

	aCross = np.cross(a1, a2)

	A = np.zeros((3,3))
	A[:3, 0] = a1
	A[:3, 1] = a2
	A[:3, 2] = aCross

	s1 = R.from_quat(s[1][0][3:7])
	S1 = np.eye(4)
	S1[:3,:3] = s1.as_matrix()
	S1[:3, 3] = [s[1][0][0], s[1][0][1], s[1][0][2]]

	s2 = R.from_quat(s[1][1][3:7])
	S2 = np.eye(4)
	S2[:3,:3] = s2.as_matrix()
	S2[:3, 3] = [s[1][1][0], s[1][1][1], s[1][1][2]]

	s3 = R.from_quat(s[1][2][3:7])
	S3 = np.eye(4)
	S3[:3,:3] = s3.as_matrix()
	S3[:3, 3] = [s[1][2][0], s[1][2][1], s[1][2][2]]

	B1 = np.matmul(S1, linalg.inv(S2))
	B2 = np.matmul(S1, linalg.inv(S3))

	b1 = R.from_matrix(B1[:3,:3]).as_rotvec()
	b2 = R.from_matrix(B2[:3,:3]).as_rotvec()

	bCross = np.cross(b1, b2)

	B = np.zeros((3,3))
	B[:3, 0] = b1
	B[:3, 1] = b2
	B[:3, 2] = bCross

	Rx = np.matmul(A, linalg.inv(B))

	m1 = np.zeros((6,3))
	m1[:3, :3] = A1[:3,:3] - np.eye(3)
	m1[3:6, :3] = A2[:3,:3] - np.eye(3)

	m2 = np.zeros((6,1))
	m2[:3, 0] = np.matmul(Rx, B1[:3,3]) - A1[:3,3]
	m2[3:6, 0] = np.matmul(Rx, B2[:3,3]) - A2[:3,3]

	tx = np.matmul(linalg.pinv(m1), m2)

	Tx = np.eye(4)
	Tx[:3, :3] = Rx
	Tx[0, 3] = tx[0]
	Tx[1, 3] = tx[1]
	Tx[2, 3] = tx[2]
	return Tx
	# return X: the 4x4 homogeneous transformation of the hand-eye calibration
