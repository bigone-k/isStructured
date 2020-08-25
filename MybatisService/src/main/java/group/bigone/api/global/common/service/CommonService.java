package group.bigone.api.global.common.service;

import group.bigone.api.global.common.constants.GlobalConstants;
import group.bigone.api.global.advice.auth.JwtTokenProvider;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class CommonService {
    @Value("${spring.url.base}")
    private String baseUrl;

    private final JwtTokenProvider jwtTokenProvider;
    private final SqlSession sqlSession;

    CommonService(JwtTokenProvider jwtTokenProvider, SqlSession sqlSession) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.sqlSession = sqlSession;
    }

    //    # File upload
    public String FileUpload(MultipartFile file) throws Exception {
        File dest = new File(GlobalConstants.FILE_PATH + file.getOriginalFilename());
        file.transferTo(dest);

        return GlobalConstants.FILE_PATH + file.getOriginalFilename();
    }
}
