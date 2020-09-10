package request;

public enum HttpMethod {
    POST(new PostRequest()), GET(new GetRequest()), DELETE(new DeleteRequest());

    private final HttpRequest httpRequest;

    HttpMethod(final HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    public HttpRequest createHttpRequest() {
        return httpRequest;
    }
}