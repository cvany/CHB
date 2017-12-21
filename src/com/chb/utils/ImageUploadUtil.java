//package com.chb.utils;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.io.PrintWriter;  
//
//import javax.servlet.http.HttpServletRequest;         
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.struts2.ServletActionContext;
//
//
//public class ImageUploadUtil {
//	private File upload; // 文件
//	private String uploadContentType; // 文件类型
//	private String uploadFileName; // 文件名
//   
//	/**
//	 * 图片上传
//	 * 
//	 * @return
//	 * @throws IOException
//	 */
//	public String imgUpload() throws IOException {
//
//		// 获得response,request
//		HttpServletResponse response = ServletActionContext.getResponse();    
//		HttpServletRequest request = ServletActionContext.getRequest();
//
//		response.setCharacterEncoding("utf-8");
//		PrintWriter out = response.getWriter();
//		// CKEditor提交的很重要的一个参数
//		String callback = request.getParameter("CKEditorFuncNum");
//		String expandedName = ""; // 文件扩展名
//		if (uploadContentType.equals("image/pjpeg")
//				|| uploadContentType.equals("image/jpeg")) {
//			// IE6上传jpg图片的headimageContentType是image/pjpeg，而IE9以及火狐上传的jpg图片是image/jpeg
//			expandedName = ".jpg";
//		} else if (uploadContentType.equals("image/png")
//				|| uploadContentType.equals("image/x-png")) {
//			// IE6上传的png图片的headimageContentType是"image/x-png"
//			expandedName = ".png";
//		} else if (uploadContentType.equals("image/gif")) {
//			expandedName = ".gif";
//		} else if (uploadContentType.equals("image/bmp")) {
//			expandedName = ".bmp";
//		} else {
//			out.println("<script type=\"text/javascript\">");
//			out.println("window.parent.CKEDITOR.tools.callFunction(" + callback
//					+ ",''," + "'文件格式不正确（必须为.jpg/.gif/.bmp/.png文件）');");
//			out.println("</script>");
//			return null;
//		}
//		if (upload.length() > 600 * 1024) {
//			out.println("<script type=\"text/javascript\">");
//			out.println("window.parent.CKEDITOR.tools.callFunction(" + callback
//					+ ",''," + "'文件大小不得大于600k');");
//			out.println("</script>");
//			return null;
//		}
//
//		InputStream is = new FileInputStream(upload);
//		//图片上传路径
//		String uploadPath = ServletActionContext.getServletContext().getRealPath("/upload/img/");
//		String fileName = java.util.UUID.randomUUID().toString(); // 采用时间+UUID的方式随即命名
//		fileName += expandedName;
//		File file = new File(uploadPath);
//		if (!file.exists()) { // 如果路径不存在，创建
//			file.mkdirs();
//		}
//		File toFile = new File(uploadPath, fileName);
//		OutputStream os = new FileOutputStream(toFile);
//		byte[] buffer = new byte[1024];
//		int length = 0;
//		while ((length = is.read(buffer)) > 0) {
//			os.write(buffer, 0, length);
//		}
//		is.close();
//		os.close();
//
//		// 返回"图像"选项卡并显示图片  request.getContextPath()为web项目名 
//		out.println("<script type=\"text/javascript\">");
//		out.println("window.parent.CKEDITOR.tools.callFunction(" + callback
//				+ ",'" + request.getContextPath() + "/newsImages/" + fileName + "','')");
//		out.println("</script>");
//		return null;
//	}
//
//	public File getUpload() {
//		return upload;
//	}
//
//	public void setUpload(File upload) {
//		this.upload = upload;
//	}
//
//	public String getUploadContentType() {
//		return uploadContentType;
//	}
//
//	public void setUploadContentType(String uploadContentType) {
//		this.uploadContentType = uploadContentType;
//	}
//
//	public String getUploadFileName() {
//		return uploadFileName;
//	}
//
//	public void setUploadFileName(String uploadFileName) {
//		this.uploadFileName = uploadFileName;
//	}
//}
