package com.levelup.spring.service.impl;

import com.dropbox.core.*;
import com.levelup.spring.service.DropBoxService;
import com.levelup.stock.model.DropBoxFile;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**
* Created by SMULL on 5/10/2015.
*/
@Service("dropBoxService")
public class DropBoxServiceImpl implements DropBoxService {

    public static final String APP_KEY = "62j03uwvzcaeuki";
    public static final String APP_SECRET = "9kve9350fznnpl1";
    private DbxClient dbxClient;

    private DbxWebAuthNoRedirect dbxWebAuthNoRedirect;
    private DbxRequestConfig dbxRequestConfig;

    public static final String accessToken = "PldL4uPQH8AAAAAAAAAAOE6l9kY80tvZPPXb1Am7WdIOEBLfzZVRpyAdioy7w6m9";


    public String getAppKey() {
        return APP_KEY;
    }

    public String getAppSecret() {
        return APP_SECRET;
    }

    public DbxRequestConfig getDbxRequestConfig() {
        dbxRequestConfig = new DbxRequestConfig(
                "JavaDropboxTutorial/1.0", Locale.getDefault().toString());
        return dbxRequestConfig;
    }

    public void setDbxRequestConfig(DbxRequestConfig dbxRequestConfig) {
        this.dbxRequestConfig = dbxRequestConfig;
    }



    public DbxClient getAuth() {
//        if(accessToken == null || accessToken.equals("")){
//            return null;
//        }
        dbxClient = new DbxClient(getDbxRequestConfig(), accessToken);
        return dbxClient;
    }


    public DbxClient getDbxClient(String authAccessToken) throws DbxException {
        dbxClient = new DbxClient(dbxRequestConfig, authAccessToken);
//        System.out.println("Dropbox Account Name: "
//                + dbxClient.getAccountInfo().displayName);
        return dbxClient;
    }


    public String getAccessToken(String dropBoxAuthCode) throws DbxException {
        DbxAuthFinish authFinish = dbxWebAuthNoRedirect.finish(dropBoxAuthCode);
        String authAccessToken = authFinish.accessToken;
        return authAccessToken;
    }

    public String getAuthorizeUrl(String dropBoxAppKey, String dropBoxAppSecret) {
        DbxAppInfo dbxAppInfo = new DbxAppInfo(APP_KEY, APP_SECRET);
        dbxRequestConfig = getDbxRequestConfig();
        dbxWebAuthNoRedirect = new DbxWebAuthNoRedirect(
                dbxRequestConfig, dbxAppInfo);
        String authorizeUrl = dbxWebAuthNoRedirect.start();
//        System.out.println("1. Authorize: Go to URL and click Allow : "
//                + authorizeUrl);
        return authorizeUrl;
    }



    public long getDropboxSize() throws DbxException {
        long dropboxSize = 0;
        DbxAccountInfo dbxAccountInfo = dbxClient.getAccountInfo();
        // in GB :)
        dropboxSize = dbxAccountInfo.quota.total / 1024 / 1024 / 1024;
        return dropboxSize;
    }

    public void uploadToDropbox(String fileName) throws DbxException,
            IOException {
        File inputFile = new File(fileName);
        FileInputStream fis = new FileInputStream(inputFile);
        try {
            DbxEntry.File uploadedFile = dbxClient.uploadFile("/" + fileName,
                    DbxWriteMode.add(), inputFile.length(), fis);
            String sharedUrl = dbxClient.createShareableUrl("/" + fileName);
            System.out.println("Uploaded: " + uploadedFile.toString() + " URL "
                    + sharedUrl);
        } finally {
            fis.close();
        }
    }

    public void createFolder(String folderName) throws DbxException {
        dbxClient.createFolder("/" + folderName);
    }

    public List<DropBoxFile> listDropboxFolders(String folderPath) throws DbxException {
        DropBoxFile dropBoxFile = new DropBoxFile();
        List<DropBoxFile> filesDropBox = new LinkedList<>();
        DbxEntry.WithChildren listing = dbxClient
                .getMetadataWithChildren(folderPath);
        //System.out.println("Files List:");
        for (DbxEntry child : listing.children) {
//            System.out.println("	" + child.name + ": " + child.toString());
            dropBoxFile = new DropBoxFile();
            if(child.isFolder()){
                dropBoxFile.setFolder(child.isFolder());
                dropBoxFile.setName(child.name);
                filesDropBox.add(dropBoxFile);
            } else {
                dropBoxFile.setName(child.name);
                dropBoxFile.setClientMtime(getClientMtime(child.toString()));
                dropBoxFile.setLastModified(getLastModified(child.toString()));
                dropBoxFile.setNumBytes(getNumBytes(child.toString()));
                dropBoxFile.setFolder(child.isFolder());
                filesDropBox.add(dropBoxFile);
            }
        }
        return filesDropBox;
    }

    public void downloadFromDropbox(String fileName) throws DbxException,
            IOException {
        File folder = new File("C:\\temp");
        if (!folder.exists())
            folder.mkdir();
        FileOutputStream outputStream = new FileOutputStream("C:\\temp\\" + fileName);
        //FileOutputStream outputStream = new FileOutputStream(fileName);
        try {
            DbxEntry.File downloadedFile = dbxClient.getFile("/" + fileName,
                    null, outputStream);

            System.out.println("Metadata: " + downloadedFile.toString());
        } finally {
            outputStream.close();
        }
    }


    public String getClientMtime(String source){
        if(source == null || source.equals("")){
            return null;
        }
        String clientMtime = new String();
        String[] list = source.toString().split(",");
        for (String s : list) {
            if(s.contains("clientMtime")){
//                System.out.println(s);
                int indexLetter = s.indexOf("=");
                indexLetter += 2;
//                System.out.println(s.substring(++indexLetter));
                clientMtime = s.substring(++indexLetter,s.length()-1);
            }
        }
        return clientMtime;
    }

    public String getLastModified(String source){
        if(source == null || source.equals("")){
            return null;
        }
        String lastModified = new String();
        String[] list = source.toString().split(",");
        for (String s : list) {
            if(s.contains("lastModified")){
//                System.out.println(s);
                int indexLetter = s.indexOf("=");
                indexLetter += 2;
//                System.out.println(s.substring(++indexLetter));
                lastModified = s.substring(indexLetter,s.length()-1);
            }
        }
        return lastModified;
    }

    public String getNumBytes(String source){
        if(source == null || source.equals("")){
            return null;
        }
        String numBytes = new String();
        String[] list = source.toString().split(",");
        for (String s : list) {
            if(s.contains("numBytes")){
//                System.out.println(s);
                int indexLetter = s.indexOf("=");
//                System.out.println(s.substring(++indexLetter));
                numBytes = s.substring(++indexLetter);
            }
        }
        return numBytes;
    }
}
