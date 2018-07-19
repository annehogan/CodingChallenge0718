package simpleatm.model;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.io.Serializable;
import java.util.Objects;

@JsonRootName("error")
public class ServiceErrorCode implements Serializable {
    private String code;
    private String errorMessage;

    public ServiceErrorCode(String code, String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceErrorCode that = (ServiceErrorCode) o;
        return Objects.equals(code, that.code) &&
                Objects.equals(errorMessage, that.errorMessage);
    }

    @Override
    public int hashCode() {

        return Objects.hash(code, errorMessage);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ServiceErrorCode{");
        sb.append("code='").append(code).append('\'');
        sb.append(", errorMessage='").append(errorMessage).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
