package com.levelup.stock.view;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import com.levelup.spring.service.DropBoxService;
import com.levelup.spring.service.impl.DropBoxServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
* Handles requests for the application file upload requests
*/
@Controller
public class FileUploadController {

	private static final Logger logger = LoggerFactory
			.getLogger(FileUploadController.class);

    private static DropBoxService dropBoxService = new DropBoxServiceImpl();
	/**
	 * Upload single file using Spring Controller
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public /*@ResponseBody*/
	String uploadFileHandler(/*@RequestParam("name") String name,*/
			@RequestParam("file") MultipartFile file,
            Model model) {
        String message = new String();
        String name = file.getOriginalFilename();
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();
                System.out.println("name: " + name);

                // Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + name);
                serverFile.getAbsolutePath();
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				logger.info("Server File Location="
						+ serverFile.getAbsolutePath());

                message = "You successfully uploaded file = " + name;
//				return "You successfully uploaded file=" + name;
			} catch (Exception e) {
                message = "You failed to upload " + name + " => " + e.getMessage();
//				return "You failed to upload " + name + " => " + e.getMessage();
			}
		} else {
            message = "You failed to upload " + name + " because the file was empty.";
//			return "You failed to upload " + name
//					+ " because the file was empty.";
		}
        model.addAttribute("message",message);
        return "upload.page";
	}

	/**
	 * Upload multiple file using Spring Controller
	 */
	@RequestMapping(value = "/uploadMultipleFile", method = RequestMethod.POST)
	public @ResponseBody
	String uploadMultipleFileHandler(@RequestParam("name") String[] names,
			@RequestParam("file") MultipartFile[] files) {

		if (files.length != names.length)
			return "Mandatory information missing";

		String message = "";
		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			String name = names[i];
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				logger.info("Server File Location="
						+ serverFile.getAbsolutePath());

				message = message + "You successfully uploaded file=" + name
						+ "<br />";
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		}
		return message;
	}
}
