package com.dove.mongodb.mongoYun.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import sun.security.provider.MD5;

import java.io.*;
import java.net.URI;


@Document(collection = "t_object_file")
public class OFile extends File {
    private static final long serialVersionUID = 100L;
    private OFileInfo fileInfo = new OFileInfo();
    private boolean isOfile = false;
    private String name;

    public OFile(File file) {
        this(file.getAbsolutePath());
    }

    public OFile(String pathname) {
        super(pathname);
        isOfile = isOFile();
    }


    /**
     * 获取文件大小
     * @return
     */
    @Override
    public long length() {
        if (fileInfo.length == null) {
            if (fileInfo.realFile != null) {
                return fileInfo.getRealFile().length();
            }
        } else {
            return fileInfo.length;
        }
        return super.length();
    }

    private boolean isOFile() {
        return (this.length() < 1024 && read());
    }


    public boolean read() {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(this));
            fileInfo = (OFileInfo) (in.readObject());
            in.close();
            return true;
        } catch (Exception e) {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                }
            }
        }
        return false;
    }

    public static long getSerialVersionUID() {
        return OFile.serialVersionUID;
    }

    public OFileInfo getFileInfo() {
        return this.fileInfo;
    }

    public void setFileInfo(final OFileInfo fileInfo) {
        this.fileInfo = fileInfo;
    }

    public boolean isOfile() {
        return this.isOfile;
    }

    public void setOfile(final boolean ofile) {
        this.isOfile = ofile;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }


    class OFileInfo implements Externalizable {
        private static final long serialVersionUID = 1L;
        private File realFile = null;
        private String fileCode = null;
        private String canonicalPath = null;
        Long length;

        public OFileInfo() {
        }

        public File getRealFile() {
            return this.realFile;
        }

        public void setRealFile(final File realFile) {
            this.realFile = realFile;
        }

        public String getFileCode() {
            return this.fileCode;
        }

        public void setFileCode(final String fileCode) {
            this.fileCode = fileCode;
        }

        public String getCanonicalPath() {
            return this.canonicalPath;
        }

        public void setCanonicalPath(final String canonicalPath) {
            this.canonicalPath = canonicalPath;
        }

        public Long getLength() {
            return this.length;
        }

        public void setLength(final Long length) {
            this.length = length;
        }

        /**
         * 获取真实文件
         */

        File reaFile() {
            if (realFile == null) {
                if (canonicalPath == null) {
                    return null;
                }
                realFile = new File(canonicalPath);
            }
            return realFile;
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeObject(realFile.getCanonicalFile());
            out.writeObject(realFile.length());
            out.writeObject(fileCode);
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            this.canonicalPath = (String) in.readObject();
            this.length = (Long) in.readObject();
            this.fileCode = (String) in.readObject();
        }
    }
}
