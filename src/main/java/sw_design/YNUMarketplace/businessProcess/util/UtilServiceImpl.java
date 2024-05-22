package sw_design.YNUMarketplace.businessProcess.util;


import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import sw_design.YNUMarketplace.businessProcess.auth.dto.auth.CheckPasswordDto;
import sw_design.YNUMarketplace.config.contant.DbInitConstants;
import sw_design.YNUMarketplace.exception.collections.IoException.NotExitsInitImageFileException;
import sw_design.YNUMarketplace.exception.collections.IoException.allImageFileIOException;
import sw_design.YNUMarketplace.exception.collections.business.PasswordNotMatchException;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Component
@RequiredArgsConstructor
public class UtilServiceImpl implements UtilService {
    private final ResourceLoader resourceLoader;

    @Override
    public String getRandomNum() {
        return String.valueOf(ThreadLocalRandom.current().nextInt(100000, 900000));
    }

    @Override
    public String getRandomUUID(String originalName) {
        String uuid = UUID.randomUUID().toString();
        return uuid + originalName;
    }

    @Override
    public String getInitImagePath() throws IOException { //해당 에러 발생시, 상위 함수로 에러 던져짐
        Resource resource = resourceLoader.getResource("classpath:static/image/" + DbInitConstants.initImageFileName);

        if (!resource.exists()) throw new NotExitsInitImageFileException();

        try {
            return resource.getURI().getPath();
        } catch (IOException e) {
            throw new allImageFileIOException();
        }
    }

    @Override
    public void checkPassword(CheckPasswordDto checkPasswordDto) {
        if(!checkPasswordDto.getPasswrod().equals(checkPasswordDto.getCheckPassword())) throw new PasswordNotMatchException();
    }
}
