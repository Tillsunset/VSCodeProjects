import numpy as np
from scipy.spatial.transform import Rotation
import random
from math import *

# Generate synthetic data to test hand-eye calibration.
# e_bh and e_sc are Nx7 matrices that represent N E_bh and N E_sc transformations.
# Each row is of the form [ tx ty tz qx qy qz qw ]
# where tx, ty and tz denote a translation and qx, qy, qz, qw a quaternion.
# X is a randomly generated hand-eye transformation
def generatedata(N):
	# Assume we know the transformation between the base and the checkerboard
	E_bc = np.eye(4)
	E_bc[:4, 3] = [1, 0, 0, 1]


	# Create a random X for generating the data
	X = randSE3()
	e_bh = []
	e_sc = []


	for i in range(N):
		# Now that you have X and E_bc
		# TODO: Generate a random E_bh, you can use scipy.spatial.transform
		# library to convert the rotation matrix to a quaternion and append 
		# the transformation to e_bh
		E_bh = randSE3()
		r = Rotation.from_matrix(E_bh[:3, :3])
		
		E_bh = [
			E_bh[0,3], E_bh[1,3], E_bh[2,3],
			r.as_quat()[0], r.as_quat()[1], r.as_quat()[2], r.as_quat()[3]]
		e_bh.append(E_bh)

		# Now that you have X, E_bc and E_bh
		# TODO: Find E_sc and append the transformation to e_sc

		invr = r.inv().as_matrix()
		invt = [-E_bh[0], -E_bh[1], -E_bh[2]]
		invbh = np.eye(4)
		invbh[:3, :3] = invr
		invbh[:3, 3] = np.matmul(invr, invt)

		invXr = Rotation.from_matrix(X[:3, :3]).inv().as_matrix()
		invXt = [-X[0,3], -X[1,3], -X[2,3]]
		invX = np.eye(4)
		invX[:3, :3] = invXr
		invX[:3, 3] = np.matmul(invXr, invXt)

		E_sc = np.matmul(np.matmul(invX, invbh), E_bc)

		r = Rotation.from_matrix(E_sc[:3, :3])
		
		E_sc = [
			E_sc[0,3], E_sc[1,3], E_sc[2,3],
			r.as_quat()[0], r.as_quat()[1], r.as_quat()[2], r.as_quat()[3]]
		e_sc.append(E_sc)

	return e_bh, e_sc, X


# Generate a random SE3 transformation
def randSE3() :
	x = random.random() * 2 * pi
	y = random.random() * 2 * pi
	z = random.random() * 2 * pi

	rx = np.array([
		[1,			0,			0],
		[0,			cos(x),		-sin(x)],
		[0,			sin(x),		cos(x)]
	])

	ry = np.array([
		[cos(y),	0,			sin(y)],
		[0,			1,			0],
		[-sin(y),	0,			cos(y)]
	])

	rz = np.array([
		[cos(z),	-sin(z),	0],
		[sin(z),	cos(z),		0],
		[0,			0,			1]
	])
	# TODO: Generate a random rotation matrix
	R= np.matmul(np.matmul(rx, ry), rz)
	# TODO: Generate a random translation
	t= [random.random(), random.random(), random.random()]
	Rt = np.eye(4)
	Rt[:3, :3] = R
	Rt[:3, 3] = t

	return Rt