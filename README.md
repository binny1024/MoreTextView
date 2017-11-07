## MoreTextView
一行代码实现TextView的“展开”和“收起”
#### 引用
    compile 'com.smart.moretext:moretext:1.0.0
1、在文本末尾，实现点击“展开”---展开所有文本，并把“展开”改为“收起”；点击“收起”，则收起文本； 2、“展开”和“收起”紧跟文本末尾。并且不换行。
# 需求介绍：
1、在文本末尾，实现点击“展开”---展开所有文本，并把“展开”改为“收起”；点击“收起”，则收起文本；
2、“展开”和“收起”紧跟文本末尾。并且不换行。
### 函数说明
#### 1.1 构造函函数
 
##### 1.1.1 文字形式
        /**
         * @param textView  文本框
         * @param oriMsg    原始信息
         * @param textOpen  展开性质的文字
         * @param textClose 关闭性质的文字
         */
        public UtilMoreText(final TextView textView, String oriMsg, String textOpen, String textClose) {
        }
##### 1.1.2 文字形式    
        /**
         * @param textView 文本框
         * @param oriMsg   原始文字
         */
        public UtilMoreText(final TextView textView, String oriMsg) {
        
        }
##### 1.1.3 图片形式  
        /**
         * @param textView      文本框
         * @param oriMsg        原始文字
         * @param drawableOpen  展开图标
         * @param drawableColse 关闭图标
         */
        public UtilMoreText(final TextView textView, String oriMsg, Drawable drawableOpen, Drawable drawableColse) {
        }
####成员方法介绍
        
    /** 设置结尾 文字的颜色
     * @param spanTextColor 颜色id
     * @return 本类实例
     */
    public UtilMoreText setSpanTextColor(int spanTextColor) {
       
    }
### 使用说明
#### 设置文本
    new UtilMoreText(tv1,msg).setSpanTextColor(R.color.colorAccent).createString();
#### 设置图标
    new UtilMoreText(tv2,msg,down,up).createImg();
#### UtilMoreText.java 源码地址
https://github.com/XBean1024/MoreTextView/blob/master/moretext/src/main/java/com/smart/moretext/UtilMoreText.java
#### 效果图，(结尾随意找的图片，项目中更换一下)
![](https://github.com/xubinbin1024/MoreTextView/blob/master/gif/3.gif)

#### 推荐，其他网友的一个组合view实现的，效果也不错
##### 先看下效果图吧:
![simple.gif](https://github.com/1002326270xc/MoreTextView/blob/master/photo/demo.gif)
##### 地址
https://github.com/XBean1024/MoreTextView-1
##### QQ：交流群 ：192268854
![](https://github.com/Xbean1024/XHttp/blob/master/gif/QQ.JPG)
