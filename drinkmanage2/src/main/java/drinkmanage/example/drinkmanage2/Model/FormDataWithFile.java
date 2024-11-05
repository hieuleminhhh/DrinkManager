package drinkmanage.example.drinkmanage2.Model;

import org.springframework.web.multipart.MultipartFile;

public class FormDataWithFile {
	private MultipartFile file;
	private String filePath;
	private String fileName;
	public FormDataWithFile() {
		// TODO Auto-generated constructor stub
	}
	
	public FormDataWithFile(MultipartFile file, String filePath, String fileName) {
		super();
		this.file = file;
		this.filePath = filePath;
		this.fileName = fileName;
	}

	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	
}
