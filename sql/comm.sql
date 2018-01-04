CREATE TABLE `comm_vars` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `cate_code` varchar(64) NOT NULL DEFAULT '' COMMENT '变量名',
  `code` varchar(64) NOT NULL DEFAULT '' COMMENT '键',
  `name` varchar(256) NOT NULL DEFAULT '' COMMENT '值',
  `opt_id` int(11) NOT NULL DEFAULT '0' COMMENT '操作人',
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `mark` tinyint(1) NOT NULL DEFAULT '0' COMMENT '标识(是否有效 0有效；-1无效)',
  PRIMARY KEY (`id`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='基础变量信息表'
