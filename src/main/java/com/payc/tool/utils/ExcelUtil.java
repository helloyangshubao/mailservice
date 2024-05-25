package com.payc.tool.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 基于EasyExcel
 * 具体使用可参见https://github.com/alibaba/easyexcel
 *
 * @author yangshbuao
 * @version 2021/11/7 20:06
 **/
@Slf4j
public final class ExcelUtil {

    private ExcelUtil() {
    }

    /**
     * 解析excel
     */
    public static <T> List<T> read(MultipartFile multipartFile, Class<T> clazz) throws IOException {
        try (InputStream inputStream = multipartFile.getInputStream()) {
            return EasyExcel.read(inputStream).head(clazz).sheet().doReadSync();
        }
    }

    public static <T> List<T> read(MultipartFile multipartFile, Class<T> clazz, int sheet, Integer number) throws IOException {
        try (InputStream inputStream = multipartFile.getInputStream()) {
            return EasyExcel.read(inputStream).head(clazz).sheet().headRowNumber(number).doReadSync();
        }
    }

    public static <T> List<T> read(MultipartFile multipartFile, Class<T> clazz, ReadListener<T> readListener)
            throws IOException {
        try (InputStream inputStream = multipartFile.getInputStream()) {
            return EasyExcel.read(inputStream).head(clazz).sheet()
                    .registerReadListener(readListener).doReadSync();
        }
    }

    /**
     * 同步解析excel
     */
    public static <T> List<T> read(MultipartFile multipartFile, Class<T> clazz, int sheet) throws IOException {
        try (InputStream inputStream = multipartFile.getInputStream()) {
            return EasyExcel.read(inputStream).head(clazz).sheet(sheet).doReadSync();
        }
    }

    /**
     * 导出一个sheet
     */
    public static <T> void downloadExcel(List<T> data, String fileName, String sheetName) {
        try {
            T type = data.get(0);
            EasyExcel.write(setupOutputStream(fileName), type.getClass())
                    .registerWriteHandler(style())
                    .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                    .autoCloseStream(true)
                    .sheet(sheetName).doWrite(data);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    private static OutputStream setupOutputStream(String fileName) throws IOException {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
        if (servletRequestAttributes == null) {
            return null;
        }
        HttpServletResponse response = servletRequestAttributes.getResponse();
        if (response == null) {
            return null;
        }
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        String encodedFileName = fileName;
        try {
            encodedFileName = URLEncoder.encode(encodedFileName, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        }
        response.setHeader("Content-disposition", "attachment;filename=" + encodedFileName + ".xlsx");
        return response.getOutputStream();
    }

    /**
     * 导出多个sheet
     */
    public static void downloadExcels(String fileName,
                                      String[] sheetNameArr,
                                      List<List<?>> dataList,
                                      List<Class<?>> clazzList) {
        ExcelWriter excelWriter = null;
        try {
            OutputStream outputStream = setupOutputStream(fileName);
            excelWriter = EasyExcel.write(outputStream).build();
            int count = 0;
            for (String sheetName : sheetNameArr) {
                //这里 需要指定写用哪个class去写
                WriteSheet writeSheet = EasyExcel.writerSheet(count, sheetName)
                        .head(clazzList.get(count)).build();
                excelWriter.write(dataList.get(count), writeSheet);

                count++;
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            if (excelWriter != null) {
                //千万别忘记finish 会帮忙关闭流
                excelWriter.finish();
            }
        }
    }

    public static <T> void write(List<?> list, Class<T> clazz, ByteArrayOutputStream byteArrayOutputStream) {
        EasyExcel.write(byteArrayOutputStream, clazz)
                .registerWriteHandler(ExcelUtil.style())
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                .sheet("sheet").doWrite(list);
    }

    public static <T> void write(List<?> list, Class<T> clazz, String fileName) {
        EasyExcel.write(fileName, clazz)
                .registerWriteHandler(ExcelUtil.style())
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                .sheet("sheet").doWrite(list);
    }

    /**
     * excel样式
     *
     * @author gary.wang on 2019/12/11 21:49
     */
    private static HorizontalCellStyleStrategy style() {
        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        headWriteCellStyle.setShrinkToFit(true);
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short) 10);
        headWriteCellStyle.setWriteFont(headWriteFont);
        headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.index);
        // 内容的策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        contentWriteCellStyle.setWriteFont(headWriteFont);
        return new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
    }
}
