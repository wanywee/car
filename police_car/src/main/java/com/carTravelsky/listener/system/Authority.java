package com.carTravelsky.listener.system;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)  
@Target({ java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.TYPE })
@Documented
public @interface Authority {  
    // 默认验证
    AuthorityType value() default AuthorityType.Validate;
}  
