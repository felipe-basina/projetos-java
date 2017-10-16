package com.concretepage;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

	@RequestMapping(value = "/singleUpload")
	public String singleUpload() {
		return "singleUpload";
	}

	@RequestMapping(value = "/singleSave", method = RequestMethod.POST)
	public @ResponseBody String singleSave(
			@RequestParam("file") MultipartFile file,
			@RequestParam("desc") String desc) {
		System.out.println("File Description:" + desc);
		String fileName = null;
		if (!file.isEmpty()) {
			try {
				fileName = file.getOriginalFilename();
				byte[] bytes = file.getBytes();
				BufferedOutputStream buffStream = new BufferedOutputStream(
						new FileOutputStream(new File("F:/cp/" + fileName)));
				buffStream.write(bytes);
				buffStream.close();
				return "You have successfully uploaded " + fileName;
			} catch (Exception e) {
				return "You failed to upload " + fileName + ": "
						+ e.getMessage();
			}
		} else {
			return "Unable to upload. File is empty.";
		}
	}

	@RequestMapping(value = "/multipleUpload")
	public String multiUpload() {
		return "multipleUpload";
	}

	@RequestMapping(value = "/multipleUpload2")
	public String multiUpload2() {
		return "multipleUpload2";
	}

	@RequestMapping(value = "/multipleSave", method = RequestMethod.POST)
	public @ResponseBody String multipleSave(
			@RequestParam("file") MultipartFile[] files) {
		String fileName = null;
		String msg = "";
		if (files != null && files.length > 0) {
			for (int i = 0; i < files.length; i++) {
				try {
					fileName = files[i].getOriginalFilename();
					byte[] bytes = files[i].getBytes();
					BufferedOutputStream buffStream = new BufferedOutputStream(
							new FileOutputStream(
									new File("c:/temp/" + fileName)));
					buffStream.write(bytes);
					buffStream.close();
					msg += "You have successfully uploaded " + fileName
							+ "<br/>";
				} catch (Exception e) {
					return "You failed to upload " + fileName + ": "
							+ e.getMessage() + "<br/>";
				}
			}
			return msg;
		} else {
			return "Unable to upload. File is empty.";
		}
	}

	@RequestMapping(value = "/multipleSave2", method = RequestMethod.POST, produces = "application/json", consumes = {
			"multipart/mixed", "multipart/form-data", "application/json" })
	public @ResponseBody String multipleSave2(CargaArquivoVersao cargaArquivo) {

		System.out.println(" --- parametro: " + cargaArquivo);

		String fileName = null;
		String msg = "";
		if (cargaArquivo.getArquivos() != null
				&& cargaArquivo.getArquivos().size() > 0) {
			for (int i = 0; i < cargaArquivo.getArquivos().size(); i++) {
				try {
					fileName = cargaArquivo.getArquivos().get(i)
							.getOriginalFilename();
					byte[] bytes = cargaArquivo.getArquivos().get(i).getBytes();
					BufferedOutputStream buffStream = new BufferedOutputStream(
							new FileOutputStream(
									new File("c:/temp/" + fileName)));
					buffStream.write(bytes);
					buffStream.close();
					msg += "You have successfully uploaded " + fileName
							+ "<br/>";
				} catch (Exception e) {
					return "You failed to upload " + fileName + ": "
							+ e.getMessage() + "<br/>";
				}
			}
			return msg;
		} else {
			return "Unable to upload. File is empty.";
		}
	}

}