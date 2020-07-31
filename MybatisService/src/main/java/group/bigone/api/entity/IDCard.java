package group.bigone.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IDCard {
    private Long userNo;

    private String name;

    private String birthday;

    private String idCardSeqNo;

    @JsonIgnore
    private String idCardImage;

    @JsonIgnore
    private MultipartFile file;


    // scrapingBuilder
    @Builder(builderClassName = "scrapingBuilder", builderMethodName = "scrapingBuilder")
    public IDCard(Long userNo, String name, String birthday, String idCardSeqNo) {
        this.userNo = userNo;
        this.name = name;
        this.birthday = birthday;
        this.idCardSeqNo = idCardSeqNo;
    }

    // scannerBuilder
    @Builder(builderClassName = "scannerBuilder", builderMethodName = "scannerBuilder")
    public IDCard(Long userNo, String name, String birthday, String idCardSeqNo, String idCardImage) {
        this.userNo = userNo;
        this.name = name;
        this.birthday = birthday;
        this.idCardSeqNo = idCardSeqNo;
        this.idCardImage = idCardImage;
    }
}
