package spring.practice.ksh.alba.vo;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.EqualsAndHashCode;
import spring.practice.ksh.lic.vo.LicAlba;

@Data
@EqualsAndHashCode(of = "alId")
public class AlbaVO implements Serializable {
	private String alId;
	private String alName;
	private Integer alAge;
	private String alZip;
	private String alAddr1;
	private String alAddr2;
	private String alHp;
	private String grCode;
	private String alGen;
	private String alMail;
	private String alCareer;
	private String alSpec;
	private String alDesc;
	private String alImg;
	
	private Set<LicAlba> licSet;
	
	
	private MultipartFile alImage;
	
	public void setAlImage(MultipartFile alImage) {
		if(alImage != null && !alImage.isEmpty()) {
			this.alImage = alImage;
			this.alImg = UUID.randomUUID().toString();
		}
	}
	
	public void saveTo(File saveFolder) throws IllegalStateException, IOException {
		if(alImage != null && !alImage.isEmpty()) {
			alImage.transferTo(new File(saveFolder,this.alImg));
		}
	}
	
	
	
}
