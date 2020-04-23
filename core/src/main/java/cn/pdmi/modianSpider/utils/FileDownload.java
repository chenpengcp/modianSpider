package cn.pdmi.modianSpider.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

import org.apache.http.impl.client.CloseableHttpClient;


public class FileDownload {
	private static int BUFFER_SIZE = 8096;

	/**
	 * 下载文件
	 * 
	 * @param fileUrl
	 *            完整的URL
	 * @param savePath
	 *            完整的保存路�?
	 * @throws Exception
	 */
	public static void download(String fileUrl, String savePath)
			throws Exception {
		FileOutputStream fos = null;
		InputStream bis = null;
		CloseableHttpClient httpClient = null;
		HttpURLConnection httpUrl = null;
		boolean https = false;
		URL url = null;
		try {
			// 建立链接
			if (fileUrl.startsWith("http://")) {
				url = new URL(fileUrl);
			} else if (fileUrl.startsWith("https://")) {
				https = true;
			} else {
				url = new URL("http://" + fileUrl);
			}
			if(!https) {
				httpUrl = (HttpURLConnection) url.openConnection();
//				httpUrl.setRequestProperty("User-Agent", UAUtils.getUA());
				httpUrl.setReadTimeout(1000 * 30);
				httpUrl.connect();
				bis = new BufferedInputStream(httpUrl.getInputStream());
				if(httpUrl.getHeaderFields().containsKey("Content-Encoding")
						&& httpUrl.getHeaderFields().get("Content-Encoding").contains("gzip")){
					bis = new GZIPInputStream(bis);
				}
			}
			byte[] buf = new byte[BUFFER_SIZE];
			int size = 0;
			// 建立文件
			File file = new File(savePath);
			if (!file.exists()) {
				if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}
				file.createNewFile();
			}
			if(file.isDirectory()){
				listDelete(file);
				if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}
				file.createNewFile();
			}
			fos = new FileOutputStream(savePath);
			while ((size = bis.read(buf)) != -1){
				fos.write(buf, 0, size);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			if(fos != null){
				fos.close();
			}
			if(bis != null){
				bis.close();
			}
			if(httpUrl != null){
				httpUrl.disconnect();
			}
			if(httpClient != null) {
				httpClient.close();
			}
		}
	}

	public static void listDelete(File file){
		if(file.isDirectory()){
			for(File f : file.listFiles()){
				listDelete(f);
			}
			file.delete();
		}else{
			file.delete();
		}
	}

	public static void main(String[] args) throws Exception{
		FileDownload.download("http://mmbiz.qpic.cn/mmbiz_jpg/aRG2tRtia9HR9oKlgaZlvSo6DR3PDNdOJDibQnXRBRNwFwricnic1TzWx9TTYvKXk2eicXuyW0Vic2y3AyQLxexAiafKg/0?wx_fmt=jpeg","D:\\downloads\\999.jpg");
	}
}
