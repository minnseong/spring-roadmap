package hello.core.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient3 {

    private String url;

    public NetworkClient3() {
        System.out.println("생성자 호출, url : " + url);
    }

    public void setUrl(String url) {
        System.out.println("NetworkClient.setUrl");
        this.url = url;
    }

    public void connect() {
        System.out.println("connect : " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url +  " message = " + message);
    }

    public void disconnect() {
        System.out.println("close " + url);
    }

    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
