import pygame
import random
from enum import Enum

class Direction(Enum):
    Up = pygame.K_UP
    Down = pygame.K_DOWN
    Left = pygame.K_LEFT
    Right = pygame.K_RIGHT
    NoDirectrion = 0

pygame.init()

White = (255, 255, 255)
Yellow = (255, 255, 102)
Black = (0, 0, 0)
Red = (213, 50, 80)
Green = (0, 255, 0)
Blue = (50, 153, 213)

# approximate fps
SnakeSpeed = 15
# Unit size ingame
SnakeTileSize = int(150/SnakeSpeed)

DisplayWidth = 600
DisplayHeight = 400
display = pygame.display.set_mode((DisplayWidth, DisplayHeight))

tileHeightNum = int(DisplayHeight / SnakeTileSize)
tileWidthNum = int(DisplayWidth / SnakeTileSize)

xPos = 0
yPos = 0

xPosChange = 0
yPosChange = 0
lastDirection = Direction.NoDirectrion

snakePositionList = []
snakeLength = 1

gameOver = False
gameClose = False

foodx = 0
foody = 0

buffer = []

pygame.display.set_caption("Snake Game")

clock = pygame.time.Clock()

Font_Style = pygame.font.SysFont("bahnschrift", 25)
Score_Font = pygame.font.SysFont("comicsansms", 35)

def setDirection(direction):
    global xPosChange, yPosChange, lastDirection

    if direction == Direction.Left.value and lastDirection != Direction.Right:
        lastDirection = Direction.Left
        xPosChange = -SnakeTileSize
        yPosChange = 0
    elif direction == Direction.Right.value and lastDirection != Direction.Left:
        lastDirection = Direction.Right
        xPosChange = SnakeTileSize
        yPosChange = 0
    elif direction == Direction.Up.value and lastDirection != Direction.Down:
        lastDirection = Direction.Up
        yPosChange = -SnakeTileSize
        xPosChange = 0
    elif direction == Direction.Down.value and lastDirection != Direction.Up:
        lastDirection = Direction.Down
        yPosChange = SnakeTileSize
        xPosChange = 0

def moveFood():
    global foodx, foody, snakePositionList, SnakeTileSize, tileHeightNum, tileWidthNum

    # all possible locations on window
    possibleLocations = []
    for i in range(tileWidthNum):
        for j in range(tileHeightNum):
            possibleLocations.append([i,j])

    # remove all overlapping locations with snake
    for i in snakePositionList[:-1]:
        if i in possibleLocations:
            possibleLocations.remove(i)

    # random choice
    chosenLocation = random.choice(possibleLocations)

    # scale to match tile sizes
    foodx = chosenLocation[0] * SnakeTileSize
    foody = chosenLocation[1] * SnakeTileSize

def initGame():
    global buffer, xPos, yPos, xPosChange, yPosChange, snakePositionList, snakeLength, gameClose, gameOver
    # init to center of screen, stops movements, resets snakeLength and snakePositionList
    xPos = DisplayWidth / 2
    yPos = DisplayHeight / 2
    xPosChange = 0
    yPosChange = 0
    snakePositionList = []
    snakeLength = 1
    gameOver = False
    gameClose = False
    moveFood()
    buffer = []

    pygame.event.clear()
    pygame.event.set_blocked(None)
    pygame.event.set_allowed(pygame.KEYDOWN)
    pygame.event.set_allowed(pygame.QUIT)

def yourScore(score):
    value = Score_Font.render("Your Score: " + str(score), True, Yellow)
    display.blit(value, [0, 0])

def drawSnake(snakeList):
    global SnakeTileSize
    for x in snakeList:
        pygame.draw.rect(display, Black, [x[0], x[1], SnakeTileSize, SnakeTileSize])

def message(msg, color):
    mesg = Font_Style.render(msg, True, color)
    display.blit(mesg, [DisplayWidth / 6, DisplayHeight / 3])

def inputBuffer(input):
    global buffer
    duplicate = False

    for event in buffer:
        if input == event:
            duplicate = True

    # only unique arrow keys are put in the buffer
    if not duplicate and input.key in {Direction.Up.value, Direction.Down.value, Direction.Right.value, Direction.Left.value}:
        buffer.append(input)

def gameLoop():
    global xPos, yPos, xPosChange, yPosChange, foodx, foody, snakePositionList, snakeLength, gameClose, gameOver, buffer
    display.fill(Blue)

    if not gameOver:
        # get inputs
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                gameClose = True
            elif event.type == pygame.KEYDOWN:
                inputBuffer(event)

        # process the first input in the buffer
        if len(buffer) > 0:
            setDirection(buffer.pop(0).key)

        # move Snake head
        xPos += xPosChange
        yPos += yPosChange

        # New array to represents the head position as [xPos, yPos]
        snakeHeadPos = []
        snakeHeadPos.append(xPos)
        snakeHeadPos.append(yPos)

        # Appends the new head positions to the end of list of past head positions and delete oldest posistion if longer then snakeLength
        snakePositionList.append(snakeHeadPos)
        if len(snakePositionList) > snakeLength:
            del snakePositionList[0]

        # If snake hits wall/out of bounds
        if xPos >= DisplayWidth or xPos < 0 or yPos >= DisplayHeight or yPos < 0:
            gameOver = True

        # If snake hits body
        for x in snakePositionList[:-1]:
            if x == snakeHeadPos:
                gameOver = True
        
        # If head is over food then move food, and grow snake then move food
        if xPos == foodx and yPos == foody:
            moveFood()
            snakeLength += 1
        pygame.draw.rect(display, Green, [foodx, foody, SnakeTileSize, SnakeTileSize])

        drawSnake(snakePositionList)
        
        pygame.display.set_caption("Snake Game\tScore : " + str(snakeLength - 1))
        
    else:
        message("Game over! Press C-Play Again or Q-Quit", Red)
        yourScore(snakeLength - 1)

        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                gameClose = True
                gameOver = False
            elif event.type == pygame.KEYDOWN:
                if event.key == pygame.K_q:
                    gameClose = True
                    gameOver = False
                elif event.key == pygame.K_c:
                    gameLoop()

    pygame.display.update()
    clock.tick(SnakeSpeed)


def __init__():
    initGame()

def quitCall():
    pygame.quit()
    quit()

def getFields():
    return [foodx,foody, snakeLength, snakePositionList, tileHeightNum, tileWidthNum]
