package annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 * controller Token注解类,凡使用了此注解的controller均需要token验证通过才能访问
 * </pre>
 *
 * @author fengyts
 * @version $Id: Token.java, v 0.1 2017年12月9日 下午12:46:28 fengyts Exp $
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Token {

	/**
	 * <pre>
	 * 是否需要校验token
	 * </pre>
	 *
	 * @return
	 */
	boolean needValidate() default false;

}
