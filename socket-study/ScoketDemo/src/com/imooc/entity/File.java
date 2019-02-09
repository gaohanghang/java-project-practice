package src.com.imooc.entity;

import java.io.Serializable;

/**
 * @Description: 文件实体类
 * @author: Gao Hang Hang
 * @date 2019/02/09 13:40
 */
public class File implements Serializable {
    private int fid;
    private String fname;
    private byte[] fcontent;

    public File() {
    }

    public File(String fname, byte[] fcontent) {
        super();
        this.fname = fname;
        this.fcontent = fcontent;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public byte[] getFcontent() {
        return fcontent;
    }

    public void setFcontent(byte[] fcontent) {
        this.fcontent = fcontent;
    }
}
