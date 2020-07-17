# CustomAnnoationProcessor
一个根据自定义注解生成文档的插件


**一、用途:**

用注解标注需要说明的变量，编译后自动在当前项目根目录生成文档。



**二、使用方法**

1. 添加依赖
   在app->build.gradle下：

```java
dependencies {
           ... 
           api project(':processor') annotationProcessor project(':processor') 
           ... 
       }
```

2. 注解变量
   在需要标注的变量添加注解 DescAnnotation
 

```java
	   @DescAnnotation(title = "test_str_one", desc = "我是第一个变量的描述")
	   private String testStrOne = "test_str_one"; 
	   @DescAnnotation(title = "test_str_two", desc = "我是第二个变量的描述")
	   private String testStrTwo = "test_str_two";
```

3. 执行编译

	build->rebuild project

1. 查看文档

	在项目根目录会生成 注解生成文档.txt，里边儿就是注解字段内容。

![在这里插入图片描述](https://user-gold-cdn.xitu.io/2020/7/17/1735c44d91071f0a?w=586&h=183&f=png&s=27909)


**三、todo**

- 生成文档目录自定义配置
- 文档内容格式优化
- 上传到bintray.com，方便依赖
- ...

