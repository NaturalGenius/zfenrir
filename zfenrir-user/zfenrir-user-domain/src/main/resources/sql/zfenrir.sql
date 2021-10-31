
CREATE DATABASE /*!32312 IF NOT EXISTS*/`zfenrir` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `zfenrir`;

CREATE TABLE `permission` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `code` varchar(20) NOT NULL DEFAULT '' COMMENT '权限名称',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '权限名称',
  `pid` int(11) NOT NULL DEFAULT '0' COMMENT '父ID',
  `control` varchar(20) NOT NULL DEFAULT '' COMMENT '权限控制器',
  `app_id` varchar(20) NOT NULL DEFAULT '' COMMENT '所属系统id',
  `menu` int(11) NOT NULL DEFAULT '1' COMMENT '菜单 1 菜单 2 按钮',
  `url` varchar(100) NOT NULL DEFAULT '' COMMENT '路径',
  `method` varchar(100) NOT NULL DEFAULT '' COMMENT '请求方法 多个逗号隔开',
  `desc` varchar(20) NOT NULL DEFAULT '' COMMENT '描述',
  `status` tinyint(4) NOT NULL COMMENT '状态 1正常 2 删除',
  `create_id` int(11) NOT NULL DEFAULT '0' COMMENT '创建人id',
  `create_name` varchar(50) NOT NULL DEFAULT '' COMMENT '创建人名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_id` int(11) NOT NULL DEFAULT '0' COMMENT '更新人id',
  `update_name` varchar(50) NOT NULL DEFAULT '' COMMENT '更新人名称',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unqi_code` (`code`),
  KEY `idx_app_id` (`app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '权限名称',
  `code` varchar(20) NOT NULL DEFAULT '' COMMENT '权限名称',
  `type` tinyint(2) NOT NULL DEFAULT '1' COMMENT '类型 1 普通用户 2 系统角色 3 系统管理员 4 超级管理员',
  `app_id` varchar(20) NOT NULL DEFAULT '' COMMENT '所属系统名称',
  `desc` varchar(100) NOT NULL DEFAULT '' COMMENT '描述',
  `status` tinyint(4) NOT NULL COMMENT '状态 1正常 2 删除',
  `create_id` int(11) NOT NULL DEFAULT '0' COMMENT '创建人id',
  `create_name` varchar(50) NOT NULL DEFAULT '' COMMENT '创建人名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_id` int(11) NOT NULL DEFAULT '0' COMMENT '更新人id',
  `update_name` varchar(50) NOT NULL DEFAULT '' COMMENT '更新人名称',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unqi_code` (`code`),
  KEY `idx_app_id` (`app_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `role_permission` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `role_id` int(11) NOT NULL DEFAULT '0' COMMENT '角色ID',
  `permission_id` int(11) NOT NULL DEFAULT '0' COMMENT '权限ID',
  `status` tinyint(4) NOT NULL COMMENT '状态 1正常 2 删除',
  `create_id` int(11) NOT NULL DEFAULT '0' COMMENT '创建人id',
  `create_name` varchar(50) NOT NULL DEFAULT '' COMMENT '创建人名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_id` int(11) NOT NULL DEFAULT '0' COMMENT '更新人id',
  `update_name` varchar(50) NOT NULL DEFAULT '' COMMENT '更新人名称',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_role_id` (`role_id`),
  KEY `idx_permission_id` (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `system` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `app_id` varchar(50) NOT NULL DEFAULT '' COMMENT '系统id',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '系统名称',
  `desc` varchar(100) NOT NULL DEFAULT '' COMMENT '系统描述',
  `home_url` varchar(100) NOT NULL DEFAULT '' COMMENT '首页地址',
  `status` tinyint(4) NOT NULL COMMENT '状态 1正常 2 删除',
  `create_id` int(11) NOT NULL DEFAULT '0' COMMENT '创建人id',
  `create_name` varchar(50) NOT NULL DEFAULT '' COMMENT '创建人名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_id` int(11) NOT NULL DEFAULT '0' COMMENT '更新人id',
  `update_name` varchar(50) NOT NULL DEFAULT '' COMMENT '更新人名称',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `system_operate_log` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '操作类型',
  `entity_id` int(11) NOT NULL DEFAULT '0' COMMENT '操作实体id',
  `old_value` varchar(1000) NOT NULL DEFAULT '' COMMENT '旧值',
  `new_value` varchar(1000) NOT NULL DEFAULT '' COMMENT '新值',
  `remark` varchar(500) NOT NULL DEFAULT '' COMMENT '备注',
  `create_id` int(11) NOT NULL DEFAULT '0' COMMENT '创建人id',
  `create_name` varchar(50) NOT NULL DEFAULT '' COMMENT '创建人名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_id` int(11) NOT NULL DEFAULT '0' COMMENT '更新人id',
  `update_name` varchar(50) NOT NULL DEFAULT '' COMMENT '更新人名称',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(20) NOT NULL DEFAULT '' COMMENT '用户名称',
  `nick_name` varchar(50) NOT NULL DEFAULT '' COMMENT '昵称',
  `account` varchar(50) NOT NULL DEFAULT '' COMMENT '登录名',
  `password` varchar(100) NOT NULL DEFAULT '' COMMENT '密码',
  `telphone` varchar(11) NOT NULL DEFAULT '' COMMENT '电话号码',
  `email` varchar(50) NOT NULL DEFAULT '' COMMENT '邮箱',
  `status` tinyint(4) NOT NULL COMMENT '状态 1正常 2 删除',
  `create_id` int(11) NOT NULL DEFAULT '0' COMMENT '创建人id',
  `create_name` varchar(50) NOT NULL DEFAULT '' COMMENT '创建人名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_id` int(11) NOT NULL DEFAULT '0' COMMENT '更新人id',
  `update_name` varchar(50) NOT NULL DEFAULT '' COMMENT '更新人名称',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_account` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `user_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `role_id` int(11) NOT NULL DEFAULT '0' COMMENT '角色ID',
  `status` tinyint(4) NOT NULL COMMENT '状态 1正常 2 删除',
  `create_id` int(11) NOT NULL DEFAULT '0' COMMENT '创建人id',
  `create_name` varchar(50) NOT NULL DEFAULT '' COMMENT '创建人名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_id` int(11) NOT NULL DEFAULT '0' COMMENT '更新人id',
  `update_name` varchar(50) NOT NULL DEFAULT '' COMMENT '更新人名称',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_role_id` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
