package sw_design.YNUMarketplace.exception.collections.IoException;

import sw_design.YNUMarketplace.exception.message.DbExceptionMessage;

import java.io.IOException;


public class allImageFileIOException extends IOException {

    public allImageFileIOException() {
        super(DbExceptionMessage.AllImageFileIOError);
    }
}
