# import coding=utf-8
import time
# 用python做游戏必须的模块
import pygame
# 是system的缩写，用来获取操作系统和编译器的一些配置、设置及操作
import sys
# 产生随机数
import random
from pygame.locals import *

'''
sprite是游戏中所有对象的父类
'''


# 坦克界面类
class TankMain(object):
    # 下面的都是类属性
    width = 600
    height = 500
    # 我方坦克的misslie
    my_tank_missile_list = []
    # 创建我的坦克对象，并赋初值
    my_tank = None
    # 创建敌人坦克列表
    # enemy_list = []
    # 障碍物
    wall = None
    # 敌方坦克的组群,碰撞检测
    enemy_list = pygame.sprite.Group()
    # 炮弹列表
    explode_list = []
    # 敌方坦克炮弹
    enemy_missile_list = pygame.sprite.Group()

    # 开始游戏方法，加载系统资源
    def startGame(self):
        # 屏幕初始化
        pygame.init()
        # 创建窗口，大小（宽和高），窗口的属性（可变,0是固定大小），颜色位数
        screem = pygame.display.set_mode((TankMain.width, TankMain.height), 0, 32)
        # 给窗口设置标题
        pygame.display.set_caption("坦克大战")
        # 创建一堵墙
        TankMain.wall = Wall(screem, 80, 160, 15, 120)
        # 创建我的坦克对象，并赋初值，这里吧screem传给了之后所有的screem
        TankMain.my_tank = My_Tank(screem)
        if (len(TankMain.enemy_list) == 0):
            # 创建5个敌人坦克
            for i in range(1, 6):
                # 把敌人坦克放入组中
                TankMain.enemy_list.add(Enemy_Tank(screem, i * 100, 30))
            for i in range(1, 6):
                # 把敌人坦克放入组中
                TankMain.enemy_list.add(Enemy_Tank(screem, i * 100, 90))
            for i in range(1, 6):
                # 把敌人坦克放入组中
                TankMain.enemy_list.add(Enemy_Tank(screem, i * 100, 150))
            for i in range(1, 6):
                # 把敌人坦克放入组中
                TankMain.enemy_list.add(Enemy_Tank(screem, i * 100, 210))
            for i in range(1, 6):
                # 把敌人坦克放入组中
                TankMain.enemy_list.add(Enemy_Tank(screem, i * 100, 270))

        # 里面的东西需要不断更新
        while (True):

            # color RGB(),涉及到颜色的都是一个元组，有三个数字aaaa
            # 设置屏幕的背景色是黑色
            screem.fill((0, 0, 0))
            # pygame.draw.rect(screem,(0,255,0),Rect(400,50,100,30),10)

            # 显示左上角文字(5,5)
            # 使用迭代，i从0开始
            for i, text in enumerate(self.write_text(), 0):
                screem.blit(text, (5, 5 + (15 * i)))
            # 如果敌人坦克小于0个提示游戏结束
            if (len(TankMain.enemy_list) == 0):
                font = pygame.font.SysFont("simsunnsimsun", 50)
                text = font.render("GAME-OVER", True, (255, 255, 255))
                screem.blit(text, (170, 220))

            # 显示游戏中的墙,并且对墙和其他对象进行碰撞检测
            TankMain.wall.display()
            TankMain.wall.hit_other()

            # 获取事件,就是监听（鼠标、键盘）
            self.get_event(TankMain.my_tank, screem)
            # 我方坦克有的话再判断
            if (TankMain.my_tank):
                # 判断我方坦克是否中弹
                TankMain.my_tank.hit_enemy_missile()
            # 我方坦克活着并且我方坦克有的话
            if (TankMain.my_tank) and (TankMain.my_tank.live):
                # 屏幕上显示我方坦克
                TankMain.my_tank.display()
                # 让坦克时时刻刻移动
                TankMain.my_tank.move()
            else:
                # print("GAME-OVER")
                TankMain.my_tank = None

            # 显示和随机移动所有敌方坦克
            for enemy in TankMain.enemy_list:
                enemy.display()
                enemy.random_move()
                enemy.random_fire()
            # 显示所有的我方炮弹
            for m in TankMain.my_tank_missile_list:
                if (m.live):
                    m.display()
                    # 判断是否击中敌方坦克
                    m.hit_tank()
                    m.move()
                else:
                    TankMain.my_tank_missile_list.remove(m)
            # 显示所有的敌方炮弹
            for m in TankMain.enemy_missile_list:
                if (m.live):
                    m.display()
                    m.move()
                else:
                    TankMain.enemy_missile_list.remove(m)
            # 显示爆炸效果
            for explode in TankMain.explode_list:
                explode.display()
            # 每次休息0.05s调到下一帧
            time.sleep(0.05)
            # 屏幕显示更新
            pygame.display.update()

    # 获取监听所有事件
    def get_event(self, my_tank, screem):
        # 取出每个事件
        for event in pygame.event.get():
            # 如果鼠标点击了右上角红色的X
            if (event.type == pygame.QUIT):
                self.stopGame()
                # print("ok")
            if (event.type == pygame.KEYDOWN) and (not my_tank) and (event.key == pygame.K_n):
                TankMain.my_tank = My_Tank(screem)
            # 如果有按键按下了↑→↓←
            if (event.type == pygame.KEYDOWN) and (my_tank):
                if (event.key == pygame.K_LEFT or event.key == pygame.K_a):
                    my_tank.direction = "L"
                    my_tank.stop = False
                    # my_tank.move()
                if (event.key == pygame.K_RIGHT or event.key == pygame.K_d):
                    my_tank.direction = "R"
                    my_tank.stop = False
                # my_tank.move()
                if (event.key == pygame.K_UP or event.key == pygame.K_w):
                    my_tank.direction = "U"
                    my_tank.stop = False
                    # my_tank.move()
                if (event.key == pygame.K_DOWN or event.key == pygame.K_s):
                    my_tank.direction = "D"
                    my_tank.stop = False
                    # my_tank.move()
                # 如果按下了esc键
                if (event.key == pygame.K_ESCAPE):
                    self.stopGame()
                # 发子弹
                if (event.key == pygame.K_j):
                    m = my_tank.fire()
                    # 我方坦克发射的炮弹
                    m.good = True
                    # 添加炮弹，并调用开火函数
                    TankMain.my_tank_missile_list.append(m)
            # 如果按键松开，则我的坦克必须停止
            if (event.type == pygame.KEYUP) and (my_tank):
                if (
                        event.key == pygame.K_LEFT or event.key == pygame.K_RIGHT or event.key == pygame.K_UP or event.key == pygame.K_DOWN):
                    my_tank.stop = True
                if (
                        event.key == pygame.K_w or event.key == pygame.K_d or event.key == pygame.K_s or event.key == pygame.K_a):
                    my_tank.stop = True

    # 关闭游戏方法
    def stopGame(self):
        # 退出游戏
        sys.exit()

    # 在屏幕的左上角显示文字
    def write_text(self):
        # 定义字体格式和大小
        font = pygame.font.SysFont("simsunnsimsun", 12)
        # 根据字体创建文件的图像
        text_sf1 = font.render("敌方坦克数量：%d" % len(TankMain.enemy_list), True, (255, 0, 0))
        text_sf2 = font.render("我方坦克炮弹数量：%d" % len(TankMain.my_tank_missile_list), True, (255, 255, 255))
        return text_sf1, text_sf2


# 继承顶级sprite的类
# 坦克大战游戏中所有对象的父类
class BaseItem(pygame.sprite.Sprite):
    def __init__(self, screem):
        # 这是必须要的
        pygame.sprite.Sprite.__init__(self)
        # 所有对象公共的属性
        self.screem = screem

    # 显示炮弹
    def display(self):
        if (self.live):
            # 设置炮弹图像，需要不断地显示
            self.image = self.images[self.direction]
            # 画图片在指定的位置
            self.scremm.blit(self.image, self.rect)


# 每个类都有图片
# 坦克类（我方坦克和敌方坦克公共的父类）
class Tank(BaseItem):
    # 类属性,因为所有坦克对象都是一样的尺寸
    width = 50
    height = 50

    def __init__(self, screem, left, top):
        # 调用父类的__init__()，需要写一个和父类重名的方法但又要调用父类的方法就要super
        super().__init__(screem)
        # 坦克在移动或者显示过程中要用到的游戏屏幕
        self.scremm = screem
        # 坦克方向，默认向上
        self.direction = "U"
        # 坦克速度
        self.speed = 5
        # 初始化默认坦克会走
        self.stop = False
        # 坦克的所有图片，是字典形式，key:方向作为键，value:图片（surface）是值
        self.images = {}
        # 动态添加值
        self.images["L"] = pygame.image.load("image3/tankL.PNG")
        self.images["R"] = pygame.image.load("image3/tankR.PNG")
        self.images["U"] = pygame.image.load("image3/tankU.PNG")
        self.images["D"] = pygame.image.load("image3/tankD.PNG")
        # 设置坦克的图像，坦克图片由方向决定
        self.image = self.images[self.direction]
        # 获取图片边界，就是x和y坐标
        self.rect = self.image.get_rect()
        # 图片x坐标
        self.rect.left = left
        # 图片y坐标
        self.rect.top = top
        # 决定坦克是否消灭了
        self.live = True
        # 记录坦克当前坐标
        self.oldtop = self.rect.top
        self.oldleft = self.rect.left

    # 重写父类，把坦克图片显示在游戏窗口上
    def display(self):
        # 设置坦克图像，需要不断地显示
        self.image = self.images[self.direction]
        # 画图片在指定的位置
        self.scremm.blit(self.image, self.rect)

    # 碰撞时调用，使坦克处在原来位置
    def stay(self):
        self.rect.top = (self.oldtop)
        self.rect.left = (self.oldleft)

    # 坦克移动
    def move(self):
        # 如果坦克不是停止状态
        if (not self.stop):
            # 坦克移动之前必须判断是否在障碍物旁边
            # 如果在障碍物旁边就不能运动，保存原来位置
            # 把当前坐标保留下来
            self.oldleft = self.rect.left
            self.oldtop = self.rect.top
            if (self.direction == "L"):
                # 判断坦克是否已经在边界上
                if (self.rect.left > 0):
                    self.rect.left = self.rect.left - self.speed
                else:
                    self.rect.left = 0
            if (self.direction == "R"):
                if (self.rect.right < TankMain.width):
                    self.rect.right = self.rect.right + self.speed
                else:
                    self.rect.right = TankMain.width
            if (self.direction == "U"):
                if (self.rect.top > 0):
                    self.rect.top = self.rect.top - self.speed
                else:
                    self.rect.top = 0
            if (self.direction == "D"):
                if (self.rect.bottom < TankMain.height):
                    self.rect.bottom = self.rect.bottom + self.speed
                else:
                    self.rect.bottom = TankMain.height

    # 开火，获取一颗子弹
    def fire(self):
        # 创建一颗子弹
        m = Missile(self.screem, self)
        return m


# 我方坦克
class My_Tank(Tank):
    def __init__(self, screem):
        # 初始化我的坦克位置
        super().__init__(screem, 275, 400)
        # 初始化坦克不会走
        self.stop = True
        self.live = True

    # 敌方炮弹和我方坦克碰撞
    def hit_enemy_missile(self):
        hit_list = pygame.sprite.spritecollide(self, TankMain.enemy_missile_list, False)
        # 如果有碰撞,中弹
        for m in hit_list:
            m.live = False
            TankMain.enemy_missile_list.remove(m)
            self.live = False
            explode = Explode(self.screem, self.rect)
            TankMain.explode_list.append(explode)


# 敌方坦克
class Enemy_Tank(Tank):

    def __init__(self, screem, left, top):
        # 初始化敌人坦克位置
        super().__init__(screem, left, top)
        self.speed = 3
        # 坦克按照某个方向连续移动的步数
        self.step = 8
        # 获取一个随机数方向，用于刚创建敌人坦克就有的方向
        self.get_random_direction()
        # 标志位，0没有碰撞，1有碰撞
        self.flag = 0

    # 需要产生一个随机数方向
    def get_random_direction(self):
        # 坦克移动和停止的随机数0,1,2,3,4
        # 1/5的概率会停止
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

    # 敌方坦克按照确定的随机方向，连续移动6步，让后才能再次改变方向
    def random_move(self):
        if (self.live):
            if (self.step == 0):
                self.get_random_direction()
                self.step = 8
            # 步长不等于0就要移动
            else:
                # 返回的是当前self坦克与其余坦克有没有碰撞的列表,False没有碰撞，True有碰撞
                All_bool = self.collide()
                # 如果没有碰撞一定是self与所有的坦克都没有碰撞
                for i in All_bool:
                    # 一直找，直到发现有碰撞为止，没有的话就说明self坦克与其余坦克没有任何碰撞
                    if (i == False or i == 0):
                        continue
                    # 有碰撞就设置flag=1
                    else:
                        self.flag = 1
                # 没有碰撞
                if (self.flag == 0):
                    self.move()
                    self.step -= 1
                # 如果有碰撞，就保存原来位置不动
                else:
                    # 先要不动
                    self.stay()
                    self.flag = 0
                    # 然后再运动
                    self.move()
                    self.step -= 1

    # 检测self坦克与其余坦克有没有碰撞
    def collide(self):
        All_bool = [0] * 100
        x = 0
        # 把所有坦克都放在一个列表中，包括我方坦克
        All_Tank = list(TankMain.enemy_list)
        for i, enemy in enumerate(All_Tank, 0):
            if (self != enemy):
                # 把调用坦克对象和其他坦克对象进行碰撞检测
                # 返回True是有碰撞，False是没有碰撞
                is_hit = pygame.sprite.collide_rect(self, enemy)
                All_bool[x] = is_hit
                x = x + 1
        # 在没有碰撞的时候进行移动
        return All_bool

    # 随机开火
    def random_fire(self):
        r = random.randint(0, 50)
        if (r == 10) or (r == 20):
            m = self.fire()
            TankMain.enemy_missile_list.add(m)


# 炮弹类
class Missile(BaseItem):
    width = 12
    height = 12

    def __init__(self, screem, tank):
        # 既要实现子类的方法又要实现父类中重名的方法就需要super
        super().__init__(screem)
        self.tank = tank
        # 坦克在移动或者显示过程中要用到的游戏屏幕
        self.scremm = screem
        # 炮弹的方向由坦克的方向决定
        self.direction = tank.direction
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
        # 炮弹需要从坦克的中心打出来
        self.rect.left = tank.rect.left + (tank.width - self.width) / 2
        self.rect.top = tank.rect.top + (tank.height - self.height) / 2
        # 决定炮弹是否消灭了
        self.live = True
        # 默认是敌方的炮弹
        # 就是等于True是我方炮弹，False是敌方炮弹
        self.good = False

    # 炮弹移动
    def move(self):
        # 如果炮弹活着
        if (self.live):
            if len(TankMain.my_tank_missile_list) != 0:
                hit_missile = pygame.sprite.collide_rect(self, TankMain.wall)
                # 有碰撞
                if (hit_missile == True):
                    self.live = False
                    # 删除敌方坦克子弹
                    TankMain.my_tank_missile_list.remove(self)
            if (self.direction == "L"):
                # 判断坦克是否已经在边界上
                if (self.rect.left > 0):
                    self.rect.left = self.rect.left - self.speed
                # 坦克已经碰到边界了
                else:
                    self.live = False
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

    # 炮弹击中坦克,1、我方炮弹击中敌方坦克；2、敌方炮弹击中我方坦克
    def hit_tank(self):
        # 如果是我方的炮弹
        if (self.good):
            # 返回的是敌方坦克的列表,判断精灵之间是否重叠
            hit_list = pygame.sprite.spritecollide(self, TankMain.enemy_list, False)
            for e in hit_list:
                e.live = False
                # 如果敌方坦克被击中，则删除敌方坦克
                TankMain.enemy_list.remove(e)
                # 把炮弹弄死
                self.live = False
                # 产生爆炸对象
                explode = Explode(self.screem, e.rect)
                TankMain.explode_list.append(explode)


# 爆炸类
class Explode(BaseItem):
    def __init__(self, screem, rect):
        super().__init__(screem)
        self.live = True
        # 一个炸弹由五张图片构成
        self.images = [pygame.image.load("image_b/bomb1.png"), \
                       pygame.image.load("image_b/bomb2.png"), \
                       pygame.image.load("image_b/bomb3.png"), \
                       pygame.image.load("image_b/bomb4.png"), \
                       pygame.image.load("image_b/bomb5.png")]
        # 显示的时间和步长
        self.step = 0
        # 爆炸的位置和爆炸前坦克的位置一样
        # 在构建爆炸时把坦克的rect传进来
        self.rect = rect

    # 循环调用
    def display(self):
        if (self.live):
            # 最后一张图片已经显示了
            if (self.step == len(self.images)):
                self.live = False
            else:
                self.image = self.images[self.step]
                self.screem.blit(self.image, self.rect)
                self.step += 1
        else:
            # 删除对象
            pass


# 游戏中的障碍物
class Wall(BaseItem):
    def __init__(self, screem, left, top, width, height):
        # 当要调用与父类重名的方法时使用super()
        super().__init__(screem)
        # 矩形
        self.rect = Rect(left, top, width, height)
        # 颜色红色
        self.color = (255, 0, 0)

    # 从写父类方法
    def display(self):
        # 画出矩形
        self.screem.fill(self.color, self.rect)

    # 针对墙和其他坦克和子弹碰撞检测
    def hit_other(self):
        if (TankMain.my_tank):
            # 我方坦克和墙进行碰撞检测,返回True表示有碰撞
            # 表示调用对象和我的坦克之间有无碰撞
            is_hit = pygame.sprite.collide_rect(self, TankMain.my_tank)
            if (is_hit):
                TankMain.my_tank.stop = True
                TankMain.my_tank.stay()
        # 如果敌人坦克不为空
        if len(TankMain.enemy_list) != 0:
            # 判断墙和敌方坦克有没有碰撞
            # 有的话返回的是列表
            hit_list = pygame.sprite.spritecollide(self, TankMain.enemy_list, False)
            for e in hit_list:
                e.stop = True
                e.stay()
        # 判断敌人坦克的子弹和墙有无碰撞
        if len(TankMain.enemy_missile_list) != 0:
            hit_missile = pygame.sprite.spritecollide(self, TankMain.enemy_missile_list, False)
            for m in hit_missile:
                m.live = False
                # 如果敌方坦克被击中，则删除敌方坦克子弹
                TankMain.enemy_missile_list.remove(m)


# 创建坦克对象，不是作为模块调用时执行
if __name__ == "__main__":
    game = TankMain()
    game.startGame()
