import java.util.UUID;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/24 15:47
 */
public class DownloadPicTest {
    public static void main(String[] args) {
        String url = "http://lorempixel.com/1600/900";
        String path="/users/gaohanghang/download/";
        DownloadPicFromURL.downloadPicture(url,path + UUID.randomUUID() + ".jpg");
    }
}
