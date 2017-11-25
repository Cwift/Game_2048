# Game_2048
用java写的2048

使用的容器是JTable

目前有两个bug需要解决：

1： 当游戏开始时table不显示，需要按一下方向键才能显示，为了解决这个问题，将button设置成需要初始点一下。不知道为什么点一下之后就可以显示了，但是在初始化函数里面直接调用button的函数也不能显示。

2： 当游戏进入后期，即cell比较多的时候，会出现table无法刷新的情况，此时需要用鼠标点击table的cell才能刷新。

如果有人能解决或者有更好的容器的话希望可以告诉我谢谢！

-----------------------------------------------------------------
2048 by java

The container is JTable

There are still 2 bugs need debug

1: The table can't be seen when game start,to slove this problem, I set the button need tap first. And, if I call the button's function it still useless.

2: When the cell is full, the table may not refresh and need click the cell.

Please tell me if you know how to debug these bugs or there are better container than JTable. Thanks a lot!

Poor English...
