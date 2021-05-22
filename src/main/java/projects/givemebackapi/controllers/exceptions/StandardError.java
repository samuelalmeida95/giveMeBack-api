package projects.givemebackapi.controllers.exceptions;

import lombok.Data;

@Data
public class StandardError {

    private Long timestamp;
    private Integer status;
    private String message;

    public StandardError() {
        super();
    }

    public StandardError(Long timestamp, Integer status, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
    }
}
