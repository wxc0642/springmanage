package com.wei.service;

import com.wei.dao.SignInDao;
import com.wei.exception.TimeOutException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FileService {


    @Autowired

    SignInDao signInDao;

    /**
     * 改变格式以及存入数据库
     * @param file
     */
    public void changeFileFormat(MultipartFile file){
        InputStream is=null;
        try {
            is = file.getInputStream();
        }catch (Exception e){
            e.printStackTrace();
        }
        Scanner scan_is=new Scanner(is);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        while(scan_is.hasNextLine()) {
            String signInDataLine = scan_is.nextLine();
            String[] signInDataCols=signInDataLine.split("=");
            int id=0;
            try{
                id=Integer.parseInt(signInDataCols[0]);

            }catch (NumberFormatException e){
                e.printStackTrace();
            }
            String time=signInDataCols[1];


            Date date=null;

            Timestamp timestamp=null;
            try {
                date=sdf.parse(time);

                //long dateTime = sdf.parse(time).getTime();
                //timestamp=new Timestamp(dateTime);
            }catch (Exception e){
                e.printStackTrace();
            }
            try {
                signInDao.setSignInDataAndTag(id, date, checkSignInTime(time));
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    /**
     * 判断是否处于规定的时间段
     * @param time
     * @return
     */
    public String checkSignInTime(String time) throws Exception{
        final String MSI1="07:00:00";
        final String MSI2="09:10:00";
        final String MSO1="11:30:00";
        final String MSO2="12:30:00";
        final String MNI1="14:00:00";
        final String MNI2="14:30:00";
        final String MNO1="17:30:00";
        final String MNO2="18:10:00";
        char[] toArray=time.toCharArray();
        String time_con="";
        for(int i=0;i<11;i++){
            time_con+=toArray[i];
        }


        if(((time_con+MSI1).compareTo(time))<0&&((time_con+MSI2).compareTo(time))>0){
            return "morin";
        }else if(((time_con+MSO1).compareTo(time))<0&&((time_con+MSO2).compareTo(time))>0){
            return "morout";
        }else if(((time_con+MNI1).compareTo(time))<0&&((time_con+MNI2).compareTo(time))>0){
            return "afterin";
        }else if(((time_con+MNO1).compareTo(time))<0&&((time_con+MNO2).compareTo(time))>0){
            return "afterout";
        }else{
            throw new TimeOutException("不在打卡时间段内");
        }
    }


    /**
     * 文件下载前处理（创建Excel表格，将数据记录到表格中）
     */
    public void exportExcel(HttpServletResponse response,
                                   List<Map<String,Object>> signInDataList,
                                   String fileName,
                                   String sheetName,
                                   int columnWith) throws IOException{


        //声明一个工作簿
        HSSFWorkbook hssfWorkbook=new HSSFWorkbook();

        //生成一个表格，设置表格名称
        HSSFSheet hssfSheet=hssfWorkbook.createSheet(sheetName);

        //设置表格列宽度
        hssfSheet.setDefaultColumnWidth(columnWith);

        //写入signInDataList中的数据
        int rowIndex=0;
        for(Map<String,Object> signInData:signInDataList){
            //创建一个row，自增1
            HSSFRow hssfRow=hssfSheet.createRow(rowIndex++);
            //添加本行数据
            String idString=Integer.toString((int)signInData.get("id"));
            hssfRow.createCell(0).setCellValue(idString);


            Date[] dates={(Date) signInData.get("morin"),
                    (Date)signInData.get("morout"),
                    (Date)signInData.get("afterin"),
                    (Date)signInData.get("afterout")};

            for(int i=0;i<dates.length;i++){
                HSSFCell cell=hssfRow.createCell(i+1);
                if(dates[i]==null) continue;
                else{
                    cell.setCellValue(dates[i]);
                }

            }

        }

        response.setHeader("Content-Disposition",
                "attachment;filename="+new String(fileName.getBytes("UTF-8"),
                        "iso-8859-1"));
        response.setContentType("application/vnd.ms-excel");
        response.flushBuffer();
        //hssfWorkbook将Excel写到respose的输出流中，供页面下载文件
        hssfWorkbook.write(response.getOutputStream());

        hssfWorkbook.close();
    }


}
