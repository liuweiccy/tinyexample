# Java8新特性简要概述
## Lambda表达式
## Stream
## Java8新增API
### Optional
从 Java 8 引入的一个很有趣的特性是 Optional  类。Optional 类主要解决的问题是臭名昭著的空指针异常（NullPointerException）。

本质上，这是一个包含有可选值的包装类，这意味着 Optional 类既可以含有对象也可以为空。

Java8之前：处理NullPointerException是这样的：
``` java
    public static String getGender(Student student)
    {
        if(null == student)
        {
            return "Unkown";
        }
        return student.getGender();
        
    }
```
这样的代码显得臃肿，不够直观，为了解决空指针异常同时精简代码，Java8引入了Optional类，处理空指针异常的方式是这样的。

``` java
    public static String getGender(Student student)
    {
       return Optional.ofNullable(student).map(u -> u.getGender()).orElse("Unkown");
        
    }
```

 可以看到，Optional类结合lambda表达式的使用能够让开发出的代码更简洁和优雅。

Optional类组成结构如下图：

![Optional](照片\Optional.png)

#### Optional创建
``` java
// 1、创建一个包装对象值为空的Optional对象
Optional<String> optStr = Optional.empty();
// 2、创建包装对象值非空的Optional对象
Optional<String> optStr1 = Optional.of("optional");
// 3、创建包装对象值允许为空的Optional对象
Optional<String> optStr2 = Optional.ofNullable(null);
```
#### Optional使用

##### get方法
``` java
    public T get() {
        if (value == null) {
            throw new NoSuchElementException("No value present");
        }
        return value;
    }
```
可以看到，get()方法主要用于返回包装对象的实际值，但是如果包装对象值为null，会抛出NoSuchElementException异常。

##### isPresent方法
``` java
    public boolean isPresent() {
        return value != null;
    }
```
可以看到，isPresent()方法用于判断包装对象的值是否非空。下面我们来看一段糟糕的代码：
``` java
    public static String getGender(Student student)
    {
        Optional<Student> stuOpt =  Optional.ofNullable(student);
        if(stuOpt.isPresent())
        {
            return stuOpt.get().getGender();
        }
        
        return "Unkown";
    }
```
这种用法不但没有减少null的防御性检查，而且增加了Optional包装的过程，违背了Optional设计的初衷，因此开发中要避免这种糟糕的使用

##### ifPresent方法
``` java
    public void ifPresent(Consumer<? super T> consumer) {
        if (value != null)
            consumer.accept(value);
    }
```
ifPresent()方法接受一个Consumer对象（消费函数），如果包装对象的值非空，运行Consumer对象的accept()方法。示例如下：
``` java
    public static void printName(Student student)
    {
        Optional.ofNullable(student).ifPresent(u ->  System.out.println("The student name is : " + u.getName()));
    }
```
上述示例用于打印学生姓名，由于ifPresent()方法内部做了null值检查，调用前无需担心NPE问题。

##### filter方法
``` java
    public Optional<T> filter(Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        if (!isPresent())
            return this;
        else
            return predicate.test(value) ? this : empty();
    }
```
filter()方法接受参数为Predicate对象，用于对Optional对象进行过滤，如果符合Predicate的条件，返回Optional对象本身，否则返回一个空的Optional对象。举例如下：
``` java
    public static void filterAge(Student student)
    {
        Optional.ofNullable(student).filter( u -> u.getAge() > 18).ifPresent(u ->  System.out.println("The student age is more than 18."));
    }
```
##### map方法
``` java
    public<U> Optional<U> map(Function<? super T, ? extends U> mapper) {
        Objects.requireNonNull(mapper);
        if (!isPresent())
            return empty();
        else {
            return Optional.ofNullable(mapper.apply(value));
        }
    }
```
map()方法的参数为Function（函数式接口）对象，map()方法将Optional中的包装对象用Function函数进行运算，并包装成新的Optional对象（包装对象的类型可能改变）。举例如下：
``` java
    public static Optional<Integer> getAge(Student student)
    {
        return Optional.ofNullable(student).map(u -> u.getAge()); 
    }
```
上述代码中，先用ofNullable()方法构造一个Optional<Student>对象，然后用map()计算学生的年龄，返回Optional<Integer>对象（如果student为null, 返回map()方法返回一个空的Optinal对象）。
##### flatMap方法
``` java
    public<U> Optional<U> flatMap(Function<? super T, Optional<U>> mapper) {
        Objects.requireNonNull(mapper);
        if (!isPresent())
            return empty();
        else {
            return Objects.requireNonNull(mapper.apply(value));
        }
    }
```
跟map()方法不同的是，入参Function函数的返回值类型为`Optional<U>`类型，而不是U类型，这样flatMap()能将一个二维的Optional对象映射成一个一维的对象。以3.5中示例功能为例，进行faltMap()改写如下：
``` java
    public static Optional<Integer> getAge(Student student)
    {
        return Optional.ofNullable(student).flatMap(u -> Optional.ofNullable(u.getAge())); 
    }
```
##### orElse方法
``` java
    public T orElse(T other) {
        return value != null ? value : other;
    }
```    
orElse()方法功能比较简单，即如果包装对象值非空，返回包装对象值，否则返回入参other的值（默认值）。如第一章（简介）中提到的代码：
``` java
    public static String getGender(Student student)
    {
       return Optional.ofNullable(student).map(u -> u.getGender()).orElse("Unkown");
        
    }
```
##### orElseGet方法
``` java
    public T orElseGet(Supplier<? extends T> other) {
        return value != null ? value : other.get();
    }
```
orElseGet()方法与orElse()方法类似，区别在于orElseGet()方法的入参为一个Supplier对象，用Supplier对象的get()方法的返回值作为默认值。如：
``` java
    public static String getGender(Student student)
    {
        return Optional.ofNullable(student).map(u -> u.getGender()).orElseGet(() -> "Unkown");      
    }
```
##### orElseThrow方法
``` java
    public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        if (value != null) {
            return value;
        } else {
            throw exceptionSupplier.get();
        }
    }
```
orElseThrow()方法其实与orElseGet()方法非常相似了，入参都是Supplier对象，只不过orElseThrow()的Supplier对象必须返回一个Throwable异常，并在orElseThrow()中将异常抛出：
``` java
    public static String getGender1(Student student)
    {
        return Optional.ofNullable(student).map(u -> u.getGender()).orElseThrow(() -> new RuntimeException("Unkown"));      
    }
```
orElseThrow()方法适用于包装对象值为空时需要抛出特定异常的场景。
#### 注意事项
使用Optional开发时要注意正确使用Optional的“姿势”，特别注意不要使用3.2节提到的错误示范，谨慎使用isPresent()和get()方法，尽量多使用map()、filter()、orElse()等方法来发挥Optional的作用。

### Date/Time
### Base64
Base64是一种用64个字符来表示任意二进制数据的方式。

对于二进制文件如图片、exe、音频、视频等，包含很多无法显示和打印的字符，如果希望能够通过记事本这样的文本处理软件处理二进制数据，就需要一个二进制转字符串的转换方法。

Base64是一种非常常用的二进制编解码方案。编解码方法简单且公开，并不具有加密解密的效用。只作为一种二进制数据的文本存储格式。

Base64的64个字符，每个字符代表一种编码，共64种编码。

   > A-Z , a-z , 0-9 , + , /


Java8 为开发者提供了 java.util.Base64 的工具类，并提供一套静态方法获取三种Base64编解码器：

    1）Basic编码

    2）URL编码

    3）MIME编码

``` java
try {
     String encoded = Base64.getEncoder().encodeToString("Will Smith = 威尔·史密斯".getBytes("UTF-8"));
     System.out.println(encoded);
     String decoded = new String(Base64.getDecoder().decode(encoded));
     System.out.println(decoded);
} catch (UnsupportedEncodingException e) {
     e.printStackTrace();
}
```    

## 其他
### LongAddr
### CompletableFuture
### JVM堆分布的优化
在Java8中，PermGen空间被移除了，取而代之的是Metaspace。JVM选项-XX:PermSize与-XX:MaxPermSize分别被-XX:MetaSpaceSize与-XX:MaxMetaspaceSize所代替。
Java8中把存放元数据中的永生代内存从堆内存中移到了本地内存(native memory)中，Java8中JVM堆内存结构就变成了以下所示：

![JVM内存分布](照片\JVM内存分布.png)



这样永生代内存就不再占用堆内存。它能够通过自己主动增长来避免JDK7以及前期版本号中常见的永生代内存错误(java.lang.OutOfMemoryError: PermGen)。随着Java8的到来，JVM不再有PermGen。但类的元数据信息（metadata）还在，只不过不再是存储在连续的堆空间上，而是移动到叫做“Metaspace”的本地内存（Native memory）中。
类的元数据信息转移到Metaspace的原因是PermGen很难调整。PermGen中类的元数据信息在每次FullGC的时候可能会被收集，但成绩很难令人满意。而且应该为PermGen分配多大的空间很难确定，因为PermSize的大小依赖于很多因素，比如JVM加载的class的总数，常量池的大小，方法的大小等。 