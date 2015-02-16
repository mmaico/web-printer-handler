package br.com.kauedb.web_printer_handler;

import org.junit.Test;

import javax.print.*;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PrintTest {


    @Test
    public void shouldFindPrinter() {
        final String name = "Argox";

        final PrintService namedPrintService = PrintServiceFactory.getInstance().getPrintServiceByName(name);

        assertThat(namedPrintService.getName(), is("Argox OS-214 plus series PPLA"));

    }


    @Test
    public void shouldPrint() throws Exception {
        final String name = "Argox";
        final PrintService namedPrintService = PrintServiceFactory.getInstance().getPrintServiceByName(name);

        final String personName = "Marcelo Maico";
        final String company = "Kohen Sistemas";
        final String position = "Analista";
        final String supplier = "-Fornecedor";


        final StringBuilder builder = new StringBuilder("\u0002O0220\n")
                .append("\u0002M3000\n")
                .append("\u0002c0000\n")
                .append("\u0002f320\n")
                .append("\u0002e\n")
                .append("\u0002L\n")
                .append("A2\n")
                .append("C0000\n")
                .append("D11\n")
                .append("H13\n")
                .append("SC\n")
                .append("PC\n")
                .append("R0000\n")
                .append("z\n")
                .append("^01\n")
                .append("191200501300014" + personName + "\n")
                .append("191200501300015" + personName + "\n")
                .append("191200400950015" + company + "\n")
                .append("141100000700014" + position + "\n")
                .append("141100000100014\n")
                .append("1e4202000260240B8837738\n")
                .append("1200000001002408837738" + supplier + "\n")
                .append("Q0001\n")
                .append("E\n");


        final DocPrintJob dpj = namedPrintService.createPrintJob();
        final InputStream stream = new ByteArrayInputStream(builder.toString().getBytes());
        final DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;

        final HashDocAttributeSet attributes = new HashDocAttributeSet();
        attributes.add(new MediaPrintableArea(50, 50, 100, 100, MediaPrintableArea.MM));

        final Doc doc = new SimpleDoc(stream, flavor, null);
        dpj.print(doc, null);

    }


}