package com.study.tomcat;

import org.apache.catalina.core.StandardContext;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * Tomcat 配置 Class
 */
@Configuration
public class TomcatConfiguration implements EmbeddedServletContainerCustomizer {

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        System.err.println(container.getClass());

        if (container instanceof TomcatEmbeddedServletContainerFactory) {

            TomcatEmbeddedServletContainerFactory factory =
                    (TomcatEmbeddedServletContainerFactory) container;
            factory.addContextCustomizers((context) -> {
                if (context instanceof StandardContext) {
                    StandardContext standardContext = (StandardContext) context;
                    // standardContext.setDefaultWebXml(); // 设置
                    standardContext.setReloadable(true);
                }
            });

            factory.addConnectorCustomizers(connector -> {
                connector.setPort(12345);
            });

        }

    }
}
