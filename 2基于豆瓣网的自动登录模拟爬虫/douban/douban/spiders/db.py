# -*- coding: utf-8 -*-
import scrapy
from scrapy.http import Request, FormRequest
import urllib.request


class DbSpider(scrapy.Spider):
    name = 'db'
    allowed_domains = ['douban.com']
    # 浏览器头文件
    header = {
        "User-Agent": 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.221 Safari/537.36 SE 2.X MetaSr 1.0'}

    # start_urls = ['http://douban.com/']

    def start_requests(self):
        return [Request('https://accounts.douban.com/login', callback=self.parse, meta={"cookiejar": 1})]

    def parse(self, response):
        # 爬取验证码网址
        captcha = response.xpath('//img[@id="captcha_image"]/@src').extract()
        if len(captcha) > 0:
            captcha[0] = captcha[0].replace('amp;', '')
        else:
            captcha = []
        # 设置要爬去的网址
        url = 'https://accounts.douban.com/login'

        if (len(captcha) > 0):
            print("有验证码")
            # 设置验证码保存路径
            localpath = "C:/Users/Administrator/Desktop/pycharm/douban/captcha.jpg"
            # 把验证码网址图片保存下来
            urllib.request.urlretrieve(captcha[0], filename=localpath)
            print("请输入验证码：")
            captcha_value = input()
            # 设置登入信息：用户名，密码，个人主cd页
            data = {"form_email": "15168317617",
                    "form_password": "1115101321",
                    "captcha-solution": captcha_value,
                    "redir": "https://www.douban.com/people/130089470/",
                    }

        else:
            print("无验证码")
            # 设置登入信息：用户名，密码，个人主页
            data = {"form_email": "15168317617",
                    "form_password": "1115101321",
                    "redir": "https://www.douban.com/people/130089470/",
                    }
        print("登录中……")
        return [FormRequest.from_response(response,
                                          meta={"cookiejar": response.meta["cookiejar"]},
                                          headers=self.header,
                                          formdata=data,
                                          callback=self.next,
                                          )]

    # 输出爬取内容，回调函数
    def next(self, response):
        print("登录成功")
        title = response.xpath('//div[@class="note-header pl2"]/a[@class="ll"]/text()').extract()
        note = response.xpath('//div[@class="note"]/text()').extract()
        print(title[0])
        print(note[0])
