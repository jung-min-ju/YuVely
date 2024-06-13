package sw_design.YNUMarketplace.businessProcess.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sw_design.YNUMarketplace.businessProcess.auth.dto.auth.SignInDto;
import sw_design.YNUMarketplace.businessProcess.auth.dto.auth.SignUpDto;
import sw_design.YNUMarketplace.businessProcess.auth.service.Facade.AuthFacadeService;
import sw_design.YNUMarketplace.config.contant.ControllerConstants;
import sw_design.YNUMarketplace.exception.collections.InputValid.BindingErrors;
import sw_design.YNUMarketplace.springSecurity.redis.dto.jwt.AllJwtTokenDto;

import java.io.IOException;

@Tag(name = "Auth", description = "auth Api")
@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthFacadeService authFacadeService;

    @Transactional //매니저와 보육원의 동시저장을 보장해줄 애노테이션
    @PostMapping("/signUp")
    @Operation(summary = "signUp Api summary", description = "회원가입 시 사용할 api 명세서")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
            content = {@Content(schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Not founc"),
    })
    public ResponseEntity<?> authSignup(@RequestBody @Valid SignUpDto signUpDto, BindingResult bindingResult ) throws IOException {
        //@Valid 체크
        handleBindingErrors(bindingResult);
        authFacadeService.authSignup(signUpDto);
        return new ResponseEntity<>(ControllerConstants.completeSignUp, HttpStatus.OK);
    }

    @Transactional
    @Operation(summary = "로그인 api", description = "로그인 api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved recruitments",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AllJwtTokenDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    @PostMapping("/signIn")
    public ResponseEntity<?> authSignIn(@RequestBody @Valid SignInDto signDto, BindingResult bindingResult) {
        // BindingResult 에러 처리
        handleBindingErrors(bindingResult);

        AllJwtTokenDto allJwtTokenDto = authFacadeService.authSignIn(signDto);
        return ResponseEntity.ok(allJwtTokenDto);
    }

    public void handleBindingErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BindingErrors();
        }
    }

}
