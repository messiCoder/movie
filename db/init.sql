DROP TABLE IF EXISTS `movie`;
CREATE TABLE `movie` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT '' COMMENT '电影名称',
  `imageUrl` varchar(500) DEFAULT '' COMMENT '电影图片地址',
  `score` varchar(255) DEFAULT '' COMMENT '评分',
  `director` varchar(255) DEFAULT '' COMMENT '导演',
  `actor` varchar(500) DEFAULT '' COMMENT '演员',
  `type` varchar(255) DEFAULT '' COMMENT '电影类型',
  `region` varchar(255) DEFAULT '' COMMENT '上映地区',
  `showTime` varchar(255) DEFAULT '' COMMENT '上映时间',
  `imdb` varchar(255) DEFAULT NULL COMMENT 'imdb',
  `movieLong` varchar(255) DEFAULT '' COMMENT '片长',
  `otherName` varchar(255) DEFAULT '' COMMENT '其他名称',
  `movieStory` text COMMENT '剧情',
  PRIMARY KEY (`id`),
  KEY `name_index` (`name`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `downaddress`;
CREATE TABLE `downaddress` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `movieId` bigint(20) NOT NULL COMMENT '电影表id',
  `downName` varchar(255) DEFAULT NULL COMMENT '下载显示名称',
  `seedUrl` varchar(1000) DEFAULT NULL COMMENT '种子地址',
  `downUrl` varchar(1000) DEFAULT NULL COMMENT '磁力链接',
  `rp` varchar(50) DEFAULT NULL COMMENT '分辨率',
  `size` varchar(50) DEFAULT NULL COMMENT '大小',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


