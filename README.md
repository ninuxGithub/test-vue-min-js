## test-vue-min-js
这是采用vue.js 实现前天页面的js 逻辑


## aop 对controller 的返回结果进行定制

@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD, ElementType.METHOD})
public @interface ResultBeanAnnotation {
}


对带有@ResultBeanAnnotation的Controller 方法进行aop的拦截
返回的结果必选是 ResultBean<T>

