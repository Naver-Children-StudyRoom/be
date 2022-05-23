package nvc.studyroom.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(SQLException.class)
    public ResponseEntity handleSqlException() {
        return new ResponseEntity<>("SqlException", HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity handleSignUpException() {
//        return new ResponseEntity<>("SignUpException", HttpStatus.BAD_REQUEST);
//    }
}
