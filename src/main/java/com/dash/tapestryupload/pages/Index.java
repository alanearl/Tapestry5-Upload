package com.dash.tapestryupload.pages;

import java.io.File;
import java.util.Date;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.upload.services.UploadedFile;

/**
 * Start page of application TapestryUpload.
 */
public class Index
{
	public Date getCurrentTime() 
	{ 
		return new Date(); 
	}
        
        @Property
        private UploadedFile file;

        public void onSuccessFromSearchFiles()
        {
            File copied = new File("C:/temp/" + file.getFileName());
            
            file.write(copied);
        }
        
        @Persist(PersistenceConstants.FLASH)
        @Property
        private String message;


        public Object onUploadException(FileUploadException ex)
        {
            message = "Upload exception: " + ex.getMessage() + " You can only upload file less than 20kb.";

            return this;
        }
        
        public String getErrorMessage(){
            
            return message;
        }
        
        
        @Property
        private String filename;
        
        public void onDeleteFile(String name) {
	
            String path = "C:/temp/";
            boolean success = (new File(path + name)).delete();
            
            File dir = new File("C:/temp/");
            
            String[] children = dir.list();
            String a;
            
            if (children != null) {
                
                for (int i=0; i<children.length; i++) {
                    // Get filename of file or directory
                    a = children[i];
                    success = (new File("C:/temp/" + a)).delete();
                    
                    if (!success) {
                        // Deletion failed
                    }
                }
            }  
	}
        
        private String temp;
        
        public String getFileList(){
            
            File dir = new File("C:/temp/");

            String[] children = dir.list();
            String a;
            
            if (children != null) {
                
                for (int i=0; i<children.length; i++) {
                    // Get filename of file or directory
                    a = children[i];
                    
                    if(a != null)
                        temp = temp + a;
                }
            }
            
            return temp;
        }
}
