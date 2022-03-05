package pl.aswit.rest.config.auth;

import jdk.swing.interop.SwingInterOpUtils;
import lombok.extern.slf4j.Slf4j;
//import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Config;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.admin.client.token.TokenManager;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.authorization.AuthorizationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.stereotype.Component;
import pl.aswit.rest.client.KeycloakFeignClient;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static pl.aswit.rest.enums.CodeE.*;
import static pl.aswit.rest.utils.Utils.prepareHttpServletResponse;

@Slf4j
public class CheckSecurityFilter implements Filter  {


    private String keycloakUrl;
    private String clientId;
    private String secret;
    private KeycloakFeignClient keycloakFeignClient;

    public CheckSecurityFilter(String keycloakUrl, String clientId, String secret, KeycloakFeignClient keycloakFeignClient) {
        this.keycloakUrl = keycloakUrl;
        this.clientId = clientId;
        this.secret = secret;
        this.keycloakFeignClient = keycloakFeignClient;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
/*        HttpServletResponse httpServletResponse = ((HttpServletResponse) response);
        try {
            String bearerAccessToken = ((HttpServletRequest) request).getHeader("Authorization");
            if (bearerAccessToken == null) {
                prepareHttpServletResponse(httpServletResponse, 401, CODE_0001);
                return;
            }
            String[] split = bearerAccessToken.trim().split("\\s+");
            if (split.length != 2) {
                prepareHttpServletResponse(httpServletResponse, 401, CODE_0002);
                return;
            }
            String accessToken = null;
            if (split[0].equalsIgnoreCase("Bearer")) {
                accessToken = split[1];
            } else {
                prepareHttpServletResponse(httpServletResponse, 401, CODE_0003);
                return;
            }

            UserInfoResponse userInfo = keycloakFeignClient.getUserInfo(accessToken);
            System.out.println();

        }catch (Exception e){
            log.error(e.getMessage(), e);
            prepareHttpServletResponse(httpServletResponse, 500, CODE_0000);
            return;
        }*/

        chain.doFilter(request, response);
    }


}

