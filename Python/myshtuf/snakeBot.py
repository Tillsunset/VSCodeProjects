# from typing import Dict


SnakeSpeed = 15

# Unit size ingame
SnakeBlockSize = 150/SnakeSpeed
# 10

DisplayWidth = 80
DisplayHeight = 200

tileHeightNum = DisplayHeight / SnakeBlockSize
tileWidthNum = DisplayWidth / SnakeBlockSize

foodx = 4
foody = 0
snakeLength = 0
snakePositionList = []

vertexArray = []


# class Rect:
#     def __init__(self, X, Y, Width, Height):
#         self.origin = [X,Y]
#         self.width = Width
#         self.height = Height
#         self.area = Width * Height


# ULrect = Rect(1,            1,          foodx,                      foody - 1)
# URrect = Rect(foodx + 1,    1,          tileWidthNum - foodx,       foody)
# LLrect = Rect(1,            foody,      foodx - 1,                  tileHeightNum - foody + 1)
# LRrect = Rect(foodx,        foody + 1,  tileWidthNum - foodx + 1,   tileHeightNum - foody)

# # print(tileHeightNum * tileWidthNum - ULrect.area - URrect.area - LLrect.area - LRrect.area)

# print("test")


# def __init__(self, Length, Direction, Foodx, Foody, SnakePositionList):
#     self.length = Length
#     self.direction = Direction
#     self.foodx = Foodx
#     self.foody = Foody
#     self.positionList = SnakePositionList
    
    
import snake
import pygame

temp = True 
def chooseDirection():
    global temp
    if temp:
        snake.setDirection(snake.Direction.Down.value)
        temp = False

snake.__init__()

while not snake.gameClose:
    snake.gameLoop()

    chooseDirection()

snake.quitCall()

import time
