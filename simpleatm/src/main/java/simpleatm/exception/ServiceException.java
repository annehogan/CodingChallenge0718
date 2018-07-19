package simpleatm.exception;

import simpleatm.model.ServiceErrorCode;

/**
 * ServiceException - simple exception for handing error codes and messages while maintaining caught Exceptions by
 * extending and not consuming them.
 *
 * @Author Anne Hogan
 * @Copyright 2018
 */

public class ServiceException extends RuntimeException {
    private final ServiceErrorCode serviceErrorCode;

    public ServiceException(ServiceErrorCode serviceErrorCode) {
        super(serviceErrorCode.getErrorMessage());
        this.serviceErrorCode = serviceErrorCode;
    }

    public ServiceException(Throwable cause, ServiceErrorCode serviceErrorCode) {
        super(cause);
        this.serviceErrorCode = serviceErrorCode;
    }

    public ServiceException(String message, Throwable cause, ServiceErrorCode serviceErrorCode) {
        super(message, cause);
        this.serviceErrorCode = serviceErrorCode;
    }

    public ServiceErrorCode getServiceErrorCode() {
        return serviceErrorCode;
    }
}