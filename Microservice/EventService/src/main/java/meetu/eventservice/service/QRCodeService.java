/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.eventservice.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.bson.internal.Base64;
import org.springframework.stereotype.Service;

/**
 *
 * @author wdrdr
 */
@Service
public class QRCodeService {
	private final String LOGO = "https://www.mkyong.com/wp-content/uploads/2016/11/spring-logo-new.png";
	private final String DIR = "D:\\photo";
	private final String ext = ".png";
	private final String CONTENT = "some content here";
	private final int WIDTH = 300;
    private final int HEIGHT = 300;
	    //private final String LOGO = "logo_url";
	    //private final String CONTENT = "some content here";

	public byte[] getQRCodeImage(String text, int width, int height) {
		 Map<EncodeHintType, ErrorCorrectionLevel> hints = new HashMap<>();
		 hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		 QRCodeWriter qrCodeWriter = new QRCodeWriter();
		 BitMatrix bitMatrix = null;
		 ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
         
         try {
        	 cleanDirectory(DIR);
             initDirectory(DIR);
	         bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height,hints);
	       
	         //MatrixToImageWriter.writeToStream(bitMatrix, "png", byteArrayOutputStream);
	         BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix, getMatrixConfig());
	         BufferedImage overly = getOverly(LOGO);
	         
	         int deltaHeight = qrImage.getHeight() - overly.getHeight();
	         int deltaWidth = qrImage.getWidth() - overly.getWidth();
	         
	         BufferedImage combined = new BufferedImage(qrImage.getHeight(), qrImage.getWidth(), BufferedImage.TYPE_INT_ARGB);
	         Graphics2D g = (Graphics2D) combined.getGraphics();
	         // Write QR code to new image at position 0/0
	         g.drawImage(qrImage, 0, 0, null);
	         g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	         g.drawImage(overly, (int) Math.round(deltaWidth / 2), (int) Math.round(deltaHeight / 2), null);	         
	       
	         
	        
	         ImageIO.write(combined, "png", byteArrayOutputStream);	        
	         
		   
	         
	     } catch (WriterException e) {
	            e.printStackTrace();
	            //LOG.error("WriterException occured", e);
	        } catch (IOException e) {
	            e.printStackTrace();
	            //LOG.error("IOException occured", e);
	        }
         return  byteArrayOutputStream.toByteArray();
        
	 }
	
	
	 private BufferedImage getOverly(String LOGO) throws IOException {
         URL url = new URL(LOGO);
         return ImageIO.read(url);
     }

     private void initDirectory(String DIR) throws IOException {
         Files.createDirectories(Paths.get(DIR));
     }

     private void cleanDirectory(String DIR) {
         try {
             Files.walk(Paths.get(DIR), FileVisitOption.FOLLOW_LINKS)
                     .sorted(Comparator.reverseOrder())
                     .map(Path::toFile)
                     .forEach(File::delete);
         } catch (IOException e) {
             // Directory does not exist, Do nothing
         }
     }

    private MatrixToImageConfig getMatrixConfig() {
         // ARGB Colors
        // Check Colors ENUM
        return new MatrixToImageConfig(QRCodeService.Colors.BLACK.getArgb(), QRCodeService.Colors.WHITE.getArgb());
   }

     private String generateRandoTitle(Random random, int length) {
         return random.ints(48, 122)
                 .filter(i -> (i < 57 || i > 65) && (i < 90 || i > 97))
                 .mapToObj(i -> (char) i)
                 .limit(length)
                 .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                 .toString();
     }

     public enum Colors {

         BLUE(0xFF40BAD0),
         RED(0xFFE91C43),
         PURPLE(0xFF8A4F9E),
         ORANGE(0xFFF4B13D),
         WHITE(0xFFFFFFFF),
         BLACK(0xFF000000);

         private final int argb;

         Colors(final int argb){
             this.argb = argb;
         }

         public int getArgb(){
             return argb;
         }
     }
     }
