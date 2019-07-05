package com.kunyong.rds.aop;

import com.kunyong.rds.exceptions.BizException;
import com.kunyong.rds.utils.ResponseUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.QueryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * @类描述：
 * @项目名称：cprpm-web
 * @包名： com.cprpm.aop
 * @类名称：ControllerAop
 * @创建人：zhangsongli
 * @创建时间：2018年10月27日下午3:52:44
 * @修改人：zhangsongli
 * @修改时间：2018年10月27日下午3:52:44
 * @修改备注：
 * @version v1.0
 */
@Component
@Aspect
public class ControllerAop {

	private static final Logger log = LoggerFactory.getLogger("adminLogger");

	/*@Pointcut("execution(public com.kunyong.rds.utils.ResponseUtil *(..))")*/
	@Around(value = "execution(public com.kunyong.rds.utils.ResponseUtil *(..))")
	public Object handlerControllerMethod(ProceedingJoinPoint pjp) {
		long startTime =System.currentTimeMillis();
		
		ResponseUtil<?> result;
		try {
			result =(ResponseUtil<?>) pjp.proceed();
		}catch(Throwable e) {
			result =handlerException(pjp,e);
		}
		return result;
	}

	
	/**
	 * @描述:
	 * @方法名: handlerException
	 * @param pjp
	 * @param e
	 * @return
	 * @返回类型 ResultBean<?>
	 * @since
	 * @throws
	 */
	
	private ResponseUtil<?> handlerException(ProceedingJoinPoint pjp, Throwable e) {
		ResponseUtil<?> result = new ResponseUtil();
		// 已知异常
		if(e instanceof BizException) {
			result.setErrMessage(e.getLocalizedMessage());
			result.setResponseCode(2);
		}else {
			//未知异常。。
			log.info("异常信息:{}",e.getLocalizedMessage());
			result.setErrMessage("系统异常,请联系维护人员....");
			result.setResponseCode(2);
		}
		return result;
	}
	
	
	
}
