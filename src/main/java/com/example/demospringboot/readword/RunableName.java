package com.example.demospringboot.readword;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tianyalei
 */
public class RunableName {
    static int sum = 0;
    /**
     * 创建一个静态钥匙
     * 值是任意的
     */
    static Object ob = "aa";

    public static final class AccumRunnable implements Runnable {

        private static String filePath ;
        private static String findName;

        private int result;

        final Logger logger = LoggerFactory.getLogger( getClass() );
        public AccumRunnable(String filePath, String findName) {
            AccumRunnable.filePath = filePath;
            AccumRunnable.findName = findName;
        }

        String txt = "";
        String text = "";
        String fileName = "";


        List<String> list = new ArrayList<>();
        File file = new File( filePath);
        File[] arrFiles = file.listFiles();
        int txtLength = 0;
        StringBuilder stringBuilder = null;
        @Override
        public void run() {
            result = 0;
            synchronized (ob) {

                try {
                    for (File getFile : arrFiles) {
                        fileName = getFile.getName();
                        //filePath = stringBuilder.append(filePath + "/" + fileName).toString();
                        FileInputStream in;
                        in = new FileInputStream( getFile );
                        InputStream is = FileMagic.prepareToCheckMagic( in );
                        if (FileMagic.valueOf( is ) == FileMagic.OLE2) {
                            WordExtractor ex = new WordExtractor( is );
                            text = ex.getText();
                            ex.close();
                        } else if (FileMagic.valueOf( is ) == FileMagic.OOXML) {

                            // jdk 8 以上可以使用此方法避免读取大word文档报错问题，ZipSecureFile.setMinInflateRatio;
                            ZipSecureFile.setMinInflateRatio( 0 );
                            XWPFDocument doc = new XWPFDocument( is );
                            XWPFWordExtractor extractor = new XWPFWordExtractor( doc );
                            text = extractor.getText();
                            extractor.close();
                        } else if (FileMagic.valueOf( is ) == FileMagic.PDF) {
                            PDDocument pdf = null;
                            pdf = PDDocument.load( is );
                            PDFTextStripper stripper = new PDFTextStripper();
                            text = stripper.getText( pdf );
                            pdf.close();
                        }
                        txtLength += txt.length();
                        //判断出现过搜索的字符串
                        int num = txt.indexOf( findName );
                        if (num > 0) {
                            list.add( fileName );
                        }
                    }
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace(System.err);
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.error( "for file " + filePath, e );
                }
                System.out.printf("(%s) - 运行结束，结果为 %d\n",
                        Thread.currentThread().getName(), result);
                System.out.println(list);
                System.out.println(list.size());
            }
        }

        public int getResult() {
            return result;
        }
    }
}
