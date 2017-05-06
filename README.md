# DateFormatUtils
## 简介
这个库的主要作用是对时间进行进行处理，依赖于Gson和Joda-Android，需要配合这两个库才能正确使用。
如果对这个库的具体实现有兴趣的话，可以看博主的博客，里面有对时间的处理作了详细的介绍。博客地址：
[Android(Java) 日期和时间处理完全解析(上)](http://tangpj.com/2017/05/02/dateformat/)
[Android(Java) 日期和时间处理完全解析(下)](http://tangpj.com/2017/05/02/dateformat2/)

## 功能
1. 使Gson能够序列化和反序列化DateTime类。
2. 对DateTime进行格式化，转换成更容易让人更容易理解的形式。

## 用法
首先想项目的build.gradle文件引入MAVEN库：

```  
allprojects {  
		repositories {  
			...  
			maven { url 'https://jitpack.io' }  
		}  
	}  
  
```

然后在需要使用该工具类的Module下引入依赖：
```  
dependencies {  
			compile 'com.github.DobbyTang:DateFormatUtils:1.0.0'  
	}  
  
```


### 解析ISO 8061标准的时间
测试例子：
```  
String ISO8061_STR = "\"2017-05-05T06:44:16Z\"";  
```

测试代码:
```  
Gson gson = new GsonBuilder()  
		.registerTypeAdapter(DateTime.class, new DateTimeTypeAdapter())  
		.create();  
DateTime isoDateTime = gson.fromJson(ISO8061_STR,DateTime.class);  
System.out.println(DateFormatUtils.format(isoDateTime));  
```

### 解析自定义格式的时间
我们假设现在的时间是：2017年5月5日 16:46:46
测试例子：
```  
private static final String WEIBO_STR1 = "\"Fri May 05 16:47:19 +0800 2017\"";  
private static final String WEIBO_STR2 = "\"Fri May 05 16:40:22 +0800 2017\"";  
private static final String WEIBO_STR3 = "\"Fri May 05 04:22:19 +0800 2017\"";  
private static final String WEIBO_STR4 = "\"Thu May 04 12:00:19 +0800 2017\"";  
private static final String WEIBO_STR5 = "\"Sun Apr 16 06:00:19 +0800 2017\"";  
private static final String WEIBO_STR6 = "\"Thu Nov 05 23:17:19 +0800 2015\"";  
  
```

测试代码：
```  
DateTimeFormatter weiboFormat =  DateTimeFormat.forPattern("EEE MMM dd HH:mm:ss Z yyyy")  
		.withLocale(Locale.US)  
                 .withZone(DateTimeZone.forID("+08:00"));  
Gson gson1 = new GsonBuilder()  
		.registerTypeAdapter(DateTime.class  
				,new DateTimeTypeAdapter(weiboFormat))  
		.create();  
  
System.out.println(DateFormatUtils.format(gson1.fromJson(WEIBO_STR1,DateTime.class)));  
System.out.println(DateFormatUtils.format(gson1.fromJson(WEIBO_STR2,DateTime.class)));  
System.out.println(DateFormatUtils.format(gson1.fromJson(WEIBO_STR3,DateTime.class)));  
System.out.println(DateFormatUtils.format(gson1.fromJson(WEIBO_STR4,DateTime.class)));  
System.out.println(DateFormatUtils.format(gson1.fromJson(WEIBO_STR5,DateTime.class)));  
System.out.println(DateFormatUtils.format(gson1.fromJson(WEIBO_STR6,DateTime.class)));  
```

输出结果：
33秒前
7分钟前
今天 04:22
昨天 12:00
04月16日 06:00
2015年11月05日 23:17

这就是我们的时间处理工具的处理结果了，可以看出这和一些社交平台上面的时间显示方式差不多。

# License
```  
Copyright  [2017]  [tangpj]  
  
  
Licensed under the Apache License, Version 2.0 (the "License");  
you may not use this file except in compliance with the License.  
You may obtain a copy of the License at  
  
	http://www.apache.org/licenses/LICENSE-2.0  
  
Unless required by applicable law or agreed to in writing, software  
distributed under the License is distributed on an "AS IS" BASIS,  
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  
See the License for the specific language governing permissions and  
limitations under the License.  
  
```







