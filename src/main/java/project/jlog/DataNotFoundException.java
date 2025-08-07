package project.jlog;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/* 예외가 발생하면 스프링부트는 설정된 HTTP 상태코드와 이유를 포함한 응답을 생성하여 클라이언트에게 반환한다.*/
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "entity not found")
public class DataNotFoundException extends RuntimeException {
  private static final long serialVersionUID = 1L;
    public DataNotFoundException(String message) {
        super(message);
    }
}
