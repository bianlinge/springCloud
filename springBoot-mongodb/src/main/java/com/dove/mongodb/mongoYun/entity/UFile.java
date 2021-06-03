package com.dove.mongodb.mongoYun.entity;

import com.dove.mongodb.mongoYun.common.constant.ExplorerConstants;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document(collection = "t_ufile")
public class UFile implements Serializable {

	@Id
	private String id;
	//父级ID
	private String pid;
	//文件路径
	private String xpath;
	//路径代码
	private String xcode;
	//文件名
	private String fname;
	//文件唯一标识
	private String objectId;
	//文件存储的真实路径
//	private String originalPath;
	//创建时间
	private long createTime;
	//上传者
	private String owner;
	//文件类型,是否为文件夹
	private String ftype;
	//文件扩展名
	private String ext;
	//图标类型
	private String ico;
	//最后修改时间
	private long lastModified;
	//文件大小
	private long fsize;
	//文件所在的组
	private String fgroup;
	//文件所在的子组
	private String fsgroup;
	//标记是否已删除
	private String deleted;

	public UFile(){}

	public UFile(String path){
		path =	path.replaceAll("/$", "");
		if(path.length() == 0){ path = "/"; }
		int s = path.lastIndexOf("/");
		this.xpath = path.substring(0, s + 1);
		this.fname = path.substring(s + 1);
		if(fname.indexOf(".") == -1){
			this.ftype = ExplorerConstants.FILE_TYPE_DIR;
		}else{
			this.ext = fname.substring(fname.lastIndexOf(".") + 1);
			this.ftype = ExplorerConstants.FILE_TYPE_FILE;
		}
	}

}
