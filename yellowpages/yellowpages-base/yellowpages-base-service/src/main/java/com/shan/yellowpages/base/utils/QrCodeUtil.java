//package com.shan.yellowpages.base.utils;
//
//import java.awt.*;
//import java.awt.color.ColorSpace;
//import java.awt.image.BufferedImage;
//import java.awt.image.ColorConvertOp;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.URL;
//import java.util.HashMap;
//import java.util.Hashtable;
//import java.util.Map;
//
//import javax.imageio.ImageIO;
//import javax.imageio.stream.FileImageOutputStream;
//
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.google.zxing.BarcodeFormat;
//import com.google.zxing.BinaryBitmap;
//import com.google.zxing.DecodeHintType;
//import com.google.zxing.EncodeHintType;
//import com.google.zxing.LuminanceSource;
//import com.google.zxing.MultiFormatReader;
//import com.google.zxing.MultiFormatWriter;
//import com.google.zxing.Reader;
//import com.google.zxing.ReaderException;
//import com.google.zxing.Result;
//import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
//import com.google.zxing.common.BitMatrix;
//import com.google.zxing.common.HybridBinarizer;
//import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
//
///**
// * 自生成二维码工具类
// *
// * @author bruce
// */
//public class QrCodeUtil {
//
//    private static final Logger logger = LoggerFactory.getLogger(QrCodeUtil.class);
//
//    public static void main(String[] args) throws IOException {
//        byte[] b = QrCodeUtil.genQRCodePic("http://w.top-jin.cn/yqdyhq/index2.html?chn=dyq&share=c7a33327af43ef861ca6d6ce856b3555", null);
//        //  /Users/bruce/Desktop/xx.jpeg
//        String imgLocatePath = "E:\\qrCodeImage";
//        FileImageOutputStream imageOutput = new FileImageOutputStream(new File(imgLocatePath+"xx.jpeg"));
//        imageOutput.write(b, 0, b.length);
//        imageOutput.close();
//    }
//
//    /**
//     * 根据内容构造二维码
//     *
//     * @param iconUrl 中间的icon
//     * @return 字节数组
//     */
//    public static byte[] genQRCodePic(String content, String iconUrl) {
//        logger.debug("[QrCodeUtil][genQRCodePic]entering, content:{}, iconUrl:{}", content, iconUrl);
//
//        return genQRCodePic(content, iconUrl, QRCodeMeta.DEFAULT_BACKGROUND_COLOR, QRCodeMeta.DEFAULT_QR_COLOR, null);
//    }
//
//    /**
//     * 根据内容构造二维码
//     *
//     * @param iconUrl 中间的icon
//     * @param scale   缩放
//     * @return 字节数组
//     */
//    public static byte[] genQRCodePic(String content, String iconUrl, Double scale) {
//        logger.debug("[QrCodeUtil] [genQRCodePic] enter, content = {}, iconUrl = {}, scale = {}", content, iconUrl, scale);
//
//        return genQRCodePic(content, iconUrl, QRCodeMeta.DEFAULT_BACKGROUND_COLOR, QRCodeMeta.DEFAULT_QR_COLOR, scale);
//    }
//
//    /**
//     * 生成二维码数组
//     *
//     * @param scale 缩放
//     */
//    private static byte[] genQRCodePic(String content, String iconUrl, int backgroundColor, int qrColor, Double scale) {
//        logger.debug("[QrCodeUtil] [genQRCodePic] enter, content = {}, iconUrl = {}, backgroundColor = {}, qrColor = {}, scale = {}", content, iconUrl, backgroundColor, qrColor, scale);
//
//        BufferedImage img = genQRCode(content, backgroundColor, qrColor, scale);
//        //产生中央图片
//        if (StringUtils.isNotBlank(iconUrl)) {
//            drawIcon(img, iconUrl, backgroundColor, scale);
//        }
//        return toByteArray(img);
//    }
//
//    /**
//     * 生成QR
//     *
//     * @param content 编码内容
//     */
//    private static BufferedImage genQRCode(String content, int backgroundColor, int qrColor, Double scale) {
//        logger.debug("[QrCodeUtil] [genQRCode] enter, content = {}, backgroundColor = {}, qrColor = {}, scale = {}", content, backgroundColor, qrColor, scale);
//
//        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
//        //设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小
//        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
//        hints.put(EncodeHintType.CHARACTER_SET, "utf8");
////        hints.put(EncodeHintType.MARGIN, 0);
//        BufferedImage imageData = null;
//
//        int imgWidth = QRCodeMeta.QR_WIDTH;
//        int imgHeight = QRCodeMeta.QR_HEIGHT;
//        if (scale != null && scale > 0 && scale != 1) {
//            imgWidth = (int) (QRCodeMeta.QR_WIDTH * scale);
//            imgHeight = (int) (QRCodeMeta.QR_HEIGHT * scale);
//        }
//
//        try {
//            // 生成QR码内存图
//            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE,
//                    imgWidth, imgHeight, hints);
//
//            bitMatrix = deleteWhite(bitMatrix);//删除白边
//
//            imageData = fileToBufferedImage(bitMatrix, qrColor, backgroundColor);
////          imageData = MatrixToImageWriter.toBufferedImage(bitMatrix);
//
//            // 内存图片转换色彩模型
//            ColorConvertOp cop = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_sRGB),
//                    null);
//            BufferedImage qrImg = new BufferedImage(imageData.getWidth(), imageData.getHeight(),
//                    BufferedImage.TYPE_3BYTE_BGR);
////            //52 57 61
//            cop.filter(imageData, qrImg);
//
//            return qrImg;
//        } catch (Exception e) {
//            logger.error("genQRCode", e);
//        }
//        return null;
//    }
//
//    /**
//     * 在二维码图片中央画图
//     */
//    private static void drawIcon(BufferedImage bkImg, String iconUrl, int backgroundColor, Double scale) {
//        logger.debug("[QrcodeUtil][drawIcon]entering, iconUrl:{}, backgroundColor:{}, scale:{}", iconUrl, backgroundColor, scale);
//
//        // 读取图标
//        BufferedImage icon = null;
//        try {
//            URL url = new URL(iconUrl);
//            InputStream in = url.openStream();
//            icon = ImageIO.read(in);
//        } catch (Exception e) {
//            logger.error("[QrcodeUtil][drawIcon]read iconUrl error:{}, iconUrl:{}", e, iconUrl);
//        }
//
//        Graphics g = bkImg.getGraphics();
//
//        int padWidth = QRCodeMeta.PAD_WIDTH;
//        int padHeight = QRCodeMeta.PAD_HIGHT;
//        int iconWidth = QRCodeMeta.ICON_WIDTH;
//        int iconHeight = QRCodeMeta.ICON_HEIGHT;
//        if (scale != null && scale > 0 && scale != 1) {
//            padWidth = (int) (padWidth * scale);
//            padHeight = (int) (padHeight * scale);
//            iconWidth = (int) (iconWidth * scale);
//            iconHeight = (int) (iconHeight * scale);
//        }
//
//        int padLeft = (bkImg.getWidth() - padWidth) / 2;
//        int padTop = (bkImg.getHeight() - padHeight) / 2;
//
//        // 画icon周围的白色边
//        g.setColor(new Color(backgroundColor));
//        g.fillRect(padLeft, padTop, padWidth,
//                padHeight);
//
//        // 将图标画在背景图
//
//        int left = (bkImg.getWidth() - iconWidth) / 2;
//        int top = (bkImg.getHeight() - iconHeight) / 2;
//
//        g.drawImage(icon, left, top, iconHeight, iconWidth, null);
//    }
//
//    /**
//     * 将内存图片转换为字节数组
//     */
//    private static byte[] toByteArray(BufferedImage image) {
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        try {
//            ImageIO.write(image, QRCodeMeta.IMAGE_TYPE, outputStream);
//        } catch (IOException e) {
//            System.out.println("toByteArray error !" + e);
//        } finally {
//            if (outputStream != null) {
//                try {
//                    outputStream.close();
//                } catch (IOException e) {
//                    logger.error("toByteArray", e);
//                }
//            }
//        }
//        return outputStream.toByteArray();
//    }
//
//    /**
//     * 这是一段解析程序
//     */
//    public static void decode() {
//        try {
//            Reader reader = new MultiFormatReader();
//            String imgPath = "D:" + File.separator + "ningzai.jpg";
//            File file = new File(imgPath);
//            BufferedImage image;
//            try {
//                image = ImageIO.read(file);
//                if (image == null) {
//                    System.out.println("Could not decode image");
//                }
//                LuminanceSource source = new BufferedImageLuminanceSource(image);
//                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
//                Result result;
//                Hashtable hints = new Hashtable();
//                hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
//                //解码设置编码方式为：utf-8，
//                result = new MultiFormatReader().decode(bitmap, hints);
//                String resultStr = result.getText();
//                System.out.println("解析后内容：" + resultStr);
//
//            } catch (IOException ioe) {
//                logger.error("decode", ioe);
//            } catch (ReaderException re) {
//                logger.error("decode", re);
//            }
//
//        } catch (Exception ex) {
//            logger.error("decode", ex);
//        }
//    }
//
//
//    interface QRCodeMeta {
//
//        int QR_SIZE = 400;
//        /* 二维码默认宽度 */
//        int QR_WIDTH = 372;
//        /* 二维码默认高度 */
//        int QR_HEIGHT = 360;
//        /* 二维码图片类型 */
//        String IMAGE_TYPE = "jpg";
//
//        /* icon默认宽度 */
//        int ICON_WIDTH = 100;
//        /* icon默认高度 */
//        int ICON_HEIGHT = 100;
//        /* 边框宽度 */
//        int PAD_SIZE = 4;
//        /* 边框颜色 */
//        Color PAD_COLOR = Color.WHITE;
//
//        // 衍生数据
//        int ICON_LEFT = (QR_SIZE - ICON_WIDTH) / 2;
//
//        int ICON_TOP = (QR_SIZE - ICON_HEIGHT) / 2;
//
//        int PAD_LEFT = ICON_LEFT - PAD_SIZE;
//
//        int PAD_TOP = ICON_TOP - PAD_SIZE;
//
//        int PAD_WIDTH = ICON_WIDTH + 2 * PAD_SIZE;
//
//        int PAD_HIGHT = ICON_HEIGHT + 2 * PAD_SIZE;
//
//        int DEFAULT_BACKGROUND_COLOR = 0XFFFFFF;
//
//        int DEFAULT_QR_COLOR = 0X000000;
//
//    }
//
//    /**
//     * 删除白边
//     */
//    public static BitMatrix deleteWhite(BitMatrix matrix) {
//        int[] rec = matrix.getEnclosingRectangle();
//        int resWidth = rec[2] + 1;
//        int resHeight = rec[3] + 1;
//
//        BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
//        resMatrix.clear();
//        for (int i = 0; i < resWidth; i++) {
//            for (int j = 0; j < resHeight; j++) {
//                if (matrix.get(i + rec[0], j + rec[1]))
//                    resMatrix.set(i, j);
//            }
//        }
//        return resMatrix;
//    }
//
//    /**
//     * 构建初始化二维码 可以设置二维码
//     */
//    public static BufferedImage fileToBufferedImage(BitMatrix bm) {
//        return fileToBufferedImage(bm, QRCodeMeta.DEFAULT_QR_COLOR, QRCodeMeta.DEFAULT_BACKGROUND_COLOR);
//    }
//
//    /**
//     * 构建初始化二维码 可以设置二维码
//     * 0xE3E8EB
//     */
//    private static BufferedImage fileToBufferedImage(BitMatrix bm, int codeCol, int backCol) {
//        BufferedImage image = null;
//        try {
//            int w = bm.getWidth(), h = bm.getHeight();
//            image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
//
//            for (int x = 0; x < w; x++) {
//                for (int y = 0; y < h; y++) {
//                    image.setRGB(x, y, bm.get(x, y) ? codeCol : backCol);
//                }
//            }
//
//        } catch (Exception e) {
//            logger.error("fileToBufferedImage", e);
//        }
//        return image;
//    }
//
//}
