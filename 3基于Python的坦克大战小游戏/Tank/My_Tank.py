#import coding=utf-8
import time
import pygame
#是system的缩写，用来获取操作系统和编译器的一些配置、设置及操作
import sys
import random
from pygame.locals import *

'''
sprite是游戏中所有对象的父类
'''
#坦克界面类
class TankMain(object):
    width=600
    height=500
    # 我方坦克的misslie
    my_tank_missile_list = []
    # 创建我的坦克对象，并赋初值
    my_tank =None
    # 创建敌人坦克列表
    enemy_list = []
    #开始游戏方法，加载系统资源
    def startGame(self):
        #屏幕初始化
        pygame.init()
        #创建窗口，大小（宽和高），窗口的属性（可变,0是固定大小），颜色位数
        screem=pygame.display.set_mode((TankMain.width,TankMain.height),0,32)
        #给窗口设置标题
        pygame.display.set_caption("坦克大战")
        # 创建我的坦克对象，并赋初值
        TankMain.my_tank = My_Tank(screem)

        #创建5个敌人坦克
        for i in range(1,6):
            TankMain.enemy_list.append(Enemy_Tank(screem))

        #里面的东西需要不断更新
        while(True):
            #color RGB(),涉及到颜色的都是一个元组，有三个数字
            #设置屏幕的背景色是黑色
            screem.fill((0,0,0))
            #显示左上角文字(5,5)
            #使用迭代
            for i,text in enumerate(self.write_text(),0):
                screem.blit(text,(5,5+(15*i)))
            #获取事件,就是监听（鼠标、键盘）
            self.get_event(TankMain.my_tank)
            #屏幕上显示我方坦克
            TankMain.my_tank.display()
            #让坦克时时刻刻移动
            TankMain.my_tank.move()
            #显示和随机移动所有敌方坦克
            for enemy in TankMain.enemy_list:
                enemy.display()
                enemy.random_move()
            #显示所有的我方炮弹
            for m in TankMain.my_tank_missile_list:
                if(m.live):
                    m.display()
                    m.move()
                else:
                    TankMain.my_tank_missile_list.remove(m)
            #每次休息0.05s调到下一帧
            time.sleep(0.05)
            #屏幕显示更新
            pygame.display.update()

    #获取监听所有事件
    def get_event(self,my_tank):
        #取出每个事件
        for event in pygame.event.get():
            #如果鼠标点击了右上角红色的X
            if(event.type==pygame.QUIT):
                self.stopGame()
                #print("ok")
            #如果有按键按下了↑→↓←
            if(event.type==pygame.KEYDOWN):
                if(event.key==pygame.K_LEFT or event.key==pygame.K_a):
                    my_tank.direction="L"
                    my_tank.stop=False
                    #my_tank.move()
                if (event.key == pygame.K_RIGHT or event.key==pygame.K_d):
                    my_tank.direction="R"
                    my_tank.stop = False
                   # my_tank.move()
                if(event.key==pygame.K_UP or event.key==pygame.K_w):
                    my_tank.direction="U"
                    my_tank.stop = False
                    #my_tank.move()
                if (event.key == pygame.K_DOWN or event.key==pygame.K_s):
                    my_tank.direction="D"
                    my_tank.stop = False
                    #my_tank.move()
                #如果按下了esc键
                if (event.key == pygame.K_ESCAPE):
                    self.stopGame()
                #发子弹
                if(event.key==pygame.K_j):
                    #添加炮弹，并调用开火函数
                    TankMain.my_tank_missile_list.append(my_tank.fire())
            #如果按键松开，则我的坦克必须停止
            if(event.type==pygame.KEYUP):
                if(event.key==pygame.K_LEFT or event.key==pygame.K_RIGHT or event.key==pygame.K_UP or event.key==pygame.K_DOWN):
                    my_tank.stop=True
                if (event.key == pygame.K_w or event.key == pygame.K_d or event.key == pygame.K_s or event.key == pygame.K_a):
                    my_tank.stop = True

    #关闭游戏方法
    def stopGame(self):
        #退出游戏
        sys.exit()
    #在屏幕的左上角显示文字
    def write_text(self):
        #定义字体格式和大小
        font=pygame.font.SysFont("simsunnsimsun",12)
        #根据字体创建文件的图像
        text_sf1=font.render("敌方坦克数量：%d"%len(TankMain.enemy_list),True,(255,0,0))
        text_sf2=font.render("我方坦克炮弹数量：%d"%len(TankMain.my_tank_missile_list),True,(255,0,20))
        return text_sf1,text_sf2

#继承顶级sprite的类
#坦克大战游戏中所有对象的父类
class BaseItem(pygame.sprite.Sprite):
    def __init__(self,screem):
        #这是必须要的
        pygame.sprite.Sprite.__init__(self)
        #所有对象公共的属性
        self.screem=screem
    #显示炮弹
    def display(self):
        if(self.live):
            # 设置炮弹图像，需要不断地显示
            self.image = self.images[self.direction]
            # 画图片在指定的位置
            self.scremm.blit(self.image, self.rect)

#每个类都有图片
#坦克类（我方坦克和敌方坦克公共的父类）
class Tank(BaseItem):
    #类属性,因为所有坦克对象都是一样的尺寸
    width=50
    height=50

    def __init__(self,screem,left,top):
        #调用父类的__init__()，需要写一个和父类重名的方法但又要调用父类的方法就要super
        super().__init__(screem)
        #坦克在移动或者显示过程中要用到的游戏屏幕
        self.scremm=screem
        #坦克方向，默认向上
        self.direction="U"
        #坦克速度
        self.speed=5
        #初始化默认坦克会走
        self.stop=False
        #坦克的所有图片，是字典形式，key:方向作为键，value:图片（surface）是值
        self.images={}
        #动态添加值
        self.images["L"]=pygame.image.load("image3/tankL.PNG")
        self.images["R"]=pygame.image.load("image3/tankR.PNG")
        self.images["U"]=pygame.image.load("image3/tankU.PNG")
        self.images["D"]=pygame.image.load("image3/tankD.PNG")
        #设置坦克的图像，坦克图片由方向决定
        self.image=self.images[self.direction]
        #获取图片边界
        self.rect=self.image.get_rect()
        self.rect.left=left
        self.rect.top=top
        #决定坦克是否消灭了
        self.live=True
    #把坦克图片显示在游戏窗口上
    def display(self):
        #设置坦克图像，需要不断地显示
        self.image=self.images[self.direction]
        #画图片在指定的位置
        self.scremm.blit(self.image,self.rect)

    #坦克移动
    def move(self):
        #如果坦克不是停止状态
        if(not self.stop):
            if(self.direction=="L"):
                #判断坦克是否已经在边界上
                if(self.rect.left>0):
                    self.rect.left=self.rect.left-self.speed
                else:self.rect.left=0
            if(self.direction=="R"):
                if(self.rect.right<TankMain.width):
                    self.rect.right=self.rect.right+self.speed
                else:
                    self.rect.right=TankMain.width
            if(self.direction=="U"):
                if(self.rect.top>0):
                    self.rect.top=self.rect.top-self.speed
                else:
                    self.rect.top=0
            if(self.direction=="D"):
                if(self.rect.bottom<TankMain.height):
                    self.rect.bottom=self.rect.bottom+self.speed
                else:
                    self.rect.bottom=TankMain.height

    #开火
    def fire(self):
        #创建一颗子弹
        m=Missile(self.screem,self)
        return m

#我方坦克
class My_Tank(Tank):
    def __init__(self,screem):
        #初始化我的坦克位置
        super().__init__(screem,275,400)
        #初始化坦克不会走
        self.stop=True

#敌方坦克
class Enemy_Tank(Tank):
    def __init__(self,screem):
        #初始化敌人坦克位置
        super().__init__(screem,random.randint(1,5)*100,200)
        self.speed=3
        #坦克按照某个方向连续移动的步数
        self.step=8
        #获取一个随机数方向，用于刚创建敌人坦克就有的方向
        self.get_random_direction()
    #需要产生一个随机数方向
    def get_random_direction(self):
        # 坦克移动和停止的随机数0,1,2,3,4
        #1/5的概率会停止
        r = random.randint(0, 4)
        if (r == 4):
            self.stop = True
        elif (r == 0):
            self.direction = "D"
            self.stop = False
        elif (r == 1):
            self.direction = "L"
            self.stop = False
        elif (r == 2):
            self.direction = "R"
            self.stop = False
        elif (r == 3):
            self.direction = "U"
            self.stop = False
    #敌方坦克按照确定的随机方向，连续移动6步，让后才能再次改变方向
    def random_move(self):
        if(self.live):
            if(self.step==0):
                self.get_random_direction()
                self.step=6
            else:
                self.move()
                self.step-=1

#炮弹类
class Missile(BaseItem):
    width=12
    height=12
    def __init__(self,screem,tank):
        #既要实现子类的方法又要实现父类中重名的方法就需要super
        super().__init__(screem)
        self.tank=tank
        # 坦克在移动或者显示过程中要用到的游戏屏幕
        self.scremm = screem
        # 炮弹的方向由坦克的方向决定
        self.direction =tank.direction
        # 炮弹速度
        self.speed = 12

        # 炮弹的所有图片，是字典形式，key:方向作为键，value:图片（surface）是值
        self.images = {}
        # 动态添加值
        self.images["L"] = pygame.image.load("image_m2/missileL.PNG")
        self.images["R"] = pygame.image.load("image_m2/missileR.PNG")
        self.images["U"] = pygame.image.load("image_m2/missileU.PNG")
        self.images["D"] = pygame.image.load("image_m2/missileD.PNG")
        # 设置炮弹的图像，炮弹图片由方向决定
        self.image = self.images[self.direction]
        # 获取图片边界
        self.rect = self.image.get_rect()
        #炮弹需要从坦克的中心打出来
        self.rect.left = tank.rect.left+(tank.width-self.width)/2
        self.rect.top =tank.rect.top+(tank.height-self.height)/2
        # 决定炮弹是否消灭了
        self.live = True

    def move(self):
        #如果炮弹活着
        if(self.live):
            if (self.direction == "L"):
                # 判断坦克是否已经在边界上
                if (self.rect.left > 0):
                    self.rect.left = self.rect.left - self.speed
                #坦克已经碰到边界了
                else:
                    self.live=False
            if (self.direction == "R"):
                if (self.rect.right < TankMain.width):
                    self.rect.right = self.rect.right + self.speed
                else:
                    self.live = False
            if (self.direction == "U"):
                if (self.rect.top > 0):
                    self.rect.top = self.rect.top - self.speed
                else:
                    self.live = False
            if (self.direction == "D"):
                if (self.rect.bottom < TankMain.height):
                    self.rect.bottom = self.rect.bottom + self.speed
                else:
                    self.live = False

#爆炸类
class Explode(BaseItem):
    def __init__(self,screem):
        super().__init__(screem)
        self.live=True
        self.images=[]



#创建坦克对象，不是作为模块调用时执行
if __name__=="__main__":
    game=TankMain()
    game.startGame()
