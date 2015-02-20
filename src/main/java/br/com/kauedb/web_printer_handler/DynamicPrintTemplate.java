package br.com.kauedb.web_printer_handler;


class DynamicPrintTemplate implements PrintTemplate {

    private String template;

    public DynamicPrintTemplate(String template) {
        this.template = template;
    }

    @Override
    public String build() {
    	return this.template;
    }
    
    public static DynamicPrintTemplate create(String template) {
    	return new DynamicPrintTemplate(template);
    }
}
