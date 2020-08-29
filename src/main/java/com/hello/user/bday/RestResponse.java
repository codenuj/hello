package com.hello.user.bday;

public class RestResponse<String> {

    private Integer statusCode;
    private String message;

    public RestResponse(){
        super();
    }

    public RestResponse(String message,Integer statusCode){
        super();
        this.message=message;
        this.statusCode=statusCode;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
