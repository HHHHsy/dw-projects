# 网页展活动学习平台

一个面向初中生的信息技术课程学习平台，帮助学生学习HTML、CSS和Dreamweaver网页制作。

## 项目特色

- 🎨 色彩明快、结构清晰，符合初中生认知特点
- 💻 前端使用HTML5 + CSS3 + JavaScript实现响应式布局
- ☕ 后端采用Java + Spring Boot框架
- 🗄️ 使用MySQL数据库存储数据

## 功能模块

### 学生端
- 🏠 **首页** - 平台介绍和课程特色展示
- 📚 **课程学习** - 课程导学、知识讲解、操作演示
- 🛠️ **工具教程** - Dreamweaver等工具使用教程
- 💻 **实践任务** - 递进式任务练习（4个任务）
- 🏆 **作品展示** - 优秀作品和所有作品展示
- 📝 **在线测试** - 章节测试和综合测试
- 📥 **资源下载** - 教学课件、视频教程、素材资源

### 教师端
- 👨‍🏫 **班级管理** - 创建班级、导入学生名单、分组管理
- 📊 **进度监控** - 查看学生学习轨迹、任务完成率、测试成绩统计
- 📁 **资源管理** - 上传/编辑/删除教学资源
- 📝 **评价反馈** - 在线批改作品、录入成绩、发布评语、批量导出成绩单

## 项目结构

```
dw-projects/
├── frontend/                 # 前端目录
│   ├── index.html           # 首页
│   ├── css/
│   │   └── style.css        # 样式文件
│   ├── js/
│   │   └── main.js          # JavaScript文件
│   ├── images/              # 图片资源
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
                └── schema.sql         # 数据库脚本
```

## 快速开始

### 前置要求

- JDK 17+
- Maven 3.6+
- MySQL 8.0+

### 数据库配置

1. 创建数据库：
```sql
CREATE DATABASE web_exhibition CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

2. 修改 `backend/src/main/resources/application.yml` 中的数据库连接信息：
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/web_exhibition?...
    username: your_username
    password: your_password
```

### 启动后端

```bash
cd backend
mvn spring-boot:run
```

后端服务将在 http://localhost:8080 启动

### 启动前端

直接在浏览器中打开 `frontend/index.html` 文件，或者使用任何HTTP服务器：

```bash
cd frontend
python -m http.server 3000
```

然后访问 http://localhost:3000

## API接口说明

### 用户管理
- `GET /api/users` - 获取所有用户
- `POST /api/users` - 创建用户
- `POST /api/users/login` - 用户登录

### 班级管理
- `GET /api/classes` - 获取所有班级
- `POST /api/classes` - 创建班级

### 任务管理
- `GET /api/tasks` - 获取所有任务
- `POST /api/tasks` - 创建任务

### 作品管理
- `GET /api/works` - 获取所有作品
- `GET /api/works/excellent` - 获取优秀作品
- `POST /api/works` - 提交作品
- `PUT /api/works/{id}/grade` - 批改作品

### 资源管理
- `GET /api/resources` - 获取所有资源
- `POST /api/resources` - 上传资源

### 测试管理
- `GET /api/quizzes` - 获取所有测试
- `POST /api/quizzes/results` - 提交测试结果

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
- Java 17
- Spring Boot 3.2.0
- Spring Data JPA
- MySQL 8.0

## 许可证

MIT License
