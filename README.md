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

