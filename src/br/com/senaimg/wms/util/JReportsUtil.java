package br.com.senaimg.wms.util;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

public abstract class JReportsUtil {

    private static void exportReport(String fileSource, File pdfOut, JRDataSource dataSource, boolean open) {
        try {
            // Fill the report using an empty data source
            JasperPrint print = JasperFillManager.fillReport(fileSource, null, dataSource);

            // Create a PDF exporter
            JRExporter exporter = new JRPdfExporter();
            String outFileName = pdfOut.getAbsolutePath();
            // Configure the exporter (set output file name and print object)
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, outFileName);
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);

            // Export the PDF file
            exporter.exportReport();
            if (pdfOut.isFile() && pdfOut.exists() && open) {
                Desktop.getDesktop().open(pdfOut);
            }
        } catch (JRException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void exportReport(String fileNameIn, File out, JRDataSource dataSource) {
        System.out.println("ue");

//        String fileName = System.getProperty("user.dir") + "\\reports\\" + fileNameIn + ".jasper";
        String fileName = System.getProperty("user.dir") + "\\reports\\" + fileNameIn + ".jasper";

        

        File pdf = out;

      

        JReportsUtil.exportReport(fileName, pdf, dataSource, true);
        
        
      
    }

}
