/*
 Navicat Premium Data Transfer

 Source Server         : 阿里云-47.107.127.70
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : 47.107.127.70:3306
 Source Schema         : yang-blog

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 20/07/2020 15:54:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog_article
-- ----------------------------
DROP TABLE IF EXISTS `blog_article`;
CREATE TABLE `blog_article`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '封面图片',
  `category_id` int(11) UNSIGNED NOT NULL COMMENT '栏目id',
  `tag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `views` int(11) NULL DEFAULT 0 COMMENT '阅读量',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '0-草稿 1-发布',
  `enable_comment` int(11) NOT NULL DEFAULT 0 COMMENT '1-允许评论 0-不允许评论',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_category_id`(`category_id`) USING BTREE,
  CONSTRAINT `fk_category_id` FOREIGN KEY (`category_id`) REFERENCES `blog_category` (`id`) ON DELETE NO ACTION ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_article
-- ----------------------------
INSERT INTO `blog_article` VALUES (36, 'test', 'http://qbr6zdhq7.bkt.clouddn.com/17577e39-e969-4e85-94d6-11a58e8f8b76123.jpg', 7, '123,456,789', '123', 0, 1, 1, '2020-07-20 11:23:11', '2020-07-20 11:23:11');

-- ----------------------------
-- Table structure for blog_article_tag
-- ----------------------------
DROP TABLE IF EXISTS `blog_article_tag`;
CREATE TABLE `blog_article_tag`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '关系表id',
  `blog_id` bigint(20) NOT NULL COMMENT '博客id',
  `tag_id` int(11) NOT NULL COMMENT '标签id',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 296 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_article_tag
-- ----------------------------
INSERT INTO `blog_article_tag` VALUES (288, 36, 57, '2020-07-20 11:23:11', '2020-07-20 11:23:11');
INSERT INTO `blog_article_tag` VALUES (289, 36, 58, '2020-07-20 11:23:11', '2020-07-20 11:23:11');
INSERT INTO `blog_article_tag` VALUES (290, 36, 59, '2020-07-20 11:23:11', '2020-07-20 11:23:11');

-- ----------------------------
-- Table structure for blog_category
-- ----------------------------
DROP TABLE IF EXISTS `blog_category`;
CREATE TABLE `blog_category`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_category
-- ----------------------------
INSERT INTO `blog_category` VALUES (1, 'springboot专栏', '2020-06-17 10:49:40', '2020-06-17 10:49:43');
INSERT INTO `blog_category` VALUES (2, '微服务专栏', '2020-06-17 10:49:45', '2020-06-17 10:49:48');
INSERT INTO `blog_category` VALUES (7, '测试', '2020-06-17 15:38:17', '2020-06-17 15:38:17');

-- ----------------------------
-- Table structure for blog_comment
-- ----------------------------
DROP TABLE IF EXISTS `blog_comment`;
CREATE TABLE `blog_comment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `article_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '关联的blog主键',
  `commentator` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '评论者名称',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '评论人的邮箱',
  `comment_body` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '评论内容',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `commentator_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '评论时的ip地址',
  `reply_body` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '回复内容',
  `reply_create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '回复时间',
  `comment_status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否审核通过 0-未审核 1-审核通过',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_comment
-- ----------------------------
INSERT INTO `blog_comment` VALUES (26, 4, '十三', '224683568@qq.com', '第一条评论', '2019-05-13 10:12:19', '', '123', '2020-07-09 07:11:45', 1, '2020-07-09 15:18:46');

-- ----------------------------
-- Table structure for blog_link
-- ----------------------------
DROP TABLE IF EXISTS `blog_link`;
CREATE TABLE `blog_link`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '友链表主键id',
  `type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '友链类别 0-友链 1-推荐 2-个人网站',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '网站名称',
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '网站链接',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '网站描述',
  `sort` int(11) NOT NULL DEFAULT 0 COMMENT '用于列表排序',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_link
-- ----------------------------
INSERT INTO `blog_link` VALUES (1, 0, 'tqr', 'rqwe', 'rqw', 0, '2018-10-22 18:57:52', NULL);
INSERT INTO `blog_link` VALUES (2, 2, '十三的GitHub', 'https://github.com/ZHENFENG13', '十三分享代码的地方', 1, '2018-10-22 19:41:04', NULL);
INSERT INTO `blog_link` VALUES (3, 2, '十三的博客', 'http://13blog.site', '个人独立博\r\n客13blog', 14, '2018-10-22 19:53:34', NULL);
INSERT INTO `blog_link` VALUES (4, 1, 'CSDN 图文课', 'https://gitchat.csdn.net', 'IT优质内容平台', 6, '2018-10-22 19:55:55', NULL);
INSERT INTO `blog_link` VALUES (5, 2, '十三的博客园', 'https://www.cnblogs.com/han-1034683568', '最开始写博客的地方', 17, '2018-10-22 19:56:14', NULL);
INSERT INTO `blog_link` VALUES (6, 1, 'CSDN', 'https://www.csdn.net/', 'CSDN-专业IT技术社区官网', 4, '2018-10-22 19:56:47', NULL);
INSERT INTO `blog_link` VALUES (7, 0, '梁桂钊的博客', 'http://blog.720ui.com', '后端攻城狮', 1, '2018-10-22 20:01:38', NULL);
INSERT INTO `blog_link` VALUES (8, 0, '猿天地', 'http://cxytiandi.com', '一个综合性的\r\n网站,以程序猿用户为主,提供各种开发相关的内容', 12, '2018-10-22 20:02:41', NULL);
INSERT INTO `blog_link` VALUES (9, 0, 'Giraffe Home', 'https://yemengying.com/', 'Giraffe Home', 0, '2018-10-22 20:27:04', NULL);
INSERT INTO `blog_link` VALUES (10, 0, '纯洁的微笑', 'http://www.ityouknow.com', '分享技术，分享生活', 3, '2018-10-22 20:27:16', NULL);
INSERT INTO `blog_link` VALUES (11, 0, 'afsdf', 'http://localhost:28080/admin/links', 'fad', 0, '2018-10-22 20:27:26', NULL);
INSERT INTO `blog_link` VALUES (12, 1, 'afsdf', 'http://localhost', 'fad1', 0, '2018-10-24 14:04:18', NULL);
INSERT INTO `blog_link` VALUES (13, 0, '郭赵晖', 'http://guozh.net/', '老郭三分地', 0, '2019-04-24 15:30:19', NULL);
INSERT INTO `blog_link` VALUES (14, 0, 'dalaoyang', 'https://www.dalaoyang.cn/', 'dalaoyang', 0, '2019-04-24 15:31:50', NULL);
INSERT INTO `blog_link` VALUES (15, 0, 'mushblog', 'https://www.sansani.cn', '穆世明博客', 0, '2019-04-24 15:32:19', NULL);
INSERT INTO `blog_link` VALUES (16, 1, '实验楼', 'https://www.shiyanlou.com/', '一家专注于IT技术的在线实训平台', 17, '2019-04-24 16:03:48', NULL);
INSERT INTO `blog_link` VALUES (17, 2, '《SSM 搭建精美实用的管理系统》', 'https://gitbook.cn/gitchat/column/5b4dae389bcda53d07056bc9', 'Spring+SpringMVC+MyBatis实战课程', 18, '2019-04-24 16:06:52', NULL);
INSERT INTO `blog_link` VALUES (18, 2, '《Spring Boot 入门及前后端分离项目实践》', 'https://www.shiyanlou.com/courses/1244', 'SpringBoot实战课程', 19, '2019-04-24 16:07:27', NULL);
INSERT INTO `blog_link` VALUES (19, 2, '《玩转Spring Boot 系列》', 'https://www.shiyanlou.com/courses/1274', 'SpringBoot实战课程', 20, '2019-04-24 16:10:30', NULL);
INSERT INTO `blog_link` VALUES (21, 2, '1323', '1323', '3213', 1, '2020-06-17 15:59:43', '2020-06-17 16:05:17');

-- ----------------------------
-- Table structure for blog_tag
-- ----------------------------
DROP TABLE IF EXISTS `blog_tag`;
CREATE TABLE `blog_tag`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `number` int(11) UNSIGNED NULL DEFAULT 1 COMMENT '数量',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 63 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_tag
-- ----------------------------
INSERT INTO `blog_tag` VALUES (57, '123', 1, '2020-07-20 11:22:56', '2020-07-20 11:22:56');
INSERT INTO `blog_tag` VALUES (58, '456', 1, '2020-07-20 11:22:56', '2020-07-20 11:22:56');
INSERT INTO `blog_tag` VALUES (59, '789', 1, '2020-07-20 11:22:56', '2020-07-20 11:22:56');

-- ----------------------------
-- Table structure for sys_admin
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin`;
CREATE TABLE `sys_admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '登录密码',
  `nick_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `salt` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '盐值',
  `token` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'token',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统管理-用户基础信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_admin
-- ----------------------------
INSERT INTO `sys_admin` VALUES (1, 'admin', '97ba1ef7f148b2aec1c61303a7d88d0967825495', '郑清', 'zhengqing', 'af4209d975acae3d9ba00a93b7b7a87f35ef4ce5', '2020-05-07 15:03:33', '2020-06-05 14:56:20');
INSERT INTO `sys_admin` VALUES (2, 'test', '97ba1ef7f148b2aec1c61303a7d88d0967825495', '测试号', 'zhengqing', 'a4bf084f250aebc8f0bc806bdf9bca205c7706c9', '2020-05-29 15:03:37', '2020-05-29 15:03:41');

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `config_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '配置项的名称',
  `config_value` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '配置项的值',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`config_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('footerAbout', 'your personal blog. have fun.', '2018-11-11 20:33:23', '2018-11-12 11:58:06');
INSERT INTO `sys_config` VALUES ('footerCopyRight', '2019 十三', '2018-11-11 20:33:31', '2018-11-12 11:58:06');
INSERT INTO `sys_config` VALUES ('footerICP', '浙ICP备17008806号-3', '2018-11-11 20:33:27', '2018-11-12 11:58:06');
INSERT INTO `sys_config` VALUES ('footerPoweredBy', 'https://github.com/ZHENFENG13', '2018-11-11 20:33:36', '2018-11-12 11:58:06');
INSERT INTO `sys_config` VALUES ('footerPoweredByURL', 'https://github.com/ZHENFENG13', '2018-11-11 20:33:39', '2018-11-12 11:58:06');
INSERT INTO `sys_config` VALUES ('websiteDescription', 'personal blog是SpringBoot2+Thymeleaf+Mybatis建造的个人博客网站.SpringBoot实战博客源码.个人博客搭建', '2018-11-11 20:33:04', '2018-11-11 22:05:14');
INSERT INTO `sys_config` VALUES ('websiteIcon', '/admin/dist/img/favicon.png', '2018-11-11 20:33:11', '2018-11-11 22:05:14');
INSERT INTO `sys_config` VALUES ('websiteLogo', '/admin/dist/img/logo2.png', '2018-11-11 20:33:08', '2018-11-11 22:05:14');
INSERT INTO `sys_config` VALUES ('websiteName', 'personal blog', '2018-11-11 20:33:01', '2018-11-11 22:05:14');
INSERT INTO `sys_config` VALUES ('yourAvatar', '/admin/dist/img/13.png', '2018-11-11 20:33:14', '2019-05-07 21:56:23');
INSERT INTO `sys_config` VALUES ('yourEmail', '2449207463@qq.com', '2018-11-11 20:33:17', '2019-05-07 21:56:23');
INSERT INTO `sys_config` VALUES ('yourName', '13', '2018-11-11 20:33:20', '2019-05-07 21:56:23');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pid` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '父id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '规则名称',
  `url` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'url',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `ismenu` int(11) NOT NULL DEFAULT 0 COMMENT '是否菜单',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统管理-权限资源表 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, 0, '权限管理', 'auth', 'fa fa-users', 1, '2019-03-28 18:51:08', '2019-03-28 18:51:08');
INSERT INTO `sys_permission` VALUES (2, 1, '管理员管理', '/admin/auth/admin/index', 'fa fa-user', 1, '2019-03-28 18:51:08', '2019-03-28 18:51:08');
INSERT INTO `sys_permission` VALUES (3, 2, '列表', '/admin/auth/admin/index', NULL, 0, '2019-03-28 18:51:08', '2019-03-28 18:51:10');
INSERT INTO `sys_permission` VALUES (4, 2, '添加', '/admin/auth/admin/add', NULL, 0, '2019-03-28 18:52:13', '2019-08-31 21:26:57');
INSERT INTO `sys_permission` VALUES (5, 2, '更新', '/admin/auth/admin/update', NULL, 0, '2019-03-28 18:52:13', '2019-03-28 18:52:13');
INSERT INTO `sys_permission` VALUES (6, 2, '删除', '/admin/auth/admin/delete', NULL, 0, '2019-03-28 18:52:13', '2019-03-28 18:52:13');
INSERT INTO `sys_permission` VALUES (7, 1, '角色管理', '/admin/auth/role/index', 'fa fa-users', 0, '2019-03-28 18:52:13', '2019-03-28 18:52:13');
INSERT INTO `sys_permission` VALUES (8, 7, '列表', '/admin/auth/role/index', NULL, 0, '2019-03-28 18:52:13', '2019-03-28 18:52:13');
INSERT INTO `sys_permission` VALUES (9, 7, '添加', '/admin/auth/role/add', NULL, 0, '2019-03-28 18:52:13', '2019-03-28 18:52:13');
INSERT INTO `sys_permission` VALUES (10, 7, '更新', '/admin/auth/role/update', NULL, 0, '2019-03-28 18:52:13', '2019-03-28 18:52:13');
INSERT INTO `sys_permission` VALUES (11, 7, '删除', '/admin/auth/role/delete', NULL, 0, '2019-03-28 18:52:13', '2019-03-28 18:52:13');
INSERT INTO `sys_permission` VALUES (12, 0, '内容管理', 'content', 'fa fa-align-center', 1, '2020-05-29 16:01:18', NULL);
INSERT INTO `sys_permission` VALUES (13, 12, '栏目管理', '/admin/content/category/index', 'fa fa-list', 1, NULL, NULL);
INSERT INTO `sys_permission` VALUES (14, 13, '列表', '/admin/content/category/index', NULL, 0, NULL, NULL);
INSERT INTO `sys_permission` VALUES (15, 13, '添加', '/admin/content/category/add', NULL, 0, NULL, NULL);
INSERT INTO `sys_permission` VALUES (16, 13, '更新', '/admin/content/category/update', NULL, 0, NULL, NULL);
INSERT INTO `sys_permission` VALUES (17, 13, '删除', '/admin/content/category/delete', NULL, 0, NULL, NULL);
INSERT INTO `sys_permission` VALUES (23, 2, '分配角色', '/admin/auth/admin/assign', NULL, 0, NULL, NULL);
INSERT INTO `sys_permission` VALUES (25, 7, '分配权限', '/admin/auth/role/assign', NULL, 0, NULL, NULL);
INSERT INTO `sys_permission` VALUES (27, 12, '友情链接', '/admin/content/link/index', 'fa fa-link', 1, NULL, NULL);
INSERT INTO `sys_permission` VALUES (28, 27, '列表', '/admin/content/link/index', NULL, 0, NULL, NULL);
INSERT INTO `sys_permission` VALUES (29, 27, '添加', '/admin/content/link/add', NULL, 0, NULL, NULL);
INSERT INTO `sys_permission` VALUES (30, 27, '更新', '/admin/content/link/update', NULL, 0, NULL, NULL);
INSERT INTO `sys_permission` VALUES (31, 27, '删除', '/admin/content/link/delete', NULL, 0, NULL, NULL);
INSERT INTO `sys_permission` VALUES (32, 0, '系统设置', 'system', 'fa fa-cogs', 0, NULL, NULL);
INSERT INTO `sys_permission` VALUES (33, 32, '系统配置', '/admin/system/config/index', 'fa fa-cog', 1, NULL, NULL);
INSERT INTO `sys_permission` VALUES (34, 33, '查看', '/admin/system/config/index', NULL, 0, NULL, NULL);
INSERT INTO `sys_permission` VALUES (35, 33, '编辑', '/admin/system/config/edit', NULL, 0, NULL, NULL);
INSERT INTO `sys_permission` VALUES (36, 12, '文章管理', '/admin/content/article/index', 'fa fa-clipboard', 1, NULL, NULL);
INSERT INTO `sys_permission` VALUES (37, 36, '列表', '/admin/content/article/index', NULL, 0, NULL, NULL);
INSERT INTO `sys_permission` VALUES (38, 36, '添加', '/admin/content/article/add', NULL, 0, NULL, NULL);
INSERT INTO `sys_permission` VALUES (39, 36, '更新', '/admin/content/article/edit', NULL, 0, NULL, NULL);
INSERT INTO `sys_permission` VALUES (40, 36, '删除', '/admin/content/article/delete', NULL, 0, NULL, NULL);
INSERT INTO `sys_permission` VALUES (41, 12, '标签管理', '/admin/content/tag/index', 'fa fa-tags', 0, NULL, NULL);
INSERT INTO `sys_permission` VALUES (42, 41, '列表', '/admin/content/tag/index', NULL, 0, NULL, NULL);
INSERT INTO `sys_permission` VALUES (43, 41, '删除', '/admin/content/tag/delete', NULL, 0, NULL, NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色编码',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统管理-角色表 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'admin', '系统管理员', '2019-01-04 11:19:22', '2020-05-26 11:51:25');
INSERT INTO `sys_role` VALUES (2, 'visitor', '访客', '2020-05-26 11:51:27', '2020-06-16 17:33:50');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色ID',
  `permission_id` int(11) NULL DEFAULT NULL COMMENT '权限ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 899 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统管理 - 角色-权限资源关联表 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES (5, 3, 1, '2019-09-18 21:26:43', '2019-09-18 21:26:43');
INSERT INTO `sys_role_permission` VALUES (818, 1, 1, '2020-07-20 14:29:48', '2020-07-20 14:29:48');
INSERT INTO `sys_role_permission` VALUES (819, 1, 2, '2020-07-20 14:29:48', '2020-07-20 14:29:48');
INSERT INTO `sys_role_permission` VALUES (820, 1, 3, '2020-07-20 14:29:48', '2020-07-20 14:29:48');
INSERT INTO `sys_role_permission` VALUES (821, 1, 4, '2020-07-20 14:29:48', '2020-07-20 14:29:48');
INSERT INTO `sys_role_permission` VALUES (822, 1, 5, '2020-07-20 14:29:48', '2020-07-20 14:29:48');
INSERT INTO `sys_role_permission` VALUES (823, 1, 6, '2020-07-20 14:29:48', '2020-07-20 14:29:48');
INSERT INTO `sys_role_permission` VALUES (824, 1, 23, '2020-07-20 14:29:48', '2020-07-20 14:29:48');
INSERT INTO `sys_role_permission` VALUES (825, 1, 24, '2020-07-20 14:29:48', '2020-07-20 14:29:48');
INSERT INTO `sys_role_permission` VALUES (826, 1, 7, '2020-07-20 14:29:48', '2020-07-20 14:29:48');
INSERT INTO `sys_role_permission` VALUES (827, 1, 8, '2020-07-20 14:29:48', '2020-07-20 14:29:48');
INSERT INTO `sys_role_permission` VALUES (828, 1, 9, '2020-07-20 14:29:48', '2020-07-20 14:29:48');
INSERT INTO `sys_role_permission` VALUES (829, 1, 10, '2020-07-20 14:29:48', '2020-07-20 14:29:48');
INSERT INTO `sys_role_permission` VALUES (830, 1, 11, '2020-07-20 14:29:48', '2020-07-20 14:29:48');
INSERT INTO `sys_role_permission` VALUES (831, 1, 25, '2020-07-20 14:29:48', '2020-07-20 14:29:48');
INSERT INTO `sys_role_permission` VALUES (832, 1, 26, '2020-07-20 14:29:48', '2020-07-20 14:29:48');
INSERT INTO `sys_role_permission` VALUES (833, 1, 12, '2020-07-20 14:29:48', '2020-07-20 14:29:48');
INSERT INTO `sys_role_permission` VALUES (834, 1, 13, '2020-07-20 14:29:48', '2020-07-20 14:29:48');
INSERT INTO `sys_role_permission` VALUES (835, 1, 14, '2020-07-20 14:29:48', '2020-07-20 14:29:48');
INSERT INTO `sys_role_permission` VALUES (836, 1, 15, '2020-07-20 14:29:48', '2020-07-20 14:29:48');
INSERT INTO `sys_role_permission` VALUES (837, 1, 16, '2020-07-20 14:29:48', '2020-07-20 14:29:48');
INSERT INTO `sys_role_permission` VALUES (838, 1, 17, '2020-07-20 14:29:48', '2020-07-20 14:29:48');
INSERT INTO `sys_role_permission` VALUES (839, 1, 27, '2020-07-20 14:29:48', '2020-07-20 14:29:48');
INSERT INTO `sys_role_permission` VALUES (840, 1, 28, '2020-07-20 14:29:48', '2020-07-20 14:29:48');
INSERT INTO `sys_role_permission` VALUES (841, 1, 29, '2020-07-20 14:29:48', '2020-07-20 14:29:48');
INSERT INTO `sys_role_permission` VALUES (842, 1, 30, '2020-07-20 14:29:48', '2020-07-20 14:29:48');
INSERT INTO `sys_role_permission` VALUES (843, 1, 31, '2020-07-20 14:29:48', '2020-07-20 14:29:48');
INSERT INTO `sys_role_permission` VALUES (844, 1, 36, '2020-07-20 14:29:48', '2020-07-20 14:29:48');
INSERT INTO `sys_role_permission` VALUES (845, 1, 37, '2020-07-20 14:29:48', '2020-07-20 14:29:48');
INSERT INTO `sys_role_permission` VALUES (846, 1, 38, '2020-07-20 14:29:48', '2020-07-20 14:29:48');
INSERT INTO `sys_role_permission` VALUES (847, 1, 39, '2020-07-20 14:29:48', '2020-07-20 14:29:48');
INSERT INTO `sys_role_permission` VALUES (848, 1, 40, '2020-07-20 14:29:48', '2020-07-20 14:29:48');
INSERT INTO `sys_role_permission` VALUES (849, 1, 41, '2020-07-20 14:29:48', '2020-07-20 14:29:48');
INSERT INTO `sys_role_permission` VALUES (850, 1, 42, '2020-07-20 14:29:48', '2020-07-20 14:29:48');
INSERT INTO `sys_role_permission` VALUES (851, 1, 43, '2020-07-20 14:29:48', '2020-07-20 14:29:48');
INSERT INTO `sys_role_permission` VALUES (852, 1, 32, '2020-07-20 14:29:48', '2020-07-20 14:29:48');
INSERT INTO `sys_role_permission` VALUES (853, 1, 33, '2020-07-20 14:29:48', '2020-07-20 14:29:48');
INSERT INTO `sys_role_permission` VALUES (854, 1, 34, '2020-07-20 14:29:48', '2020-07-20 14:29:48');
INSERT INTO `sys_role_permission` VALUES (855, 1, 35, '2020-07-20 14:29:48', '2020-07-20 14:29:48');
INSERT INTO `sys_role_permission` VALUES (886, 2, 1, '2020-07-20 15:31:28', '2020-07-20 15:31:28');
INSERT INTO `sys_role_permission` VALUES (887, 2, 2, '2020-07-20 15:31:28', '2020-07-20 15:31:28');
INSERT INTO `sys_role_permission` VALUES (888, 2, 3, '2020-07-20 15:31:28', '2020-07-20 15:31:28');
INSERT INTO `sys_role_permission` VALUES (889, 2, 4, '2020-07-20 15:31:28', '2020-07-20 15:31:28');
INSERT INTO `sys_role_permission` VALUES (890, 2, 5, '2020-07-20 15:31:28', '2020-07-20 15:31:28');
INSERT INTO `sys_role_permission` VALUES (891, 2, 6, '2020-07-20 15:31:28', '2020-07-20 15:31:28');
INSERT INTO `sys_role_permission` VALUES (892, 2, 23, '2020-07-20 15:31:28', '2020-07-20 15:31:28');
INSERT INTO `sys_role_permission` VALUES (893, 2, 7, '2020-07-20 15:31:28', '2020-07-20 15:31:28');
INSERT INTO `sys_role_permission` VALUES (894, 2, 8, '2020-07-20 15:31:28', '2020-07-20 15:31:28');
INSERT INTO `sys_role_permission` VALUES (895, 2, 9, '2020-07-20 15:31:28', '2020-07-20 15:31:28');
INSERT INTO `sys_role_permission` VALUES (896, 2, 10, '2020-07-20 15:31:28', '2020-07-20 15:31:28');
INSERT INTO `sys_role_permission` VALUES (897, 2, 11, '2020-07-20 15:31:28', '2020-07-20 15:31:28');
INSERT INTO `sys_role_permission` VALUES (898, 2, 25, '2020-07-20 15:31:28', '2020-07-20 15:31:28');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色ID',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 67 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统管理 - 用户角色关联表 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (63, 2, 1, '2020-06-11 11:31:41', '2020-06-11 11:31:41');
INSERT INTO `sys_user_role` VALUES (64, 1, 1, '2020-06-11 11:31:41', '2020-06-11 11:31:41');
INSERT INTO `sys_user_role` VALUES (66, 2, 2, '2020-07-20 15:18:50', '2020-07-20 15:18:50');

SET FOREIGN_KEY_CHECKS = 1;
