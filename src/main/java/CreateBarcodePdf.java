import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.Barcode;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.Barcode39;
import com.itextpdf.text.pdf.BarcodeEAN;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 生成各种条形码，并输出到 pdf 中
 * Created by plough on 2019/5/24.
 */
public class CreateBarcodePdf {
    public static void main(String... args) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("barcode.pdf"));

        document.open();
        PdfContentByte cb = writer.getDirectContent();

        Barcode128 barcode128 = new Barcode128();
        barcode128.setCode("baimoz.me");
        barcode128.setCodeType(Barcode.CODE128);
        Image code128Image = barcode128.createImageWithBarcode(cb, null, null);
        document.add(code128Image);

        Barcode39 barcode39 = new Barcode39();
        barcode39.setCode("123456789");
        Image code39Image = barcode39.createImageWithBarcode(cb, null, null);
        document.add(code39Image);

        BarcodeEAN barcodeEAN = new BarcodeEAN();
        barcodeEAN.setCode("3210123456789");
        barcodeEAN.setCodeType(Barcode.EAN13);
        Image codeEANImage = barcodeEAN.createImageWithBarcode(cb, null, null);
        document.add(codeEANImage);
        document.newPage();

        BarcodeQRCode barcodeQRCode = new BarcodeQRCode("https://baimoz.me", 1000, 1000, null);
        Image codeQrImage = barcodeQRCode.getImage();
        codeQrImage.scaleAbsolute(100, 100);
        document.add(codeQrImage);

        document.close();
    }
}
