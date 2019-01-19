package com.excle.main;

import java.io.*;

public class Mult {

    private static final String DEFAULT_LINE_SEPARATOR = System.getProperty("line.separator");



    public static void main(String[] args) {
        StringBuilder content = new StringBuilder();
        int tgi = 1;
        for(int i=0;i<100;i++){
            content.append(DEFAULT_LINE_SEPARATOR);
            if(i==0 || i==10 || i==20 || i==30 || i==40 || i==50 || i==60 || i==70 || i==80 || i==90){
                tgi = (i/10);
                content.append("use cb_dep_"+tgi+";");
                content.append(DEFAULT_LINE_SEPARATOR);
            }

            content.append(Mult.readSqlScript("D:/jiaoben/begin/ddl_cb_dep.sql",i));
        }

        FileManager fm = new FileManager();
        try {
            fm.createFile("D:/jiaoben/end/ddl_cb_dep.sql",content.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取SQL脚本，如果20行都是空行，则认为文件已经读完了
     * @param filePath
     * @param nums
     * @return
     */
    public static String readSqlScript(String filePath,int nums){
        StringBuilder sb = new StringBuilder();
        FileInputStream fis = null;
        BufferedReader reader = null;
        try {
            fis = new FileInputStream(new File(filePath));
            reader = new BufferedReader(new InputStreamReader(fis));
            int index = 0;
            while (true){
                if(index == 20){
                    break;
                }
                String line = reader.readLine();
                if(line != null && !line.equals("")){
                    index = 0;
                    if(line.startsWith("--")){
                        continue;
                    }

                    if (nums<10){
                        line = line.replaceAll("_##","_0"+nums);
                    }else {
                        line = line.replaceAll("_##","_"+nums);
                    }
                    sb.append(line);
                    sb.append(DEFAULT_LINE_SEPARATOR);
                }else {
                    index++;
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }
        return  sb.toString();
    }


    public static String getSql(String sqlContent){
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<100;i++){
            if(i<10){
                sb.append(sqlContent.replaceAll("_##","_0"+i));

            }else {
                sb.append(sqlContent.replaceAll("_##","_"+i));
            }
            sb.append(DEFAULT_LINE_SEPARATOR);
        }
        return sb.toString();
    }
}
