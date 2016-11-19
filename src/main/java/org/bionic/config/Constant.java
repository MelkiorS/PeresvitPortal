package org.bionic.config;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class Constant {
	
	 // main path to download folder
	 public static final String UPLOAD_PATH = "/home/serge/mma/downloads";
	 // folder user${userId} files
	 public static final String USR_FOLDER  = "user";
	// folder user without id files
	 public static final String USER_UNKNOWN = "userunknown";
	 // pattern filename for avatar
	 public static final String AVATAR  = "avatar";
	 // studying folder
	 public static final String STUDYING  = "studying";
	 // pattern filename resource${resourceId}
	 public static final String IMAGE  = "img";
	 public static final String VIDEO  = "video";
	 public static final String TEXT  = "text";

	public static void deleteFile(String pathFile) {
		if (pathFile != null && !pathFile.isEmpty()) {
			try {
				File file = new File(pathFile);
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
	
	public static String uploadingFile(MultipartFile inputFile, String pathFile){
		
		try {
			if (!inputFile.isEmpty()) {
				try {
					String originalFilename = inputFile.getOriginalFilename();

					File destinationFile = new File(pathFile, originalFilename);
					destinationFile.getParentFile().mkdirs();
					inputFile.transferTo(destinationFile);
	
					// saving URL
					pathFile = destinationFile.getPath();
					
					return pathFile;
					
				} catch (Exception e) {
					return null;
				}
			}
		} catch (Exception e) {
			return null;
		}

		return null;		
	}
}