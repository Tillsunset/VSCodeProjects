from PIL import Image

def scaleRGB(im, x, y, z):
	for i in range(1,im.height):
		for j in range(1,im.width):
			temp = im.getpixel((j,i))
			temp = (int(temp[0]*x), int(temp[1]*y), int(temp[1]*z))
			im.putpixel((j,i), temp)
	return im

image = 'imageManipulation/PNG_demo_Banana.png'
image = Image.open(image)


image = image.getchannel(0)
image.show()

print()
