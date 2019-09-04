package xyz.drafter.architecture.gupao.springV1.annotion;

import java.lang.annotation.*;

/**
 * @author wangmeng
 * @date 2019/9/4
 * @desciption
 */

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMapping {

}
