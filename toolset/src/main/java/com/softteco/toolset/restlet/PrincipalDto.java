package com.softteco.toolset.restlet;

import java.io.Serializable;
import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.catalina.realm.GenericPrincipal;

/**
 *
 * @author serge
 */
public class PrincipalDto implements Serializable {

    public static PrincipalDto build(final HttpServletRequest httpServletRequest) {
        final PrincipalDto dto = new PrincipalDto();
        final Principal principal = httpServletRequest.getUserPrincipal();
        if (principal == null) {
            return null;
        }
        dto.username = principal.getName();
        dto.roles = getRoles(httpServletRequest);
        return dto;
    }

    private static Set<String> getRoles(final HttpServletRequest httpServletRequest) {
        final Principal userPrincipal = httpServletRequest.getUserPrincipal();
        if (userPrincipal == null) {
            return null;
        }
        final GenericPrincipal genericPrincipal = (GenericPrincipal) userPrincipal;
        final String[] roles = genericPrincipal.getRoles();
        if (roles == null) {
            return null;
        }
        return new HashSet<String>(Arrays.asList(roles));
    }

    public String username;
    public Set<String> roles = Collections.EMPTY_SET;
}
