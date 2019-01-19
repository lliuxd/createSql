package com.excle.main;

import java.io.*;
import java.io.OutputStreamWriter;


public class FileManager {
    private String decode ="utf-8";

    private final static int BUFFER_SIZE = 8096;

    public final static boolean DEBUG = true;

    public FileManager(){

    }

    public static void main(String[] args){
        FileManager fm = new FileManager();

        try {
            String sm = fm.getFileStr("D:\\920107.20170928.600124");

            System.out.println(sm.length());
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public String getFileStr(String filePath) throws IOException{
        FileInputStream fis = new FileInputStream(filePath);
        InputStreamReader isr = new InputStreamReader(fis,decode);
        BufferedReader in = new BufferedReader(isr);
        String s = new String();
        StringBuffer sb = new StringBuffer();
        while((s = in.readLine()) != null){
            sb.append(s+"\n");
        }
        in.close();
        isr.close();
        fis.close();
        return sb.toString();
    }

    public boolean createFile (String filePath,String fileText) throws IOException{
        FileOutputStream fos = new FileOutputStream(filePath);
        OutputStreamWriter osr = new OutputStreamWriter(fos,decode);
        BufferedWriter out = new BufferedWriter(osr);
        out.write(fileText);
        out.flush();
        out.close();
        osr.close();
        fos.close();
        return false;

    }

    public boolean createFile(String filePath) throws IOException{
        File file = new File(filePath);
        boolean debug = false;
        if (file.exists()){
            if(file.delete()){
                System.out.println("替换原文件"+file);
            }
        }
        debug = file.createNewFile();
        return  debug;
    }

    public boolean deleteFile(String filePath){
        File file = new File(filePath);
        boolean debug = false;
        debug = file.delete();
        return debug;
    }

    public boolean createDirectory(String filePath){
        File file = new File(filePath);
        boolean debug = false;
        debug = file.mkdirs();
        return debug;

    }

    public boolean deleteDirectory(String filePath){
        return deleteFile(filePath);
    }

    public String[] list(String filePath){
        File file = new File(filePath);
        if(file.isDirectory()){
            return file.list();
        }else {
            return null;
        }

    }


}
