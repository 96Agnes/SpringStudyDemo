## 资料
 [Spring 文档](https://spring.io/guides)  
 [Spring Web](https://spring.io/guides/gs/serving-web-content/)  
 [Github OAuth Documents](https://developer.github.com/apps/)
 
## 目标
 [es 社区](https://elasticsearch.cn/explore/)  
 [Bootstrap](http://www.bootcss.com/)
 
## 工具
 idea + git

## Knowledge    
 Bootstrap  
 H2     
 MyBatis    
 Flyway Migration   
 
## 脚本
```sql
create table USER
(
	ID INTEGER auto_increment,
	ACCOUNT_ID VARCHAR(100),
	NAME VARCHAR(50),
	TOKEN CHAR(36),
	GMT_CREATE BIGINT,
	GMT_MODIFIED BIGINT,
	constraint USER_PK
	primary key (ID)
);
```

## 命令
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate



