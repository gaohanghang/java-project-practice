import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Description: 使用URL读取页面内容
 * @author: Gao Hang Hang
 * @date 2019/02/08 17:59
 */
public class Test03 {
    public static void main(String[] args) {
        try {
            // 创建一个URL实例
            URL url = new URL("http://www.baidu.com");
            InputStream inputStream = url.openStream();
            // 将字节输入流转换为字符输入流
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            // 为字符输入流添加缓冲
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String data = bufferedReader.readLine();
            while (data != null) { //循环读取数据
                System.out.println(data);
                data = bufferedReader.readLine();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
