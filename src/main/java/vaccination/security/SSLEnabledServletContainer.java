package vaccination.security;

import lombok.Data;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

/*
*   You can run this with SSL/TLS.
*   For example, build the application (`mvn clean install`) then run:
*     java -Dspring.profiles.active=production -Dkeystore.file=file:///`pwd`/src/main/resources/keystore.p12 -jar target/vaccination-table-0.0.1-SNAPSHOT.jar
*
*   Generate a keystore file using:
*     keytool -genkey -alias tomcat -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore keystore.p12 -validity 3650
*/
@Component
@Profile("production")
@Data
public class SSLEnabledServletContainer implements EmbeddedServletContainerCustomizer {
    @Value("${secure.port}") int port;
    @Value("${keystore.file}") Resource keystoreFile;
    @Value("${keystore.pass}") String keystorePass;

    @Override
    public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
        final String absoluteKeystoreFile;
        try {
            absoluteKeystoreFile = keystoreFile.getFile().getAbsolutePath();
            TomcatEmbeddedServletContainerFactory tomcat = (TomcatEmbeddedServletContainerFactory) configurableEmbeddedServletContainer;
            tomcat.addConnectorCustomizers(
                    (connector) -> {
                        connector.setPort(port);
                        connector.setSecure(true);
                        connector.setScheme("https");

                        Http11NioProtocol proto = (Http11NioProtocol) connector.getProtocolHandler();
                        proto.setSSLEnabled(true);
                        proto.setKeystoreFile(absoluteKeystoreFile);
                        proto.setKeystorePass(keystorePass);
                        proto.setKeystoreType("PKCS12");
                        proto.setKeyAlias("tomcat");
                    }
            );
        } catch (IOException e) {
        }
    }
}
