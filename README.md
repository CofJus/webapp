### Database开发文档  

----
<font size=3>  

#### 一、开发环境及工具  

开发环境：Windows 10  
JDK版本：jdk1.8.0_231  
MySql版本：mysql-8.0.19-winx64  
驱动版本：8.0.19(自行下载，放在lib目录下)  
开发工具：IDEA 2020.1  
其他工具：Navicat Premium、VSCode、Visio  

#### 二、功能描述  

通过MySql+JDBC实现学生注册、健康信息登记和实验室签到及签退  

#### 三、模块说明  

持久层采用DAO模式
DAO：数据访问层，在DaoImpl中实现了三个接口  
VO：值对象，与数据库表的映射，用于持久层与业务层之间的数据交换  

#### 四、类与接口说明

CreateDb：由于无法把数据库部署到远程主机，利用该类实现数据库同步。打开项目后首先运行main方法，此方法将在本地mysql数据库中创建表。运行前确保驱动已下载到正确的位置且本地mysql数据库用户root的密码为123456。需要更改表结构时，修改字符串常量即可。  
修改表结构前，打开mysql，输入  
`DROP DATABASE web;`

DbConnect：用于数据库连接和关闭。  
&emsp;&emsp;DbConnect：构造方法，连接数据库  
&emsp;&emsp;getConnection：获取数据库连接  
&emsp;&emsp;closeDb：断开数据库连接  

Md5Util：用于MD5加密密码  
&emsp;&emsp;stringToMd5：MD5加密，不加盐  
&emsp;&emsp;doubleSalt：加两次盐  

UserDaoImpl：实现了UserDao接口。  
&emsp;&emsp;insert：新用户注册时插入用户填写的基本信息  
&emsp;&emsp;示例：  
&emsp;&emsp;`User user = new User();`  
&emsp;&emsp;`user.setId("1806010633");`  
&emsp;&emsp;`user.setName("季锐");`  
&emsp;&emsp;`...`  
&emsp;&emsp;`UserDao userDao=getUserDaoInstance();`  
&emsp;&emsp;`userDao.insert(user);`  
&emsp;&emsp;updatePassword：修改密码  
&emsp;&emsp;queryById：根据id查找学生信息（不返回密码）  

InfoDaoImpl：实现了InfoDao接口。  
&emsp;&emsp;insert：插入学生健康信息，仅在第一次登记时调用。  
&emsp;&emsp;updateById：更新学生健康信息，仅在健康状况发生变化时调用。  
&emsp;&emsp;queryById：通过id查找学生健康信息。返回类型为Info。  
&emsp;&emsp;queryAll：获取所有学生的健康信息。  
&emsp;&emsp;queryByDanger：获取健康状况有异常的学生的信息，可获取学号后通过UserDao.queryById获取个人信息。  

SignDaoImpl：实现了SignDao接口。  
&emsp;&emsp;updateById：根据id签到。可根据id制作二维码，扫描二维码并解析后调用。  
&emsp;&emsp;queryById：根据学号查询某学生的签到情况。  
&emsp;&emsp;queryByName：根据名字，同上。  
&emsp;&emsp;queryBySigned：获取所有未签到的学生的信息。  

DaoFactory：DAO工厂方法，获取DAO对象。  
示例：  
`UserDao userDao = getUserDaoInstance();`  

#### 五、ER图  

![ER](https://s1.ax1x.com/2020/05/07/YmE7md.png)

#### 六、bug修复记录  

</font>  
