package group.bigone.api.service;

import group.bigone.api.model.response.CommonResult;
import group.bigone.api.model.response.ListResult;
import group.bigone.api.model.response.SingleResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {
    public enum CommonResponse {
        SUCCESS(0, "success"),
        FAIL(-1, "failure");

        int ResultCode;
        String ResultMessage;

        CommonResponse(int ResultCode, String ResultMessage) {
            this.ResultCode = ResultCode;
            this.ResultMessage = ResultMessage;
        }

        public int getResultCode() {
            return this.ResultCode;
        }

        public String getResultMessage() {
            return this.ResultMessage;
        }
    }


    public  <T> SingleResult<T> getSingleResult(T data) {
        SingleResult<T> result = new SingleResult<T>();
        result.setData(data);
        setSuccessResult(result);
        return  result;
    }

    public <T> ListResult<T> getListResult(List<T> list) {
        ListResult<T> result = new ListResult<>();
        result.setList(list);
        setSuccessResult(result);
        return result;
    }

    // 성공 결과만 처리하는 메소드
    public CommonResult getSuccessResult() {
        CommonResult result = new CommonResult();
        setSuccessResult(result);
        return result;
    }

    // 실패 결과만 처리하는 메소드
    public CommonResult getFailResult() {
        CommonResult result = new CommonResult();
        setFailResult(result);
        return result;
    }

    // 실패 결과만 처리하는 메소드
    public CommonResult getFailResult(int code, String msg) {
        CommonResult result = new CommonResult();
        result.setSuccess(false);
        result.setResultCode(code);
        result.setResultMessage(msg);
        return result;
    }

    // 결과 모델에 api 요청 성공 데이터를 세팅해주는 메소드
    private void setSuccessResult(CommonResult result) {
        result.setSuccess(true);
        result.setResultCode(CommonResponse.SUCCESS.getResultCode());
        result.setResultMessage(CommonResponse.SUCCESS.getResultMessage());
    }

    // 결과 모델에 api 요청 성공 데이터를 세팅해주는 메소드
    private void setFailResult(CommonResult result) {
        result.setSuccess(true);
        result.setResultCode(CommonResponse.FAIL.getResultCode());
        result.setResultMessage(CommonResponse.FAIL.getResultMessage());
    }
}
