package group.bigone.api.controller.v1;

import group.bigone.api.advice.exception.FailScrapIDCardException;
import group.bigone.api.common.annotation.AnnotationProcessStep;
import group.bigone.api.common.constants.GlobalConstants;
import group.bigone.api.common.constants.ProcessStepCode;
import group.bigone.api.common.constants.Step;
import group.bigone.api.entity.IDCard;
import group.bigone.api.model.response.CommonResult;
import group.bigone.api.model.response.SingleResult;
import group.bigone.api.service.IDCardService;
import group.bigone.api.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = {"Bank"})
@RestController
@RequestMapping(value = GlobalConstants.PREFIX_URL)
public class IDCardController {
    private final ResponseService responseService;
    private final IDCardService idCardService;

    public IDCardController(ResponseService responseService, IDCardService idCardService) {
        this.responseService = responseService;
        this.idCardService = idCardService;
    }

    @AnnotationProcessStep(processStepCode=ProcessStepCode.IDCARD, step = Step.START)
    @ApiImplicitParams({@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "AccessToken", required = true, dataType = "String", paramType = "header")})
    @ApiOperation(value = "Get IDCard Information", notes = "Scan IDCard")
    @PostMapping(value = "/idcardscan")
    public SingleResult<IDCard> IDCardScanner(@RequestPart MultipartFile file) throws Exception {
        // File Upload
        idCardService.FileUpload(file);

        // Scan IDCard
        IDCard reponseIDCard = idCardService.ScanIDCard(file).orElseThrow(() -> new FailScrapIDCardException());

        return responseService.getSingleResult(reponseIDCard);
    }

    @AnnotationProcessStep(processStepCode=ProcessStepCode.IDCARD, step = Step.END)
    @ApiImplicitParams({@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "AccessToken", required = true, dataType = "String", paramType = "header")})
    @ApiOperation(value = "Check IDCard Information", notes = "check IDCard")
    @PostMapping(value = "/checkidcard")
    public CommonResult CheckIdCard(@RequestBody IDCard idCard) {
        // Check IDCard
        idCardService.CheckIDCard(idCard);

        return responseService.getSuccessResult();
    }
}
