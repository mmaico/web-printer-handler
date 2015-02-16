package br.com.kauedb.web_printer_handler;

import javax.print.*;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Created by Kaue
 */
public class ArgoxPrinterHandler implements PrinterHandler {

    private final PrintTemplate printTemplate;

    public ArgoxPrinterHandler(final PrintTemplate printTemplate) {
        this.printTemplate = printTemplate;
    }

    @Override
    public void print() {

        final PrintService printService = PrintServiceFactory.getInstance().getPrintServiceByName("Argox");

        final String printing = printTemplate.build();

        final DocPrintJob docPrintJob = printService.createPrintJob();
        final InputStream stream = new ByteArrayInputStream(printing.getBytes());
        final DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;

        final HashDocAttributeSet attributes = new HashDocAttributeSet();
        attributes.add(new MediaPrintableArea(50, 50, 100, 100, MediaPrintableArea.MM));

        final Doc doc = new SimpleDoc(stream, flavor, null);

        try {
            docPrintJob.print(doc, null);
        } catch (PrintException e) {
            e.printStackTrace();
        }

    }


}
