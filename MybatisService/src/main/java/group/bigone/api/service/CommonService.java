package group.bigone.api.service;

import group.bigone.api.advice.exception.CEmailSigninFailedException;
import group.bigone.api.advice.exception.FailScrapIDCardException;
import group.bigone.api.advice.exception.NoMatchIDCardException;
import group.bigone.api.common.constants.GlobalConstants;
import group.bigone.api.config.security.JwtTokenProvider;
import group.bigone.api.entity.User;
import group.bigone.api.model.response.SingleResult;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Optional;

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
