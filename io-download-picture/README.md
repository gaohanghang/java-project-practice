# 给定url和路径下载图片

## 快速开始

````java
public class DownloadPicTest {
    public static void main(String[] args) {
        String url = "http://lorempixel.com/1600/900";
        String path="/users/gaohanghang/download/";
        DownloadPicFromURL.downloadPicture(url,path + UUID.randomUUID() + ".jpg");
    }
}
````

## 运行后示例
![](https://ws4.sinaimg.cn/large/006tNc79ly1fzhq34snxxj30le0c4js9.jpg)