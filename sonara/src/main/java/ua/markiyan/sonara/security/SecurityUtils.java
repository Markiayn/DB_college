// ua.markiyan.sonara.security.SecurityUtils
package ua.markiyan.sonara.security;

import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityUtils {
    private SecurityUtils(){}
    public static Long currentUserId(){
        var a = SecurityContextHolder.getContext().getAuthentication();
        if (a == null) return null;
        Object p = a.getPrincipal();
        return (p instanceof SecurityUser su) ? su.id() : null;
    }
}
