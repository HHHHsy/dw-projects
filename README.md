## 项目结构

```
dw-projects/
├── frontend/                 # 前端目录（独立运行版本）
│   ├── index.html          # 首页
│   ├── css/
│   │   └── style.css        # 样式文件
│   ├── js/
│   │   └── main.js          # JavaScript文件
│   └── pages/               # 页面文件
│       ├── course.html      # 课程学习
│       ├── tools.html       # 工具教程
│       ├── tasks.html       # 实践任务
│       ├── works.html       # 作品展示
│       ├── quiz.html        # 在线测试
│       ├── resources.html   # 资源下载
│       └── teacher.html     # 教师专区
│
└── backend/                  # 后端目录
    ├── pom.xml              # Maven配置
    └── src/
        └── main/
            ├── java/com/dwproject/
            │   ├── WebExhibitionApplication.java    # 主启动类
            │   ├── entity/       # 实体类
            │   │   ├── User.java
            │   │   ├── ClassInfo.java
            │   │   ├── Task.java
            │   │   ├── Work.java
            │   │   ├── Resource.java
            │   │   ├── Quiz.java
            │   │   └── QuizResult.java
            │   ├── repository/   # 数据访问层
            │   ├── controller/   # 控制器层
            │   └── config/       # 配置类
            └── resources/
                ├── application.yml    # 应用配置
                ├── schema.sql         # 数据库脚本
                └── static/           # 静态资源（与前端目录结构相同）
                    ├── index.html
                    ├── css/style.css
                    ├── js/main.js
                    ├── js/loader.js
                    ├── components/header.html
                    └── pages/*.html
```

## 环境要求

### 必须安装的软件

#### 1. JDK（Java Development Kit）
- **版本要求**：JDK 1.8（Java 8）或更高版本
- **推荐版本**：JDK 8、JDK 11、JDK 17
- **下载地址**：https://adoptium.net/temurin/releases/
- **验证安装**：打开命令行，输入 `java -version`

#### 2. Maven
- **版本要求**：Maven 3.6+
- **下载地址**：https://maven.apache.org/download.cgi
- **验证安装**：打开命令行，输入 `mvn -version`
- **配置镜像**：Maven已配置阿里云镜像（国内加速）

#### 3. MySQL数据库
- **版本要求**：MySQL 8.0+
- **下载地址**：https://dev.mysql.com/downloads/mysql/
- **验证安装**：打开命令行，输入 `mysql --version`

#### 4. Git（可选）
- **下载地址**：https://git-scm.com/download/win

## 数据库配置

### 1. 启动MySQL服务

**Windows系统**：
- 打开"服务"应用（Win + R，输入`services.msc`）
- 找到"MySQL"服务
- 点击"启动"

**或使用命令行**：
```bash
net start mysql
```

### 2. 创建数据库

使用MySQL命令行或图形化工具（如Navicat、DBeaver）执行：

```sql
CREATE DATABASE IF NOT EXISTS web_exhibition 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;
```

### 3. 修改数据库连接配置

编辑 `backend/src/main/resources/application.yml`：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/web_exhibition?createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai
    username: root           # 修改为你的MySQL用户名
    password: hrxcy31.       # 修改为你的MySQL密码
    driver-class-name: com.mysql.cj.jdbc.Driver
```

**注意**：
- 如果MySQL没有密码，将 `password:` 后面留空
- 确保 `username` 与你的MySQL用户名一致（默认通常是 `root`）

## 项目启动

### 方式一：使用Maven启动后端（推荐）

#### 步骤1：进入后端目录
```bash
cd backend
```

#### 步骤2：清理并编译项目（首次运行或更新代码后）
```bash
mvn clean compile
```

#### 步骤3：打包项目
```bash
mvn package -DskipTests
```

#### 步骤4：启动应用
```bash
mvn spring-boot:run
```

#### 步骤5：验证启动成功

看到以下输出表示启动成功：
```
========================================
网页展活动学习平台启动成功！
访问地址: http://localhost:8088
========================================
```

### 方式二：直接运行JAR包

```bash
cd backend
mvn clean package -DskipTests
java -jar target/web-exhibition-1.0.0.jar
```

### 方式三：使用IDE启动

#### IntelliJ IDEA / WebStorm / VSCode
1. 打开 `backend` 目录作为项目
2. 等待Maven自动下载依赖
3. 找到 `WebExhibitionApplication.java`
4. 右键点击，选择"Run 'WebExhibitionApplication'"
5. 或点击IDE右上角的绿色运行按钮

#### Eclipse
1. File → Import → Maven → Existing Maven Projects
2. 选择 `backend` 目录
3. 右键点击项目 → Run As → Spring Boot App

## 访问项目

### 后端服务（带API接口）
启动后端后访问：
- **首页**：http://localhost:8088/index.html
- **课程学习**：http://localhost:8088/pages/course.html
- **其他页面**：http://localhost:8088/pages/*.html

### 前端独立版本（无API功能）
如果仅查看前端页面，不使用后端：
1. 进入 `frontend` 目录
2. 双击 `index.html` 用浏览器打开
3. 或使用Python服务器：
```bash
cd frontend
python -m http.server 3000
# 然后访问 http://localhost:3000
```

### API接口地址
- **基础URL**：http://localhost:8088/api
- **用户相关**：/api/users
- **班级相关**：/api/classes
- **任务相关**：/api/tasks
- **作品相关**：/api/works
- **资源相关**：/api/resources
- **测试相关**：/api/quizzes

## 常见问题

### 1. Maven下载依赖很慢
检查网络连接，或等待阿里云镜像同步完成

### 2. MySQL连接失败
- 确认MySQL服务已启动
- 检查用户名密码是否正确
- 确认3306端口未被占用

### 3. 端口8088被占用
修改 `application.yml` 中的端口：
```yaml
server:
  port: 8089  # 改为你想要的端口
```

### 4. 前端页面样式不生效
- 确保使用Maven启动后端
- 静态资源从 `backend/src/main/resources/static/` 提供
- 强制刷新浏览器（Ctrl + Shift + R）

### 5. 页面顶部有空白间距
- 检查HTML文件开头是否有BOM字符
- 确保CSS中 `body { margin: 0; padding: 0; }`
- 强制刷新浏览器（Ctrl + Shift + R）

## 项目依赖详情

### 后端依赖（pom.xml）

| 依赖 | 版本 | 说明 |
|------|------|------|
| spring-boot-starter-web | 2.7.18 | Web开发核心 |
| spring-boot-starter-data-jpa | 2.7.18 | 数据持久化 |
| spring-boot-starter-validation | 2.7.18 | 数据校验 |
| mysql-connector-java | 8.0.33 | MySQL驱动 |
| spring-boot-starter-test | 2.7.18 | 测试框架 |

### 前端依赖
无外部前端依赖，纯原生HTML5 + CSS3 + JavaScript

## 实践任务

1. **任务一：创建个人主页** - 使用HTML基本结构创建网页
2. **任务二：插入图片与超链接** - 添加图片和链接
3. **任务三：使用CSS美化页面** - 学习CSS样式
4. **任务四：完成"网页展活动"主题作品** - 综合运用所学知识

## 技术栈

### 前端
- HTML5
- CSS3
- JavaScript (ES6+)
- 响应式布局

### 后端
- Java 1.8
- Spring Boot 2.7.18
- Spring Data JPA
- MySQL 8.0

## 许可证

MIT License
