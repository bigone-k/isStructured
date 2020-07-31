package group.bigone.api.service;

import group.bigone.api.advice.exception.NoDataCodeException;
import group.bigone.api.common.constants.StateCode;
import group.bigone.api.config.security.JwtTokenProvider;
import group.bigone.api.entity.ProcessStep;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class ProcessStepService {

    private final JwtTokenProvider jwtTokenProvider;
    private final SqlSession sqlSession;

    public ProcessStepService(JwtTokenProvider jwtTokenProvider, SqlSession sqlSession) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.sqlSession = sqlSession;
    }

    //    # Find Process Step
    public Optional<ProcessStep> FindProcessStep() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Long userNo = Long.valueOf(jwtTokenProvider.getUserPk(jwtTokenProvider.resolveToken(request)));

        ProcessStep processStep = (ProcessStep) sqlSession.selectOne("processstep.selectProcessStep", userNo);

        return Optional.of(processStep);
    }

    //    # Update Process Step
    public void UpdateProcessStep(short stateCode) {
        ProcessStep processStep = FindProcessStep().orElseThrow(() -> new NoDataCodeException());

        ProcessStep updateprocessStep = ProcessStep.UpdateBuilder()
                .seqNo(processStep.getSeqNo())
                .stateCode(stateCode)
                .build();

        sqlSession.update("processstep.updateProcessStep", updateprocessStep);
    }

    //    # Insert Process Step
    public void InsertProcessStep(short processStepCode) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Long userNo = Long.valueOf(jwtTokenProvider.getUserPk(jwtTokenProvider.resolveToken(request)));

        ProcessStep insertProcessStep = ProcessStep.InsertBuilder()
                .stateCode(StateCode.WAIT.getCode())
                .userNo(userNo)
                .stepType(processStepCode)
                .build();

        sqlSession.insert("processstep.insertProcessStep", insertProcessStep);
    }
}
