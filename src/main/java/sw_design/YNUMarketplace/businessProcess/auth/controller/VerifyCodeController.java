package sw_design.YNUMarketplace.businessProcess.auth.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sw_design.YNUMarketplace.businessProcess.auth.dto.smtp.EmailDto;
import sw_design.YNUMarketplace.businessProcess.auth.dto.smtp.PhoneNumDto;
import sw_design.YNUMarketplace.businessProcess.auth.dto.verification.VerificationEmailDto;
import sw_design.YNUMarketplace.businessProcess.auth.dto.verification.VerificationPhoneNumDto;
import sw_design.YNUMarketplace.businessProcess.auth.service.Email.EmailService;
import sw_design.YNUMarketplace.businessProcess.auth.service.PhoneNum.PhoneNumService;
import sw_design.YNUMarketplace.exception.collections.InputValid.BindingErrors;
import sw_design.YNUMarketplace.springSecurity.redis.dto.jwt.AllJwtTokenDto;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static sw_design.YNUMarketplace.config.contant.ControllerConstants.completeCheckVerifyNum;
import static sw_design.YNUMarketplace.config.contant.ControllerConstants.completeSendVerifyNum;

@RestController
@RequestMapping("api/verify")
@RequiredArgsConstructor
@Slf4j
public class VerifyCodeController {
    private final EmailService emailService;
    private final PhoneNumService phoneNumService;

    @Transactional
    @Operation(summary = "이메일 인증번호 보내기 api", description = "이메일 인증번호 보내기 api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved recruitments",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    @PostMapping("/send/email")
    public ResponseEntity<?> sendVerifyNumtoEmail(@RequestBody @Valid EmailDto emailDto, BindingResult bindingResult) throws MessagingException {
        handleBindingErrors(bindingResult);
        emailService.sendVerifyNumberByEmail(emailDto);
        return new ResponseEntity<>(completeSendVerifyNum, HttpStatus.OK);
    }

    @Transactional
    @Operation(summary = "이메일 인증번호 검증 api", description = "이메일 인증번호 검증 api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved recruitments",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    @PostMapping("/check/email")
    public ResponseEntity<?> validateVerifyNumbyEmail(@RequestBody @Valid  VerificationEmailDto verificationEmailDto, BindingResult bindingResult) throws MessagingException {
        handleBindingErrors(bindingResult);
        emailService.checkVerifyNumberByEmail(verificationEmailDto);
        return new ResponseEntity<>(completeCheckVerifyNum, HttpStatus.OK);
    }

    @Transactional
    @Operation(summary = "전화번호 인증번호 보내기 api", description = "전화번호 인증번호 보내기 api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved recruitments",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    @PostMapping("/send/phone/number")
    public ResponseEntity<?> sendVerifyNumtoPhoneNum(@RequestBody @Valid PhoneNumDto phoneNumDto, BindingResult bindingResult) throws UnsupportedEncodingException, NoSuchAlgorithmException, URISyntaxException, InvalidKeyException, JsonProcessingException {
        handleBindingErrors(bindingResult);
        phoneNumService.sendVerifyNumberByPhoneNum(phoneNumDto);
        return new ResponseEntity<>(completeSendVerifyNum, HttpStatus.OK);
    }

    @Transactional
    @Operation(summary = "전화번호 인증번호 검증 api", description = "전화번호 인증번호 검증 api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved recruitments",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    @PostMapping("/check/phone/number")
    public ResponseEntity<?> validateVerifyNumbyPhoneNum(@RequestBody @Valid VerificationPhoneNumDto verificationPhoneNumDto, BindingResult bindingResult) throws MessagingException {
        handleBindingErrors(bindingResult);
        phoneNumService.checkVerifyNumberByPhoneNum(verificationPhoneNumDto);
        return new ResponseEntity<>(completeCheckVerifyNum, HttpStatus.OK);
    }

    public void handleBindingErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BindingErrors();
        }
    }



}
