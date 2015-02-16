package br.com.kauedb.web_printer_handler;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kaue
 */
public class PrintServiceFactory {


    private static final int INITIAL_CAPACITY = 5;
    private static final PrintServiceFactory INSTANCE = new PrintServiceFactory();
    private Map<String, PrintService> cache = new HashMap<>(INITIAL_CAPACITY);

    private PrintServiceFactory() {
        super();
    }

    public static PrintServiceFactory getInstance() {
        return INSTANCE;
    }

    public PrintService getPrintServiceByName(String name) {

        if (cache.get(name) == null) {
            final PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
            for (PrintService printService : printServices) {
                if (printService.getName().toLowerCase().contains(name.toLowerCase())) {
                    cache.put(name, printService);
                    return printService;
                }
            }
        } else {
            return cache.get(name);
        }

        throw new PrintServiceNotFoundException();
    }


    public static class PrintServiceNotFoundException extends RuntimeException {

    }

}
