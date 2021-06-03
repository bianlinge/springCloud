package com.dove.web.easypoi;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by E1041 on 2020/2/24.
 */
@RestController
@Api(tags = "easypoi上传")
public class EasypoiController {
    @ApiOperation("excel文件上传并读取文件")
    @RequestMapping(value = "上传Excel", method = RequestMethod.POST)
    public List<String> upload(@RequestParam MultipartFile file) throws Exception {
        if (file == null) {
            throw new Exception("文件不能为空");
        } else if (file.getSize() > 5242880) {
            throw new Exception("上載超過5mb");
        }
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
        if (!".xls".equals(suffixName)) {
            throw new Exception("格式錯誤");
        }

        ImportParams params = new ImportParams();
        params.setTitleRows(0);
        params.setHeadRows(1);
        //读取第一张sheetPlc
        params.setStartSheetIndex(0);
        List<Plc> list = ExcelImportUtil.importExcel(file.getInputStream(), Plc.class, new ImportParams());
        if (CollectionUtils.isEmpty(list)) {
            throw new Exception("格式不符,請下載批量申請模板.");
        } else if (list.size() > 500) {
            throw new Exception("上載的檔案超過單次申請上限(上限為500張保單)，請從新申請。");
		} else if (list.size() == 1) {
			throw new Exception("上載的檔案無任何保單,請核查後上傳。");
		}

        //去除空行
        Iterator<Plc> iterator = list.iterator();
        while (iterator.hasNext()) {
            Plc next = iterator.next();
            if (StringUtils.isEmpty(next.getPlcno())) {
                iterator.remove();
            }
        }

        String pattern = "\\w{0,10}";
        Pattern r = Pattern.compile(pattern);
        List<String> plcNoList = list.stream().map(
                plc -> plc.getPlcno().replaceAll(" ", ""))
                .distinct()
                .filter(plcNo -> r.matcher(plcNo).matches())
                .collect(Collectors.toList());
		if (CollectionUtils.isEmpty(plcNoList)) {
			throw new Exception("上載的檔案無有效保單,請核查後上傳。");
		}
        return plcNoList;
    }
}
