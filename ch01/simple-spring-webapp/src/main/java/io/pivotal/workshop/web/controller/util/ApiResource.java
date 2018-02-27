package io.pivotal.workshop.web.controller.util;

public class ApiResource {

	private String method;
	private String path;
	private String parameters;
	private String link;
	private String example;

	public ApiResource(String method, String path, String parameters, String link) {
		this.method = method;
		this.path = path;
		this.parameters = parameters;
		this.link = link;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}

}
