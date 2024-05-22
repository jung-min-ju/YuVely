package sw_design.YNUMarketplace.exception.collections.IoException;

import sw_design.YNUMarketplace.exception.message.ImageExceptionMessage;

import java.io.IOException;


public class allImageFileIOException extends IOException {

    public allImageFileIOException() {
        super(ImageExceptionMessage.AllImageFileIOError);
    }
}
