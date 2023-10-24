package spring.practice.ksh.lic.vo;

import java.io.IOException;
import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = {"alId","licCode"})
public class LicAlba implements Serializable {
	private String alId;
	private String licCode;
	private String licDate;
	private byte[] licImg;
	
	private MultipartFile licImage;
	
	public void setLicImage(MultipartFile licImage) throws IOException {
		if(licImage!=null && !licImage.isEmpty()) {
			this.licImage = licImage;
			this.licImg = licImage.getBytes();
		}
	}
	
}