package org.bionic.config;

import java.io.File;

public class Constant {

	 // main path to download folder
	 public static final String UPLOAD_PATH = "/home/mma/downloads/";
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
}
