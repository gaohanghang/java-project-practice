import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class QRGenDemo {

    public static void main(String[] args) throws IOException {
        File file = QRCode.from("wwww.google.com").to(ImageType.PNG)
                .withSize(200, 200)
                .file();

        String fileName = "qugen-qrcode.png";

        Path path = Paths.get(fileName);
        if (Files.exists(path)) {
            Files.delete(path);
        }

        Files.copy(file.toPath(), path);


        Path colorPath = Paths.get("qrgen-color-qrcode.png");
        if (Files.exists(colorPath)) {
            Files.delete(colorPath);
        }

        file = QRCode.from("www.google.com")
                .withColor(Color.RED.getRGB(), Color.WHITE.getRGB())
                .withSize(200, 200)
                .withErrorCorrection(ErrorCorrectionLevel.Q)
                .file();
        Files.copy(file.toPath(), colorPath);
    }
}
