package com.softteco.toolset.restlet;

import java.util.List;
import java.util.Set;

/**
 *
 * @author serge
 */
public interface UserSession {

    String getUsername();

    void setUsername(String username);

    String getLang();

    void setLang(String lang);

    Set<String> getRoles();

    void setRoles(Set<String> roles);
    
    boolean hasRole(String role);
    
    boolean hasRoles(String... roles);
    
    boolean hasRoles(List<String> roles);
}
