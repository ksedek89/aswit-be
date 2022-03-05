package pl.aswit.rest.config.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Feign;
import feign.Logger;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.form.FormEncoder;
import feign.slf4j.Slf4jLogger;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.support.PageableSpringEncoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import pl.aswit.rest.client.KeycloakFeignClient;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
@EnableConfigurationProperties
public class FeignClientsConfig {

    @Autowired
    ObjectFactory<HttpMessageConverters> messageConverters;
    @Value("${keycloak.auth-server-url}")
    private String keycloakUrl;

    @Bean
    public KeycloakFeignClient psaOrdsClient() {
        return Feign.builder()
            .decoder(feignDecoder())
            .encoder(feignEncoderPageable())
            .logLevel(feignLoggerLevel())
            .logger(new Slf4jLogger(KeycloakFeignClient.class))
            .client(new feign.okhttp.OkHttpClient(getUnsafeOkHttpClient(10000, 10000)))
            .target(KeycloakFeignClient.class, keycloakUrl);
    }


    private OkHttpClient getUnsafeOkHttpClient(long connectTimeout, long readTimeout) {
        try {
            final TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return new java.security.cert.X509Certificate[]{};
                    }
                }
            };

            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder().readTimeout(readTimeout, TimeUnit.MILLISECONDS).connectTimeout(connectTimeout, TimeUnit.MILLISECONDS);
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier((hostname, session) -> true);

            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
    private Encoder feignEncoderPageable() {
        return new FormEncoder(new SpringEncoder(this.messageConverters));
    }

    private Decoder feignDecoder() {
        val jacksonConverter = new MappingJackson2HttpMessageConverter(new ObjectMapper());
        ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(jacksonConverter);
        return new SpringDecoder(objectFactory);
    }

}
