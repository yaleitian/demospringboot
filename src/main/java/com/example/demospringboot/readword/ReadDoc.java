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

import java.io.IOException;
import java.io.InputStream;

/**
 * @author tianyalei
 */
public class ReadDoc {
    final Logger logger = LoggerFactory.getLogger( getClass() );

    /**
     * 理论上来说，应该对于读取大多数 doc 或者 docx 文件都是有效的。但是我发现了一个奇怪的问题，就是我的代码在读取某些 doc 文件的时候，经常会给出这样的一个异常：
     * org.apache.poi.poifs.filesystem.OfficeXmlFileException: The supplied data appears to be in the Office 2007+ XML.
     * You are calling the part of POI that deals with OLE2 Office Documents.
     * 这个异常的意思是什么呢，通俗的来讲，就是你打开的文件并不是一个 doc 文件，你应该使用读取 docx 的方法去读取。但是我们明明打开的就是一个后缀是 doc 的文件，
     * 其实 doc 和 docx 的本质不同的，doc 是 OLE2 类型，而 docx 而是 OOXML 类型。如果你用压缩文件打开一个 docx 文件，你会发现一些文件夹：
     * 本质上 docx 文件就是一个 zip 文件，里面包含了一些 xml 文件。所以，一些 docx 文件虽然大小不大，但是其内部的 xml 文件确实比较大的，这也是为什么在读取
     * 某些看起来不是很大的 docx 文件的时候却耗费了大量的内存
     * 其主要就是根据 InputStream 前 8 个字节来判断文件的类型
     *
     * @param filePath
     * @param is
     * @return
     * @throws IOException
     */
    public String readDoc(String filePath, InputStream is) throws IOException {
        String text = "";
        is = FileMagic.prepareToCheckMagic( is );
        try {
            if (FileMagic.valueOf( is ) == FileMagic.OLE2) {
                WordExtractor ex = new WordExtractor( is );
                text = ex.getText();
                ex.close();
            } else if (FileMagic.valueOf( is ) == FileMagic.OOXML) {
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
            }
        } catch (Exception e) {
            logger.error( "for file " + filePath, e );
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return text;
    }
}
