package com.mkyong.common.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

@org.springframework.stereotype.Controller
public class FileUploadController {

	private boolean copiarArquivo(File origem, File destino) {
		FileInputStream fis = null;
		FileOutputStream fos = null;

		try {

			fis = new FileInputStream(origem);
			fos = new FileOutputStream(destino);

			byte[] buf = new byte[1024];
			int i = 0;
			while ((i = fis.read(buf)) != -1) {
				fos.write(buf, 0, i);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
				if (fos != null) {
					fos.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		return true;
	}

	@RequestMapping(value="/carrega", method = RequestMethod.POST)
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("---> Entrou no onSubmit");

		final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

		final Map multipartFiles = multiRequest.getFileMap();

		StringBuilder sb = new StringBuilder();

		String fileName = "";

		if (multipartFiles != null && multipartFiles.size() > 0) {

			for (Object obj : multipartFiles.values()) {

				MultipartFile multipartFile = (MultipartFile) obj;

				fileName = multipartFile.getOriginalFilename();
				System.out.println("Arquivo carregado: " + fileName);
				sb.append("\t - ").append(fileName);

				try {
					
					File convFile = new File(
							multipartFile.getOriginalFilename());
					multipartFile.transferTo(convFile);

					File novoArquivo = new File("c:/temp/" + convFile.getName());

					this.copiarArquivo(convFile, novoArquivo);

					System.out.println("Arquivo [ " + fileName
							+ " ] transferido com sucesso...");
				
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		}

		return new ModelAndView("FileUploadSuccess", "fileName", sb.toString());
	}

}