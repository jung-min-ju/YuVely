package sw_design.YNUMarketplace.businessProcess.util;

import sw_design.YNUMarketplace.businessProcess.auth.dto.auth.CheckPasswordDto;

import java.io.IOException;

public interface UtilService {
    String getRandomNum();
    String getRandomUUID(String originalName);
    String getInitImagePath() throws IOException;
    void checkPassword(CheckPasswordDto checkPasswordDto);
}
