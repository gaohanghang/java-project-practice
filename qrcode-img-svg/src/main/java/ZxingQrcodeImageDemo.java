import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.jfree.graphics2d.svg.SVGGraphics2D;
import org.jfree.graphics2d.svg.ViewBox;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

public class ZxingQrcodeImageDemo {

    public static void main(String[] args) throws IOException {
        BufferedImage qrCode = getQRCode("www.google.com", 200, 200);
        File file = new File("qrcode.png");
        ImageIO.write(qrCode, "png", file);

        String qrCodeSvg = getQRCodeSvg("www.google.com", 200, 200, false);
        System.out.println(qrCodeSvg);
    }

    public static BufferedImage getQRCode(String targetUrl, int width, int height) {
        try {
            Hashtable<EncodeHintType, Object> hintMap = new Hashtable<>();

            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix byteMatrix = qrCodeWriter.encode(targetUrl, BarcodeFormat.QR_CODE, width, height, hintMap);
            int CrunchifyWidth = byteMatrix.getWidth();

            BufferedImage image = new BufferedImage(CrunchifyWidth, CrunchifyWidth, BufferedImage.TYPE_INT_RGB);
            image.createGraphics();

            Graphics2D graphics = (Graphics2D) image.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < CrunchifyWidth; i++) {
                for (int j = 0; j < CrunchifyWidth; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            return image;
        } catch (WriterException e) {
            e.printStackTrace();
            throw new RuntimeException("Error getting QR Code");
        }

    }

    public static String getQRCodeSvg(String targetUrl, int width, int height, boolean withViewBox){
        SVGGraphics2D g2 = new SVGGraphics2D(width, height);
        BufferedImage qrCodeImage = getQRCode(targetUrl, width, height);
        g2.drawImage(qrCodeImage, 0,0, width, height, null);

        ViewBox viewBox = null;
        if ( withViewBox ){
            viewBox = new ViewBox(0,0,width,height);
        }
        return g2.getSVGElement(null, true, viewBox, null, null);
    }
}