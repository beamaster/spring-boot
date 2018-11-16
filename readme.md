# spring-boot learn notes
> start a new journey

## lesson1
+ 初始化框架
+ 第一个web应用

## lesson2
+ maven repository--阿里镜像仓库配置
第一步:修改maven根目录下的conf文件夹中的setting.xml文件，内容如下：
```xml
<mirrors>
    <mirror>
      <id>alimaven</id>
      <name>aliyun maven</name>
      <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
      <mirrorOf>central</mirrorOf>        
    </mirror>
  </mirrors>
```
第二步: pom.xml文件里添加`repositories`配置
```xml
<repositories>  
        <repository>  
            <id>alimaven</id>  
            <name>aliyun maven</name>  
            <url>http://maven.aliyun.com/nexus/content/groups/public/</url>  
            <releases>  
                <enabled>true</enabled>  
            </releases>  
            <snapshots>  
                <enabled>false</enabled>  
            </snapshots>  
        </repository>  
</repositories>  
```
+ 多数据源配置
- 1.在application.properties配置
- 2.在启动程序配置
