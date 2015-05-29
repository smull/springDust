package com.levelup.spring.service;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.levelup.stock.model.DropBoxFile;

import java.io.IOException;
import java.util.List;

/**
* Created by SMULL on 5/10/2015.
*/
public interface DropBoxService {

    public DbxRequestConfig getDbxRequestConfig();
    public void setDbxRequestConfig(DbxRequestConfig dbxRequestConfig);
    public DbxClient getAuth();
    public DbxClient getDbxClient(String authAccessToken) throws DbxException;
    public String getAccessToken(String dropBoxAuthCode) throws DbxException;

    public String getAuthorizeUrl(String dropBoxAppKey, String dropBoxAppSecret) ;
    public long getDropboxSize() throws DbxException ;
    public void uploadToDropbox(String fileName) throws DbxException,
            IOException;
    public void createFolder(String folderName) throws DbxException ;
    public List<DropBoxFile> listDropboxFolders(String folderPath) throws DbxException ;
    public void downloadFromDropbox(String fileName) throws DbxException,
            IOException ;

    public String getAppKey();

    public String getAppSecret();

}
