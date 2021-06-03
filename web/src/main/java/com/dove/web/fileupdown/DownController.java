package com.dove.web.fileupdown;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @author E1041
 */
@RestController
@Api(tags = "文件下载/预览")
public class DownController {
    private final Logger logger = LoggerFactory.getLogger(UploadController.class);
    @Value("${destdir}")
    private String destdir;

    @ApiOperation(value = "下载或者预览文件")
    @RequestMapping(value = "/getFile", method = {RequestMethod.GET, RequestMethod.POST})
    public void getFile(HttpServletRequest request, HttpServletResponse response,
                        @RequestParam @ApiParam(required = true, value = "fileid") String fileid,
                        @RequestParam @ApiParam(required = true, value = "文件名称") String fileName,
                        @RequestParam @ApiParam(required = true, value = "true-下载 false-预览") Boolean isDown) throws Exception {
        if (StringUtils.isBlank(fileid)) {
            throw new Exception("請選擇下載的fileid");
        }
        String sufferName = fileid.substring(fileid.indexOf("."));
        // 请求客户端操作系统的信息
        final String userAgent = request.getHeader("USER-AGENT").toLowerCase();
        if (userAgent.contains("firefox")) {
            //是火狐浏览器，使用BASE64编码
            fileName = "=?utf-8?b?" + new BASE64Encoder().encode(fileName.getBytes("utf-8")) + "?=";
        }//判断是否Edge浏览器
        else if (userAgent.contains("windows") && userAgent.contains("edge")) {
            isDown = false;

        } //iphone直接浏览器显示
        else if (userAgent.contains("iphone") || userAgent.contains("ipad") || userAgent.contains("mac")) {
            isDown = false;
        } else {
            //给文件名进行URL编码
            //URLEncoder.encode()需要两个参数，第一个参数时要编码的字符串，第二个是编码所采用的字符集
            fileName = URLEncoder.encode(fileName, "utf-8");
            if (fileName.contains("+")) {
                fileName = StringUtils.replace(fileName, "+", "%20");
            }
        }
        if (isDown) {
            // 设置强制下载不打开
            response.setContentType("application/force-download");
            // 设置文件名
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
        } else {
            if (sufferName.endsWith(".pdf")) {
                response.setContentType("application/pdf;charset=utf-8");
            }
            if (sufferName.endsWith(".jpg") || sufferName.endsWith(".jpeg")) {
                response.setContentType("image/jpeg;charset=utf-8");
            }
            if (sufferName.endsWith(".tiff")) {
                response.setContentType("image/tiff;charset=utf-8");
            }
            if (sufferName.endsWith(".png")) {
                response.setContentType("image/jpeg;charset=utf-8");
            }
            response.addHeader("Content-Disposition", "inline;filename=" + fileName);
        }

        byte[] resultByte = getBytes(fileid);

        OutputStream outputStream = response.getOutputStream();
        ByteArrayInputStream bais = new ByteArrayInputStream(resultByte);
        byte[] bufs = new byte[1024 * 10];
        int read = 0;
        while ((read = bais.read(bufs)) != -1) {
            outputStream.write(bufs, 0, read);
        }

        outputStream.close();
        logger.info("下载成功");
    }

    private byte[] getBytes(String fileId) {
        try {
            InputStream is = new FileInputStream(new File(destdir + fileId));
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024 * 4];
            int n = 0;
            while (-1 != (n = is.read(buffer))) {
                output.write(buffer, 0, n);
            }
            return output.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new byte[0];
    }
}
