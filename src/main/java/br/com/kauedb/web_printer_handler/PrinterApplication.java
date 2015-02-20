package br.com.kauedb.web_printer_handler;

import java.applet.Applet;


public class PrinterApplication extends Applet implements Application {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4154173797889778988L;
	
	private StringBuilder template = new StringBuilder("");
	private String printerName = ArgoxPrinterHandler.NAME_DEFAULT_PRINTER;
	
    @Override
    public void start() {
        sendPrintToArgox();
    }

    public void usePrinter(String printerToUse) {
    	this.printerName = printerToUse;
    }
    
    public void sendPrintToArgox() {

        final String personName = getParameter("personName");
        final String company = getParameter("company");
        final String position = getParameter("position");
        final String supplier = getParameter("supplier");

        sendPrintToArgox(personName, company, position, supplier);
    }
    
    public void append(String templateParam) {
    	template.append(templateParam);
    }
    
    public void print() {
    	DynamicPrintTemplate printTemplate = DynamicPrintTemplate.create(template.toString());
    	ArgoxPrinterHandler printerHandler = new ArgoxPrinterHandler(printTemplate);
    	printerHandler.print(printerName);
    }

    public void sendPrintToArgox(final String personName, final String company, final String position, final String supplier) {

        final SimplePrintTemplate simplePrintTemplate = new SimplePrintTemplate(personName, company, position, supplier);

        final ArgoxPrinterHandler argoxPrinterHandler = new ArgoxPrinterHandler(simplePrintTemplate);

        argoxPrinterHandler.print();

    }

}
