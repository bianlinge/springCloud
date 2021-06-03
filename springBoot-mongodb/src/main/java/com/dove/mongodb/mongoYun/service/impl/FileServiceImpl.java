package com.dove.mongodb.mongoYun.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.dove.mongodb.mongoYun.common.ResultMsg;
import com.dove.mongodb.mongoYun.common.config.ExplorerConfig;
import com.dove.mongodb.mongoYun.common.constant.SystemConstant;
import com.dove.mongodb.mongoYun.entity.OFile;
import com.dove.mongodb.mongoYun.entity.ShortUrl;
import com.dove.mongodb.mongoYun.entity.UFile;
import com.dove.mongodb.mongoYun.service.IUFileService;
import com.dove.mongodb.mongoYun.repository.UFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

@Service
public class FileServiceImpl implements IUFileService {
    @Autowired
    private UFileRepository uFileRepository;

    @Autowired
    private ExplorerConfig explorerConfig;

    @Override
    public ResultMsg<?> getTempPath() {
        return null;
    }

    @Override
    public ResultMsg<?> list(String group, String uname, String path) {
        return null;
    }

    @Override
    public ResultMsg<?> createFolder(String group, String uname, String path) {
        return null;
    }

    /**
     * 文件下载
     *
     * @param id
     * @return
     */
    @Override
    public ResultMsg<?> download(String uname, String id) {

        Optional<UFile> uFile = uFileRepository.findById(id);
        String realPath = explorerConfig.getUsersRootDir() + uname.replaceAll("@|\\.", "/") + "/" +
                uFile.get().getObjectId().replaceAll("(.{3})", "$1/") + "/" + uFile.get().getFname();

        return new ResultMsg<Object>(SystemConstant.RESULT_STATUS_SUCCESS,"",new File(realPath));
    }

    /**
     *上传
     * @param group
     * @param uname
     * @param path
     * @param files
     * @return
     */
    @Override
    public ResultMsg<?> upload(String group, String uname, String path, Map<String, File> files) {
        JSONArray result = new JSONArray();
        List<ShortUrl> urls = new ArrayList<ShortUrl>();
        path = path.replaceAll("//","/");
        Set<String> keySet = files.keySet();
        for (String fileName : keySet) {
            File file = files.get(fileName);
            UFile uFile = new UFile((path+"/"+fileName).replaceAll("/+","/"));
            uFile.setCreateTime(System.currentTimeMillis());
            uFile.setFname(fileName);
            uFile.setLastModified(System.currentTimeMillis());
            uFile.setOwner(uname);
            uFile.setFgroup(group);
            uFile.setFsize(file.length());
            //保存到文件仓库，保存到MongoDB
            OFile oFile = new OFile(file);
            oFile.setName(fileName);
            uFileRepository.save(uFile);
        }
        return null;
    }

    public static void main(String[] args) {
        String filname = "a//////////f";
        String s = filname.replaceAll("/+", "/");

        System.out.println(s);
    }

}
