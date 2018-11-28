# spring-boot learn notes
> start a new journey

## 1.lesson1
### 1.1 初始化框架
### 1.2 第一个web应用

## 2.lesson2
### 2.1 maven repository--阿里镜像仓库配置
- 第一步:修改maven根目录下的conf文件夹中的setting.xml文件，内容如下：
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
- 第二步: pom.xml文件里添加`repositories`配置
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
### 2.2 多数据源配置
- 1.在application.properties配置
- 2.在启动程序配置

### 2.3 配置文件加密
> [jasypt方案](https://github.com/ulisesbocchio/jasypt-spring-boot)

1.maven依赖
```xml
<dependency>
    <groupId>com.github.ulisesbocchio</groupId>
    <artifactId>jasypt-spring-boot-starter</artifactId>
    <version>1.16</version>
</dependency>
```
2.`application.properties` 增加配置
```
jasypt.encryptor.password=encrypt-code
```

3.调用JAVA API 生成密文
```java
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PigAdminApplication.class)
public class PigAdminApplicationTest {
    @Autowired
    private StringEncryptor stringEncryptor;

    @Test
    public void testEnvironmentProperties() {
        System.out.println(stringEncryptor.encrypt("lengleng"));
    }

}
```

4.配置文件中使用密文
```
spring.datasource.jdbcUrl=ENC(加密的url)
spring.datasource.username=ENC(加密的username)
spring.datasource.password=ENC(加密的password)
```

5.安全防范
- a.调用API生成的密钥中真实的username，password和jdbcUrl不能在生成代码中泄露
- b.配置文件中的`jasypt.encryptor.password`密钥不能直接放在`application.properties`中，**要在项目启动时命令中添加**
```jshelllanguage
java -jar -Djasypt.encryptor.password=encrypt-code projectName-0.0.1-SNAPSHOT.war
```
6.延伸--其他非对称等高级配置参考

| Key        | Required   |  Content  |
| --------   | -----:  | :----:  |
| jasypt.encryptor.password  | True |   根密码     |
| jasypt.encryptor.algorithm |   False   | PBEWithMD5AndDES |
| jasypt.encryptor.keyObtentionIterations| False |  1000  |
| jasypt.encryptor.poolSize  |    False   |  1  |
| jasypt.encryptor.providerName | False  |  SunJCE |
| jasypt.encryptor.saltGeneratorClassname| False |  org.jasypt.salt.RandomSaltGenerator  |
| jasypt.encryptor.stringOutputType | False |  base64  |
| jasypt.encryptor.proxyPropertySources | False |  false  |

### 2.4 c3p0配置数据库连接
1. `application.properties` 添加配置
```
c3p0.jdbcUrl=jdbc:mysql://127.0.0.1:3307/shopxx?useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false
c3p0.user=root
c3p0.password=root
c3p0.driverClass=com.mysql.jdbc.Driver
```

2.`pom.xml`添加依赖
```xml
        <dependency>
            <groupId>c3p0</groupId>
            <artifactId>c3p0</artifactId>
            <version>0.9.1.2</version>
        </dependency>
```

3.spring-boot启动项Config创建连接
```
    @Bean(name = "localSource")
    @ConfigurationProperties(prefix = "c3p0")
    public DataSource mysql127Source(){
        return DataSourceBuilder.create().type(ComboPooledDataSource.class).build();
    }

    @Bean(name = "localTemplate")
    public JdbcTemplate mysql127Template(@Qualifier("localSource") DataSource source){
        return new JdbcTemplate(source);
    }
```
### 2.5 mysql避免重复插入
>会有一个详细的、逐步叠加的工具util包
- 2.5.1 读取简单的Excel(1997-2003)
```
ExcelUtil.readExcelAsModel(args...)

```
- 2.5.2 避免重复插入sql语句
```sql
insert into se_client_power
(host_name,client_id,client_name,data_time,charge_power,discharge_power)
select ?,?,?,?,?,? from dual where not exists(
select host_name,client_id,client_name,data_time,charge_power,discharge_power from se_client_power where client_id=? and data_time=?);

```

- 2.5.3 service方法
```Java
List<Object[]> params = new ArrayList<>();
        for (ClientPowerModel cpModel : cpModelList){
            params.add(new Object[]{
                    cpModel.getHostName(),
                    cpModel.getClientId(),
                    cpModel.getClientName(),
                    cpModel.getDataTime(),
                    cpModel.getChargePower(),
                    cpModel.getDiscChargePower(),
                    cpModel.getClientId(),cpModel.getDataTime()//最后两个问号不能漏掉
            });
        }
        jdbcTemplate.batchUpdate(sql,params);
``` 

- 2.5.4 测试方法
```java
@Scheduled(fixedRate = 10000L)
    public void execTask(){
        String path = "";
        try {
            path   = ResourceUtils.getFile("classpath:static/file/201117.xls").getPath();
            System.out.println(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<ClientPowerModel> list = ExcelUtil.readExcelAsModel(path,0,0,1);
        if(list.size() >0){
            commonService.avoidInsert(list);
        }
    }
```

- 2.5.5 数据库和测试文件准备
> 所有配置文件会自动加载到target对应资源目录下

### 2.6 获取classpath的三种方式


## 3.lesson3
