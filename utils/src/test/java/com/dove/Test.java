package com.dove;

import org.apache.commons.io.FilenameUtils;

import java.io.File;

public class Test {
    public static void main(String[] args) {
        String extension = FilenameUtils.getExtension("1.png.13.pdf");
        String name = FilenameUtils.getName("E:\\file\\mng.exe");
        String prefix = FilenameUtils.getPrefix("E://test.txt");
        System.out.println(extension);
        System.out.println(name);
        System.out.println(prefix);
    }
}
