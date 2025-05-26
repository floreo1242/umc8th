package umc.study.apiPayload.exception.handler;

import umc.study.apiPayload.code.BaseErrorCode;
import umc.study.apiPayload.exception.GeneralException;

public class EntityNotFoundHandler extends GeneralException {

    public EntityNotFoundHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
