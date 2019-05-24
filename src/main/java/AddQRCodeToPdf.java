import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 给现有的 pdf 加上二维码
 * Created by plough on 2019/5/24.
 */
public class AddQRCodeToPdf {
    public static void main(String[] args) {
        try {
            PdfReader pdfReader = new PdfReader("src.pdf");
            PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream("Modified.pdf"));
            PdfContentByte canvas = pdfStamper.getOverContent(1);

            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("I'm cool! Very Cool!"), 250, 650, 0);

            BarcodeQRCode barcodeQRCode = new BarcodeQRCode("http://baimoz.me", 80, 80, null);
            Image img = barcodeQRCode.getImage();

            float x = 250;
            float y = 550;
            img.setAbsolutePosition(x, y);
            pdfStamper.getOverContent(1).addImage(img);

            pdfStamper.close();
            pdfReader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (DocumentException e)
        {
            e.printStackTrace();
        }
    }
}
