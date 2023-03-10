package kh.spring.s02.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Service
@Aspect
public class AdviceLog {
	
	private static final Logger logger = LoggerFactory.getLogger(AdviceLog.class);
	
	
	// *  => 뭐든 상관없이 1개 이상이 존재한다.
	// .. => 0개 이상이 존재한다.
	// ↓  표시할 부분만 선택해서 보여주는 어노테이션
	@Pointcut("execution(public * kh.spring.s02..*Controller.*(..))")
	public void controllerPointCut() {}

	@Pointcut("execution(public * kh.spring.s02..*Dao.*(..))")
	public void daoPointCut() {}
	
	@Pointcut("execution(public * kh.spring.s02..*ServiceImpl.*(..))")
	public void serviceImplPointCut() {}

//	@Before("controllerPointCut()")
//	public void beforeControllerPointCut(JoinPoint jp) {
//		
//		Object[] args = jp.getArgs();
//		for(int i=0; i < args.length; i++) {
//			System.out.println("args[" + i + "]: " + args[i]);
//		}
//		System.out.println("컨트롤러 모든 메소드가 호출되면 해당메소드(타겟메소드)가 실행되기 "
//				+ "전 Before 바로 이 메소드(beforeControllerPointCut)를 실행하고 "
//				+ "컨트롤러의 해당메소드(타겟메소드)로 가서 동작함.");
//	}
	
	// Around는 Object를 받는다
	@Around("controllerPointCut()")
	public Object aroundControllerPointCut(ProceedingJoinPoint pjp) throws Throwable {
		Object returnObj = null;
		// pjp.getThis() 클래스명
		// pjp.getSignature().getName() 메소드명
		// logger.info("▶Ctrl: "+ pjp.getThis()+ " " +pjp.getSignature().getName());
		logger.info("▶Ctrl:{} {}", pjp.getThis(), pjp.getSignature().getName());
		
		Object[] args = pjp.getArgs();
		for(int i=0; i < args.length; i++) {
			// logger.info("▶Ctrl args[" + i + "]: " + args[i]);
			logger.info("▶Ctrl args[{}]: {}", i, args[i]);
		}
		
		StopWatch stopwatch = new StopWatch();
		stopwatch.start();
		// 타겟메소드 실행
		returnObj = pjp.proceed(); // try~ 안하고 던진다.
		stopwatch.stop();
		
		// logger.info("▶Ctrl Return["+stopwatch.getTotalTimeMillis()+"]: " + returnObj);
		logger.info("▶Ctrl Return[{}]: {}", stopwatch.getTotalTimeMillis(), returnObj);
		return returnObj;
	
	}
			
	@Around("serviceImplPointCut()")
	public Object aroundserviceImplPointCut(ProceedingJoinPoint pjp) throws Throwable {
		
		Object returnObj = null;
		// logger.info("▶▶Srvc: "+ pjp.getThis()+ " " +pjp.getSignature().getName());
		logger.info("     ▶▶Srvc: "+ pjp.getThis()+" "+ pjp.getSignature().getName());
				
		Object[] args = pjp.getArgs();
		for(int i=0; i < args.length; i++) {
			// logger.info("▶▶args[" + i + "]: " + args[i]);
			logger.info("     ▶▶Srvc args["+i+"]: "+ args[i]);
		}
		StopWatch stopwatch = new StopWatch();
		stopwatch.start();
		// 타겟메소드 실행
		returnObj = pjp.proceed(); // try~ 안하고 던진다.
		stopwatch.stop();
		
		logger.info("     ▶▶Srvc Return["+stopwatch.getTotalTimeMillis()+"]: " + returnObj);
		
		return returnObj;
	}
	
	@Around("daoPointCut()")
	public Object aroundDaoPointCut(ProceedingJoinPoint pjp) throws Throwable {
		
		Object returnObj = null;
		// logger.info("▶▶▶Dao: "+ pjp.getThis()+ " "+pjp.getSignature().getName());
		logger.info("        ▶▶▶Dao: "+ pjp.getThis()+ " "+pjp.getSignature().getName());
		Object[] args = pjp.getArgs();
		for(int i=0; i < args.length; i++) {
			// logger.info("▶▶▶args[" + i + "]: " + args[i]);
			logger.info("        ▶▶▶Dao args["+i+"]: "+ args[i]);
		}
		StopWatch stopwatch = new StopWatch();
		stopwatch.start();
		// 타겟메소드 실행
		returnObj = pjp.proceed(); // try~ 안하고 던진다.
		stopwatch.stop();
		
		logger.info("        ▶▶▶DAO Return["+stopwatch.getTotalTimeMillis()+"]: " + returnObj);
		
		return returnObj;
	}	
}
