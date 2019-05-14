drop table if exists file;

CREATE TABLE `file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '文件名',
  `md5` varchar(32) DEFAULT NULL COMMENT 'MD5值',
  `path` varchar(100) NOT NULL COMMENT '文件路径',
  `upload_time` datetime(3) NOT NULL COMMENT '上传时间',
  `ext` varchar(255) DEFAULT NULL COMMENT '文件后缀名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;
