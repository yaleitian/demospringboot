package com.example.demospringboot;

import com.lowagie.text.pdf.BaseFont;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;


import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class HtmlTopdf {
    public boolean convertHtmlToPdf(String inputFile, String outputFile)
            throws Exception {

        OutputStream os = new FileOutputStream( outputFile );
        ITextRenderer renderer = new ITextRenderer();
        String url = new File( inputFile ).toURI().toURL().toString();
        renderer.setDocument( url );
        // 解决中文支持问题
        ITextFontResolver fontResolver = renderer.getFontResolver();
        fontResolver.addFont( "/Users/tianyalei/Downloads/simsun-extb/simsunb.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED );
        //解决图片的相对路径问题
        renderer.getSharedContext().setBaseURL( "file:/D:/test" );
        renderer.layout();
        renderer.createPDF( os );
        os.flush();
        os.close();
        return true;
    }


    public static void main(String[] args) {
        HtmlTopdf htmlTopdf = new HtmlTopdf();
        try {
            htmlTopdf.convertHtmlToPdf( "/Users/tianyalei/Downloads/word/信用风险预警系统-项目总结报告.html", "/Users/tianyalei/Downloads/word/信用风险预警系统-项目总结报告.pdf" );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


