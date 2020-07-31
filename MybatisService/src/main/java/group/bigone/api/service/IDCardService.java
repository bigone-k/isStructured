package group.bigone.api.service;

import group.bigone.api.advice.exception.CEmailSigninFailedException;
import group.bigone.api.advice.exception.FailScrapIDCardException;
import group.bigone.api.advice.exception.NoMatchIDCardException;
import group.bigone.api.common.constants.GlobalConstants;
import group.bigone.api.config.security.JwtTokenProvider;
import group.bigone.api.entity.IDCard;
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
public class IDCardService {
    @Value("${spring.url.base}")
    private String baseUrl;

    private final JwtTokenProvider jwtTokenProvider;
    private final SqlSession sqlSession;
    private final UserService userService;

    IDCardService(JwtTokenProvider jwtTokenProvider, SqlSession sqlSession, UserService userService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.sqlSession = sqlSession;
        this.userService = userService;
    }

    //    # File upload
    public String FileUpload(MultipartFile file) throws Exception {
        File dest = new File(GlobalConstants.FILE_PATH + file.getOriginalFilename());
        file.transferTo(dest);

        return GlobalConstants.FILE_PATH + file.getOriginalFilename();
    }

    //    # Image Scraping
    public Optional<IDCard> ScanIDCard(MultipartFile file) {
        // IDCard scraping
        IDCard requestIdCard = new IDCard();
        requestIdCard.setFile(file);

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = jwtTokenProvider.resolveToken(request);

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        httpHeaders.add(GlobalConstants.CONTENT_TYPE, token);

        HttpEntity<?> httpEntity = new HttpEntity<>(requestIdCard, httpHeaders);

        ResponseEntity<SingleResult<IDCard>> responseEntity =
                restTemplate.exchange(baseUrl.concat(GlobalConstants.IDCARD_SCRAPING),
                        HttpMethod.POST, httpEntity, new ParameterizedTypeReference<SingleResult<IDCard>>() {});

        if (!responseEntity.getStatusCode().equals(HttpStatus.OK))
            throw new FailScrapIDCardException();

        return Optional.of(responseEntity.getBody().getData());
    }

    //    # Check IDCard
    public void CheckIDCard(IDCard idCard) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = jwtTokenProvider.resolveToken(request);
        Long userNo = Long.valueOf(jwtTokenProvider.getUserPk(token));

        /// TODO : 암/복호화 해야함.

        // check Client
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        httpHeaders.add(GlobalConstants.CONTENT_TYPE, token);

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<SingleResult<User>> responseEntity =
                restTemplate.exchange(baseUrl.concat(GlobalConstants.USER_GET),
                        HttpMethod.GET, httpEntity, new ParameterizedTypeReference<SingleResult<User>>() {});
        if (!responseEntity.getStatusCode().equals(HttpStatus.OK))
            throw new CEmailSigninFailedException();

        if (!responseEntity.getBody().getData().getName().equals(idCard.getName()))
            throw new CEmailSigninFailedException();

        InsertIDCard(idCard);
    }

    public void InsertIDCard(IDCard idCard) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = jwtTokenProvider.resolveToken(request);
        Long userNo = Long.valueOf(jwtTokenProvider.getUserPk(token));

        try {
            // Insert IDCard
            idCard.setUserNo(userNo);
            sqlSession.insert("idcard.insertIDCard", idCard);
        }
        catch (Exception ex) {
            throw new NoMatchIDCardException();
        }
    }
}
