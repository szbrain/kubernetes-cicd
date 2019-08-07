package com.koolearn.club.ocr.http;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    
	private String url = "";
	private HttpMethod method = HttpMethod.POST;
	private HttpContentType contentType = HttpContentType.MULTIPART_FORM_DATA;
	private Map<String, String> headers = new HashMap<String,String>();
	private Map<String, Object> params = new HashMap<String, Object>();
    private HashMap<String, File> imageList = new HashMap<String, File>();
    private Map<String, byte[]> bytesContentList = new HashMap<String, byte[]>();

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	HttpMethod getMethod() {
		return method;
	}

	public void setMethod(HttpMethod method) {
		this.method = method;
	}

	HttpContentType getContentType() {
		return contentType;
	}

	public void setContentType(HttpContentType contentType) {
		this.contentType = contentType;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	Map<String, Object> getParams() {
		return params;
	}

	public void addHeader(String key, String value) {
		this.headers.put(key, value);
	}

	public void addParam(String key, Object value) {
		this.params.put(key, value);
	}

    /** @return Map<"http param key", File> */
    HashMap<String, File> getImageList() {
            return imageList;
        }

    public void addFile(String httpParamName, File file) {
        imageList.put(httpParamName, file);
    }

    public void addBytes(String key, byte[] content) {
        bytesContentList.put(key, content);
    }

    Map<String, byte[]> getBytesContentList() {
        return bytesContentList;
    }

}
