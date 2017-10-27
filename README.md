## test-vue-min-js
这是采用vue.js 实现前天页面的js 逻辑


## aop 对controller 的返回结果进行定制
```java
	@Retention(RetentionPolicy.RUNTIME)
	@Target(value = {ElementType.FIELD, ElementType.METHOD})
	public @interface ResultBeanAnnotation {
	}
```


对带有@ResultBeanAnnotation的Controller 方法进行aop的拦截
返回的结果必选是 ResultBean<T>



## ajax发送json后台接受

后台java处理前台发送过来的数据 注意使用的是@requestBody

```java
		@ResponseBody
		@RequestMapping(value = "/ajaxTest", produces= {"application/json;charset=utf-8"})
		@ResultBeanAnnotation
		public ResultBean<Map<String, Object>> ajaxTest(@RequestBody String username) {
			System.out.println("from requestBody"+username);
			List<User> users = userRepository.findAll();
			Map<String, Object> map = new HashMap<>();
			map.put("user", users);
			map.put("username", username);
			return new ResultBean<Map<String, Object>>(map);
		}
```
前台发送的数据类型是json
 
```javascript
		$.ajax({
			url:'ajaxTest',
			data: JSON.stringify('{"username":"Jackson"}'),
			type:'post',
			dataType:'text',
			contentType:'applicatoin/json',
			success:function(data){
				that.dataInfo = data;
			}
		});
```


## volatile 的使用
	java内存模型中的可见性， 原子性， 有序性。
	
	可见性：是指线程直接的可见性， 一个线程修改的状态对另外一个线程是可见的。在 Java 中volatile、synchronized 和 final 实现可见性。
	
	原子性：世界上最小的单位， 具有不可分割性。例如 a++就不是一个原子操作。（atomicInteger 可以保证原子性）
	
	有序性：保证线程之间操作的有序性。
	
	指令重排序：是指CPU采用了允许将多条指令不按程序规定的顺序分开发送给各相应电路单元处理
	

	
	
	

	volatile的官方定义：java 编程语言允许线程访问共享变量， 为了确保变量能够被准确和一致的更新， 线程应该确保通过排它锁单独获取这个变量。
	在某种情况下， 如果一个字段被声明为Volatile , java线程内存模型确保所有的线程看到这个变量的值是一致的。
	
	为什么要使用volatile?
	volatile要比synchronize使用和执行的成本更低， 因为他不会引起线程上下文的切换和调度。
	
	
	
	
	volatile实现的原理：
	当把变量声明为volatile类型后，编译器与运行时都会注意到这个变量是共享的，因此不会将该变量上的操作与其他内存操作一起重排序，
	而声明变量是 volatile 的，JVM 保证了每次读变量都从内存中读，跳过 CPU cache 这一步
	
	
	当一个变量定义为 volatile 之后，将具备两种特性：

　　1.保证此变量对所有的线程的可见性，这里的“可见性”，如本文开头所述，当一个线程修改了这个变量的值，volatile 保证了新值能立即同步到主内存，以及每次使用前立即从主内存刷新。但普通变量做不到这点，普通变量的值在线程间传递均需要通过主内存（详见：Java内存模型）来完成。

　　2.禁止指令重排序优化。有volatile修饰的变量，赋值后多执行了一个“load addl $0x0, (%esp)”操作，这个操作相当于一个内存屏障（指令重排序时不能把后面的指令重排序到内存屏障之前的位置），只有一个CPU访问内存时，并不需要内存屏障；（什么是指令重排序：是指CPU采用了允许将多条指令不按程序规定的顺序分开发送给各相应电路单元处理）。


### springBean 懒加载
结合注解@Lazy , 并且采用BeanFactory来获取bean,并且调用的时候创建
在debug的时候，看到的是scope为singleton的会在启动服务器是实例化，而prototype是在请求的时候再实例化 


### aop 的执行顺序
	around--->before--after-->(如果有异常)例外通知-->最终通知
	
	多个aop:先进入的后出，类似栈【around-before-around2-before2-after2-return2-after-return】
	
	如果有例外：
	ControllerAop - 环绕通知
	ControllerAop - 方法签名：ResultBean com.example.demo.controller.IndexController.ajaxTest(String)
	ControllerAop - "{\"username\":\"Jackson\"}"
	ControllerAop - 前置通知
	ControllerAop2 - 环绕通知
	ControllerAop2 - 方法签名：ResultBean com.example.demo.controller.IndexController.ajaxTest(String)
	ControllerAop2 - "{\"username\":\"Jackson\"}"
	ControllerAop2 - 前置通知
	2017-10-27 13:18:30 INFO  o.h.h.i.QueryTranslatorFactoryInitiator - HHH000397: Using ASTQueryTranslatorFactory
	ControllerAop2 - 最终通知
	ControllerAop2 - 例外通知/ by zero
	2017-10-27 13:18:30 ERROR com.example.demo.aop.ControllerAop - ResultBean ajaxTest(String)error:/ by zero
	ControllerAop - controllerPointCut 拦截了 ajaxTest方法, 消耗的时间为：9 ms
	ControllerAop - 最终通知
	ControllerAop - 后置通知
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

