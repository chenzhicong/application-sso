SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `SYS_APP`
-- ----------------------------
DROP TABLE IF EXISTS `SYS_APP`;
CREATE TABLE `SYS_APP` (
  `id` varchar(32) NOT NULL COMMENT '应用id',
  `code` varchar(32) NOT NULL COMMENT '编码',
  `name` varchar(128) NOT NULL COMMENT '名称',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `status` int(1) NOT NULL DEFAULT 1 COMMENT '状态：1：正常  0：禁用',
  `sort` int(11) DEFAULT 0 COMMENT '排序',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` int(1) DEFAULT 0 COMMENT '是否已删除   0：未删除  1：已删除',
  PRIMARY KEY (`id`),
  KEY UK_APP_CODE (code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='应用表';

-- ----------------------------
-- Table structure for `SYS_ROLE`
-- ----------------------------
DROP TABLE IF EXISTS `SYS_ROLE`;
CREATE TABLE `SYS_ROLE` (
  `id` varchar(32) NOT NULL COMMENT '角色id',
  `app_id` varchar(32) NOT NULL COMMENT '应用id',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `status` int(1) NOT NULL DEFAULT 1 COMMENT '状态：1：正常  0：禁用',
  `sort` int(11) DEFAULT 0 COMMENT '排序',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` int(1) DEFAULT 0 COMMENT '是否已删除   0：未删除  1：已删除',
  PRIMARY KEY (`id`),
  CONSTRAINT FK_ROLE_APP_ID FOREIGN KEY (app_id) REFERENCES SYS_APP(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Table structure for `SYS_USER`
-- ----------------------------
DROP TABLE IF EXISTS `SYS_USER`;
CREATE TABLE `SYS_USER` (
  `id` varchar(32) NOT NULL COMMENT '用户id',
  `role_id` varchar(32) NOT NULL COMMENT '角色id',
  `account` varchar(50) NOT NULL COMMENT '登录名',
  `phone` varchar(50) DEFAULT NULL COMMENT '电话号码',
  `password` varchar(100) NOT NULL COMMENT '密码(加密)',
  `name` varchar(100) DEFAULT NULL COMMENT '真实姓名',
  `nick_name` varchar(100) DEFAULT NULL COMMENT '别名',
  `sex` int(1) DEFAULT -1 COMMENT '性别 -1:未知 0:男  1:女',
  `head_img_url` varchar(255) DEFAULT NULL COMMENT '用户头像url',
  `last_login_ip` varchar(20) DEFAULT NULL COMMENT '最后登录IP',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `login_count` int(11) DEFAULT 0 COMMENT '登录总次数',
  `status` int(1) NOT NULL DEFAULT 1 COMMENT '状态：1：正常  0：禁用',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` int(1) DEFAULT 0 COMMENT '是否已删除   0：未删除  1：已删除',
  PRIMARY KEY (`id`),
  CONSTRAINT FK_USER_ROLE_ID FOREIGN KEY (role_id) REFERENCES SYS_ROLE(id),
  KEY K_USER_ACCOUNT (account),
  KEY K_USER_PHONE (phone)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Table structure for `SYS_RESOURCE`
-- ----------------------------
DROP TABLE IF EXISTS `SYS_RESOURCE`;
CREATE TABLE `SYS_RESOURCE` (
  `id` varchar(32) NOT NULL COMMENT '权限id',
  `app_id` varchar(32) NOT NULL COMMENT '应用id',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父权限id',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `resource_type` int(1) NOT NULL COMMENT '1:菜单  2:按钮',
  `url` varchar(255) NOT NULL COMMENT '权限URL',
  `status` int(1) NOT NULL DEFAULT 1 COMMENT '状态：1：正常  0：禁用',
  `sort` int(11) DEFAULT 0 COMMENT '排序',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` int(1) DEFAULT 0 COMMENT '是否已删除   0：未删除  1：已删除',
  PRIMARY KEY (`id`),
  CONSTRAINT FK_RESOURCE_APP_ID FOREIGN KEY (app_id) REFERENCES SYS_APP(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源表';


-- ----------------------------
-- Table structure for `SYS_RE_USER_ROLE`
-- ----------------------------
/*
DROP TABLE IF EXISTS `SYS_RE_USER_ROLE`;
CREATE TABLE `SYS_RE_USER_ROLE` (
  `id` varchar(32) NOT NULL,
  `user_id` varchar(32) NOT NULL COMMENT '用户id ',
  `role_id` varchar(32) NOT NULL COMMENT '角色id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `deleted` int(1) DEFAULT 0 COMMENT '是否已删除   0：未删除  1：已删除',
  PRIMARY KEY (`id`),
  CONSTRAINT FK_RUR_USER_ID FOREIGN KEY (user_id) REFERENCES SYS_USER(id),
  CONSTRAINT FK_RUR_ROLE_ID FOREIGN KEY (role_id) REFERENCES SYS_ROLE(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';
*/

-- ----------------------------
-- Table structure for `SYS_ROLE_PERMISSION`
-- ----------------------------
DROP TABLE IF EXISTS `SYS_ROLE_PERMISSION`;
CREATE TABLE `SYS_ROLE_PERMISSION` (
  `id` varchar(32) NOT NULL,
  `role_id` varchar(32) NOT NULL COMMENT '角色id',
  `resource_id` varchar(32) NOT NULL COMMENT '权限id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` int(1) DEFAULT 0 COMMENT '是否已删除   0：未删除  1：已删除',
  PRIMARY KEY (`id`),
  CONSTRAINT FK_RRR_ROLE_ID FOREIGN KEY (role_id) REFERENCES SYS_ROLE(id),
  CONSTRAINT FK_RRR_RESOURCE_ID FOREIGN KEY (resource_id) REFERENCES SYS_RESOURCE(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限表';