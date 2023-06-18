import sys
from pywebio import *
from threading import Thread
from time import sleep, ctime
from pywebio.input import *
from pywebio.output import *
from pywebio.pin import *
from pywebio import session
# from webio.application import app
import hello_world

# 控制台输出 hello world
def hello(name="world"):
    print(f"hello {name}")


# 服务器线程运行的代码
def pywebio_sever():
    put_text("start pywebio server...")


# 启动一个pywebio 的服务器
def start_pywebio():
    start_server(pywebio_sever, auto_open_webbrowser=True, port=8081, debug=False)

# 脚本模式启动 pywebio
def pywebio_script():
    put_text("服务器已经启动")
    # 在浏览器输入表单提交之前，都不会往下运行，因为它是阻塞式的
    age = input("你几岁了?", type=NUMBER)
    # 获取到输出后输出到页面上
    put_text(f"You age is {age}")

# 程序入口
if __name__ == "__main__":
    # print(sys.argv)
    # hello(sys.argv[1] if len(sys.argv) > 1 else "world")
    hello_world.hello()
    # start_pywebio()
    # pywebio_script()
