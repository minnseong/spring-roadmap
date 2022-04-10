package hello.typeconverter.type;

import lombok.EqualsAndHashCode;
import lombok.Getter;

// "127.0.0.0:8080"
@Getter
@EqualsAndHashCode
public class IpPort {

    private String ip;
    private int port;

    public IpPort(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }
}
