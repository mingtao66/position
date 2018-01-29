package net.hzbox.vj.journal.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "vj")
@Component
@lombok.Getter
@lombok.Setter
public class VjProperties {
    private String imgPath;
    private String webPath;
    private String wechatPath;
    private Integer smsValidate;
}
