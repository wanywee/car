package com.carTravelsky.utils;


import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class FileServer {
	private static final String boundary = "------ystpay-boundary";
	
	private URL serverURL;
	private HttpURLConnection conn;
	private Map<String, Object> map = new HashMap<String, Object>();
	private DataOutputStream ds;
	public FileServer(String url)throws Exception {
		serverURL = new URL(url);
	}
	
	public void addFile(String name, File value){
		map.put(name, value);
	}
	
	public void addFile(String name, String filePath){
		addFile(name, new File(filePath));
	}
	
	public void addFile(String name, InputStream in){
		map.put(name, in);
	}
	
	// 发送数据到服务器，返回一个字节包含服务器的返回结果的数组
	public byte[] send() throws Exception {
		initConnection();
		try {
			conn.connect();
		} catch (SocketTimeoutException e) {
			// something
			throw new RuntimeException();
		}
		ds = new DataOutputStream(conn.getOutputStream());
		writeFile();
		paramsEnd();
		InputStream in = conn.getInputStream();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int b;
		while ((b = in.read()) != -1) {
			out.write(b);
		}
		conn.disconnect();
		return out.toByteArray();
	}
	
	// 文件上传的connection的一些必须设置
	private void initConnection() throws Exception {
		conn = (HttpURLConnection) serverURL.openConnection();
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		conn.setConnectTimeout(10000); // 连接超时为10秒
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
	}
	
	private void writeFile() throws Exception{
		Set<String> keySet = map.keySet();
		for(Iterator<String> it = keySet.iterator(); it.hasNext();){
			String name = it.next();
			Object value = map.get(name);
			ds.writeBytes("--" + boundary + "\r\n");
			if(value instanceof File){
				File file = (File)value;
				ds.writeBytes("Content-Disposition: form-data; name=\"" + name + "\"; filename=\"" + encode(file.getName()) + "\"\r\n");
				ds.writeBytes("Content-Type: " + getContentType(file) + "\r\n");
				ds.writeBytes("\r\n");
				ds.write(getBytes(file));
			}
			if(value instanceof InputStream){
				InputStream is = (InputStream)value;
				ds.writeBytes("Content-Disposition: form-data; name=\"" + name + "\"; filename=\"" + encode(System.nanoTime()+"") + "\"\r\n");
				ds.writeBytes("Content-Type: application/octet-stream\r\n");
				ds.writeBytes("\r\n");
				ds.write(getBytes(is));
			}
			ds.writeBytes("\r\n");
		}
	}
	
	// 获取文件的上传类型，图片格式为image/png,image/jpg等。非图片为application/octet-stream
	private String getContentType(File f) throws Exception {
		// return "application/octet-stream"; //
		// 此行不再细分是否为图片，全部作为application/octet-stream 类型
		ImageInputStream imagein = ImageIO.createImageInputStream(f);
		if (imagein == null) {
			return "application/octet-stream";
		}
		Iterator<ImageReader> it = ImageIO.getImageReaders(imagein);
		if (!it.hasNext()) {
			imagein.close();
			return "application/octet-stream";
		}
		imagein.close();
		return "image/" + it.next().getFormatName().toLowerCase();// 将FormatName返回的值转换成小写，默认为大写
	}
	
	// 把文件转换成字节数组
	private byte[] getBytes(File f) throws Exception {
		FileInputStream in = new FileInputStream(f);
		return getBytes(in);
	}
	//把输入流转换成字节数组
	private byte[] getBytes(InputStream in) throws Exception{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] b = new byte[2048];
		int n;
		while ((n = in.read(b)) != -1) {
			out.write(b, 0, n);
		}
		in.close();
		return out.toByteArray();
	}

	// 添加结尾数据
	private void paramsEnd() throws Exception {
		ds.writeBytes("--" + boundary + "--" + "\r\n");
		ds.writeBytes("\r\n");
	}

	private String encode(String value) throws Exception {
		return URLEncoder.encode(value, "UTF-8");
	}
}
