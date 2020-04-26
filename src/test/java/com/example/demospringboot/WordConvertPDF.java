package com.example.demospringboot;



import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.*;

public class WordConvertPDF {

    public static void main(String[] args) {
        WordConvertPDF cwoWord = new WordConvertPDF();
        System.out.println("Start");
        cwoWord.ConvertToPDF("/Users/tianyalei/Downloads/word/信用风险预警系统-项目总结报告(1)(1).docx", "/Users/tianyalei/Downloads/word/信用风险预警系统-项目总结报告(1)(1).pdf");
    }

    public void ConvertToPDF(String docPath, String pdfPath) {
        try {
            InputStream doc = new FileInputStream(new File(docPath));
            XWPFDocument document = new XWPFDocument(doc);
            PdfOptions options = PdfOptions.create();
            OutputStream out = new FileOutputStream(new File(pdfPath));
            PdfConverter.getInstance().convert(document, out, options);
            System.out.println("Done");
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }

}