package org.pepsik.rest.utility.fileUpload;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by pepsik on 5/3/15.
 */

//Not done yet
public class FileUploads {

    private List<MultipartFile> files;

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }
}
