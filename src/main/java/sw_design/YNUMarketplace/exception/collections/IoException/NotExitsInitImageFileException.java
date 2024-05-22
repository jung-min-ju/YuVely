package sw_design.YNUMarketplace.exception.collections.IoException;

import sw_design.YNUMarketplace.exception.message.ImageExceptionMessage;
import java.io.IOException;

public class NotExitsInitImageFileException extends IOException {
    public NotExitsInitImageFileException() {
        super(ImageExceptionMessage.NoExitsInitImageFile);
    }
}
//참고 : IOexception은 체크예외에 해당되어서, 무조건 함수선언부 옆에 throws를 적어줘야함. runtimeExcepton은 비체크예외라 안적어도 됨
