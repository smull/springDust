package com.levelup.stock.model;

import java.io.Serializable;

/**
 * Created by SMULL on 5/10/2015.
 */
public class DropBoxFile implements Serializable {

    private String name;
    private String clientMtime;
    private String lastModified;
    private String numBytes;
    private boolean isFolder;


    public DropBoxFile() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClientMtime() {
        return clientMtime;
    }

    public void setClientMtime(String clientMtime) {
        this.clientMtime = clientMtime;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public String getNumBytes() {
        return numBytes;
    }

    public void setNumBytes(String numBytes) {
        this.numBytes = numBytes;
    }

    public boolean isFolder() {
        return isFolder;
    }

    public void setFolder(boolean isFolder) {
        this.isFolder = isFolder;
    }

}
