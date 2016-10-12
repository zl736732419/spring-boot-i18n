### 国际化配置
#### 在页面上使用
    springboot默认就支持国际化配置，只需要添加国际化配置文件即可
    格式为messages.properties(默认)，
    messages_zh_CN.properties(中文)
    messages_en_US.properties(英文)
    将其放到classpath:/resources下即可
    需要注意的是在页面上引用时，需要通过<label th:text="#{key}"></label>
    的方式引用
    springboot内部是通过MessageSourceAutoConfiguration来配置的
    我们可以通过在application.properties中配置属性改变国际化配置的一些信息
>
>       #设置国际化配置文件存放在classpath:/i18n目录下
>        spring.messages.basename=i18n/messages
>       #设置加载资源的缓存失效时间，-1表示永久有效，默认为-1
>        spring.messages.cache-seconds=3600
>        #设定message bundles编码方式，默认为UTF-8
>        #spring.messages.encoding=UTF-8
####在代码中使用
    在代码中直接通过@Autowired MessageSource 并通过它来获取需要的国际化字段
    当然需要使用系统当前的locale对象，可以通过：LocaleContextHolder.getLocale();
    或者RequestContextUtils.getLocale(request);获取
    
### 国际化LocaleResolver
    springboot默认采用AcceptHeaderLocaleResolver来解析国际化信息，但是这个Resolver是根据浏览器所在的
    操作系统的内核区域设置来决定语言的，一般使用的少
    采用会话Resolver，SessionLocaleResolver，在用户请求的当前会话过程中有效，用户下一次请求时恢复到原来的
    语言设置
    还包含其他Resolver
    CookieLocaleResolver
    FixedLocaleResolver 这个Resolver不允许用户修改语言
    
    除了使用LocaleResolver.setLocale()来设置语言区域之外，
    还可以使用拦截器修改语言区域：LocaleChangeInterceptor
    但是注意这种方式可以和session cookie Resolver一起使用，但是不能和FixedLocaleResolver一起使用，抛异常，我的哥！