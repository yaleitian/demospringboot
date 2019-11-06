package com.example.demospringboot.readword;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tianyalei
 */
public class WordData {
    public List<String> getFindName(String filePath, String findName) {
        String text = "";
        String fileName = "";
        List<String> stringList = new ArrayList<>();
        // 拼接为含名字的路径
        String realPath = filePath;
        File file = new File( realPath );
        File[] arrFiles = file.listFiles();
        for (File getFile : arrFiles) {
            fileName = getFile.getName();
            try {
                // doc为后缀的
                String doc = "doc";
                FileInputStream in;
                in = new FileInputStream( getFile );
                if (fileName.endsWith( doc )) {
                    HWPFDocument hwpfDocument = new HWPFDocument( in );
                    text = hwpfDocument.getText().toString();
                    in.close();
                }
                // docx为后缀的
                String docx = "docx";
                if (fileName.endsWith( docx )) {
                 /*
                直接打开会出现
                 Zip bomb detected! The file would exceed the max. ratio of compressed file size to the size
                  of the expanded data.
                 添加ZipSecureFile.setMinInflateRatio(0);即可
                 */
                    ZipSecureFile.setMinInflateRatio( 0 );
                    XWPFDocument xwpfDocument = new XWPFDocument( in );
                    XWPFWordExtractor extractor = new XWPFWordExtractor( xwpfDocument );
                    text = extractor.getText();


                }
                //判断出现过搜索的字符串
                int num = text.indexOf( findName );
                if (num > 0) {
                    stringList.add( fileName );
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return stringList;
    }

}
