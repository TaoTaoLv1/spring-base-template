package com.dokang.center.module.security.authentication;

import com.dokang.lib.jsonLib.mapper.JsonMapper;
import com.dokang.lib.utils.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author: YwT
 * @create: 2019-01-04 21:59
 **/
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws
            AuthenticationException {
        //attempt Authentication when Content-Type is json
        if (request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE)
                || request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {


            UsernamePasswordAuthenticationToken authRequest = null;

            try {
                String data = StringUtils.readStringFormInputStream(request.getInputStream());
                Map<String, Object> dataMap = JsonMapper.getInstance().readValue(data);
                String username = (String) dataMap.get(getUsernameParameter());
                String password = (String) dataMap.get(getPasswordParameter());
                authRequest = new UsernamePasswordAuthenticationToken(username, password);
            } catch (IOException e) {
                e.printStackTrace();
                authRequest = new UsernamePasswordAuthenticationToken(
                        "", "");
            } finally {
                setDetails(request, authRequest);
                return this.getAuthenticationManager().authenticate(authRequest);
            }
        }
        //transmit it to UsernamePasswordAuthenticationFilter
        else {
            return super.attemptAuthentication(request, response);
        }
    }
}
