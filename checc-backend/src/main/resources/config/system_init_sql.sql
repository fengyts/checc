/* 后台系统初始化sql  */

-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.11-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.1.0.4867
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出  表 checc.sys_menu 结构
CREATE TABLE IF NOT EXISTS `sys_menu` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(50) DEFAULT NULL COMMENT '菜单code',
  `parent_id` bigint(11) DEFAULT NULL COMMENT '父菜单id，如果为空，则为根菜单',
  `name` varchar(50) NOT NULL COMMENT '菜单名称',
  `url` varchar(150) DEFAULT NULL COMMENT '菜单请求链接',
  `menu_type` int(11) NOT NULL COMMENT '菜单类型（0:根菜单，1 :主导航,2：菜单,3：按钮',
  `sort` int(11) NOT NULL COMMENT '菜单排序值',
  `status` tinyint(2) NOT NULL COMMENT '菜单状态',
  `create_user_id` bigint(11) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_user_id` bigint(11) NOT NULL COMMENT '修改人',
  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='系统菜单表';

-- 正在导出表  checc.sys_menu 的数据：~7 rows (大约)
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` (`id`, `code`, `parent_id`, `name`, `url`, `menu_type`, `sort`, `status`, `create_user_id`, `create_time`, `modify_user_id`, `modify_time`) VALUES
	(1, 'root', 0, '系统菜单', NULL, 0, 0, 1, 0, '2017-11-17 16:51:07', 0, '2017-11-17 16:51:10'),
	(2, 'backendManager', 1, '后台管理', NULL, 1, 1, 1, 0, '2017-11-17 16:54:15', 0, '2017-11-17 16:54:18'),
	(3, 'sysManager', 1, '系统管理', NULL, 1, 2, 1, 0, '2017-11-17 20:52:36', 0, '2017-11-17 20:52:39'),
	(4, 'sysManagerChild', 3, '系统管理(主)', NULL, 2, 21, 1, 1, '2017-11-17 21:06:13', 1, '2017-11-17 21:06:15'),
	(5, 'sysUserManager', 4, '用户管理', '/sys/sysUser/list.htm', 2, 211, 1, 1, '2017-11-17 21:54:04', 1, '2017-11-17 21:54:06'),
	(6, 'sysRoleManager', 4, '角色管理', '/sys/sysRole/list.htm', 2, 212, 1, 1, '2017-11-17 22:42:33', 1, '2017-11-17 22:42:35'),
	(7, 'sysMenuManager', 4, '菜单管理', '/sys/sysMenu/list.htm', 2, 213, 1, 1, '2017-11-17 22:44:47', 1, '2017-11-17 22:44:47');
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;


-- 导出  表 checc.sys_role 结构
CREATE TABLE IF NOT EXISTS `sys_role` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) NOT NULL COMMENT '角色名称',
  `role_code` varchar(50) NOT NULL COMMENT '角色代码',
  `status` tinyint(2) NOT NULL COMMENT '角色状态，是否可用：1-可用；0-不可用',
  `create_user_id` bigint(11) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_user_id` bigint(11) NOT NULL COMMENT '修改人',
  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统用户角色表';

-- 正在导出表  checc.sys_role 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` (`id`, `role_name`, `role_code`, `status`, `create_user_id`, `create_time`, `modify_user_id`, `modify_time`) VALUES
	(1, '超级管理员', 'superadmin', 1, 0, '2017-11-17 16:48:16', 0, '2017-11-17 16:48:16');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;


-- 导出  表 checc.sys_role_menu 结构
CREATE TABLE IF NOT EXISTS `sys_role_menu` (
  `role_id` bigint(11) NOT NULL,
  `menu_id` bigint(11) NOT NULL,
  PRIMARY KEY (`menu_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色菜单表';

-- 正在导出表  checc.sys_role_menu 的数据：~6 rows (大约)
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES
	(1, 1),
	(1, 2),
	(1, 3),
	(1, 4),
	(1, 5),
	(1, 6),
	(1, 7);
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;


-- 导出  表 checc.sys_user 结构
CREATE TABLE IF NOT EXISTS `sys_user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL COMMENT '用户名称',
  `login_name` varchar(50) NOT NULL COMMENT '用户登录名',
  `email` varchar(100) NOT NULL COMMENT 'E_mail',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `salt` varchar(100) NOT NULL COMMENT '登录盐',
  `mobile` varchar(15) NOT NULL COMMENT '手机号',
  `status` tinyint(2) NOT NULL COMMENT '用户状态：0-无效；1-有效',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '创建人',
  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` bigint(11) NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='后台系统用户表';

-- 正在导出表  checc.sys_user 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` (`id`, `user_name`, `login_name`, `email`, `password`, `salt`, `mobile`, `status`, `create_time`, `create_user_id`, `modify_time`, `modify_user_id`) VALUES
	(1, '超级管理员', 'superadmin', 'fengyts@163.com', 'ad8c2683d171e12a914fd4219f8a845a', 'afc0f8db123621b989c0ae68e8792e62', '13564088616', 1, '2017-11-17 15:33:36', 0, '2017-11-17 15:33:40', 0);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;


-- 导出  表 checc.sys_user_role 结构
CREATE TABLE IF NOT EXISTS `sys_user_role` (
  `user_id` bigint(11) NOT NULL,
  `role_id` bigint(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户角色表';

-- 正在导出表  checc.sys_user_role 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES
	(1, 1),
	(2, 1);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
