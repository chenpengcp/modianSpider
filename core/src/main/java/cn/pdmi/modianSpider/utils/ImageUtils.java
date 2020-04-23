package cn.pdmi.modianSpider.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;

public class ImageUtils {

    public static void zoomImage(String filePath) throws Exception {
        BufferedImage im = ImageIO.read(new FileInputStream(filePath));
        /* 原始图像的宽度和高度 */
        DecimalFormat df = new DecimalFormat("0.00");
        int toWidth = 64 * im.getWidth() / 120;
        int toHeight = 64 * im.getWidth() / 120;
        /* 新生成结果图片 */
        BufferedImage result = new BufferedImage(toWidth, toHeight, BufferedImage.TYPE_INT_RGB);
        result.getGraphics().drawImage(im.getScaledInstance(toWidth, toHeight, java.awt.Image.SCALE_SMOOTH), 0, 0,
                null);
        ImageIO.write(result, "jpg", new File(filePath));
    }

    public static void main(String[] args) throws Exception {
        ImageUtils.zoomImage("D:/360downloads/t_u4e01u7d2bu598d.jpg");
    }
}
