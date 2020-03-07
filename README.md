## SQL script
````
CREATE TABLE `sys_permission` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `parentId` INT(11) NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `css` VARCHAR(30) DEFAULT NULL,
  `href` VARCHAR(1000) DEFAULT NULL,
  `type` INT(11) NOT NULL,
  `permission` VARCHAR(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4;

INSERT INTO `sys_permission` VALUES ('1', '0', '用户管理', '', '', '1', 'sys:user:query');
INSERT INTO `sys_permission` VALUES ('2', '1', '用户列表', '', '/api/getPage?pageName=user/user-list', '1', '');

INSERT INTO `sys_permission` VALUES ('3', '0', '科室管理', '', '', '1', '');
INSERT INTO `sys_permission` VALUES ('4', '3', '科室列表', '', '/api/getPage?pageName=dep/dep-list', '1', '');
INSERT INTO `sys_permission` VALUES ('5', '4', '新增', '', '', '2', 'sys:dep:add');
INSERT INTO `sys_permission` VALUES ('6', '4', '删除', '', '', '2', 'sys:dep:del');
INSERT INTO `sys_permission` VALUES ('7', '4', '修改', '', '', '2', 'sys:dep:edit');

INSERT INTO `sys_permission` VALUES ('8', '0', '医生管理', NULL, '', '1', '');
INSERT INTO `sys_permission` VALUES ('9', '8', '医生列表', '', '/api/getPage?pageName=doc/doc-list', '1', '');
INSERT INTO `sys_permission` VALUES ('10', '9', '新增', '', '', '2', 'sys:doc:add');
INSERT INTO `sys_permission` VALUES ('11', '9', '删除', '', '', '2', 'sys:doc:del');
INSERT INTO `sys_permission` VALUES ('12', '9', '修改', '', '', '2', 'sys:doc:edit');

INSERT INTO `sys_permission` VALUES ('13', '0', '资源管理', NULL, '', '1', 'sys:res:query');
INSERT INTO `sys_permission` VALUES ('14', '13', '轮播列表', '', '/api/getPage?pageName=pic/pic-list', '1', '');
INSERT INTO `sys_permission` VALUES ('15', '13', '文章列表', '', '/api/getPage?pageName=art/art-list', '1', '');

INSERT INTO `sys_permission` VALUES ('16', '0', '咨询管理', NULL, '', '1', 'sys:inq:query');
INSERT INTO `sys_permission` VALUES ('17', '16', '咨询列表', '', '/api/getPage?pageName=inq/inq-list', '1', '');

INSERT INTO `sys_permission` VALUES ('18', '0', '挂号管理', NULL, '', '1', 'sys:order:query');
INSERT INTO `sys_permission` VALUES ('19', '18', '挂号列表', '', '/api/getPage?pageName=order/order-list', '1', '');

INSERT INTO `sys_permission` VALUES ('20', '0', '数据源监控', 'fa-eye', 'druid/index.html', '1', 'sys:datasource:query');

-- ----------------------------

-- ----------------------------

CREATE TABLE `sys_role` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `description` VARCHAR(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`) USING BTREE
) ENGINE=INNODB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;


INSERT INTO `sys_role` VALUES ('1', 'admin', '管理员');
INSERT INTO `sys_role` VALUES ('2', 'doctor', '医生');

-- ----------------------------

-- ----------------------------

CREATE TABLE `sys_role_permission` (
  `roleId` INT(11) NOT NULL,
  `permissionId` INT(11) NOT NULL,
  PRIMARY KEY (`roleId`,`permissionId`),
  KEY `fk_sysrolepermission_permissionId` (`permissionId`),
  CONSTRAINT `fk_permission_roleId` FOREIGN KEY (`roleId`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_sysrolepermission_permissionId` FOREIGN KEY (`permissionId`) REFERENCES `sys_permission` (`id`) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

INSERT INTO `sys_role_permission` VALUES ('1', '1');
INSERT INTO `sys_role_permission` VALUES ('1', '2');
INSERT INTO `sys_role_permission` VALUES ('1', '3');
INSERT INTO `sys_role_permission` VALUES ('1', '4');
INSERT INTO `sys_role_permission` VALUES ('1', '5');
INSERT INTO `sys_role_permission` VALUES ('1', '6');
INSERT INTO `sys_role_permission` VALUES ('1', '7');
INSERT INTO `sys_role_permission` VALUES ('1', '8');
INSERT INTO `sys_role_permission` VALUES ('1', '9');
INSERT INTO `sys_role_permission` VALUES ('1', '10');
INSERT INTO `sys_role_permission` VALUES ('1', '11');
INSERT INTO `sys_role_permission` VALUES ('1', '12');
INSERT INTO `sys_role_permission` VALUES ('1', '13');
INSERT INTO `sys_role_permission` VALUES ('1', '14');
INSERT INTO `sys_role_permission` VALUES ('1', '15');
INSERT INTO `sys_role_permission` VALUES ('1', '18');
INSERT INTO `sys_role_permission` VALUES ('1', '19');
INSERT INTO `sys_role_permission` VALUES ('1', '20');
INSERT INTO `sys_role_permission` VALUES ('2', '3');
INSERT INTO `sys_role_permission` VALUES ('2', '4');
INSERT INTO `sys_role_permission` VALUES ('2', '8');
INSERT INTO `sys_role_permission` VALUES ('2', '9');
INSERT INTO `sys_role_permission` VALUES ('2', '16');
INSERT INTO `sys_role_permission` VALUES ('2', '17');

-- ----------------------------

-- ----------------------------

CREATE TABLE `sys_role_user` (
  `userId` INT(11) NOT NULL,
  `roleId` INT(11) NOT NULL,
  PRIMARY KEY (`userId`,`roleId`),
  KEY `fk_roleId` (`roleId`),
  CONSTRAINT `fk_roleId` FOREIGN KEY (`roleId`) REFERENCES `sys_role` (`id`),
  CONSTRAINT `fk_userId` FOREIGN KEY (`userId`) REFERENCES `sys_user` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

-- ----------------------------

-- ----------------------------

CREATE TABLE `sys_user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(60) NOT NULL,
  `nickname` VARCHAR(255) DEFAULT NULL,
  `headImgUrl` VARCHAR(255) DEFAULT NULL,
  `telephone` VARCHAR(30) DEFAULT NULL,
  `birthday` DATE DEFAULT NULL,
  `sex` INT(11) DEFAULT '1',
  `status` INT(11) DEFAULT '1',
  `createTime` DATETIME NOT NULL,
  `updateTime` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=INNODB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4;

-- ----------------------------

-- ----------------------------

CREATE TABLE `doctor` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `depId` INT(11) NOT NULL,
  `posId` INT(11) NOT NULL,
  `detail` VARCHAR(255),
  `intro` VARCHAR(255),
  `regCount` INT(11) DEFAULT 0,
  `inqCount` INT(11) DEFAULT 0,
  `userId` INT(11),
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4;

-- ----------------------------

-- ----------------------------

CREATE TABLE `department` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `parentId` INT(11) NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `location` VARCHAR(255),
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4;

INSERT INTO `department` VALUES ('1', '0', '骨科', '门诊楼一楼北区骨科中心');
INSERT INTO `department` VALUES ('2', '1', '关节与骨病外科','');
INSERT INTO `department` VALUES ('3', '1', '脊柱骨科','');
INSERT INTO `department` VALUES ('4', '1', '创伤骨外科','');
INSERT INTO `department` VALUES ('5', '0', '妇产科', '门诊楼二楼西区妇产科中心');
INSERT INTO `department` VALUES ('6', '5', '妇科', '');
INSERT INTO `department` VALUES ('7', '5', '产科门诊', '');
INSERT INTO `department` VALUES ('8', '5', '胎儿医学科门诊', '');
INSERT INTO `department` VALUES ('9', '0', '儿科', '');
INSERT INTO `department` VALUES ('10', '9', '小儿科', '');
INSERT INTO `department` VALUES ('11', '9', '新生儿科', '');
INSERT INTO `department` VALUES ('12', '0', '内科', '门诊楼三楼东区内科中心');
INSERT INTO `department` VALUES ('13', '12', '呼吸内科', '');
INSERT INTO `department` VALUES ('14', '12', '消化内科', '');
INSERT INTO `department` VALUES ('15', '12', '神经内科', '');
INSERT INTO `department` VALUES ('16', '12', '血液内科', '');
INSERT INTO `department` VALUES ('17', '12', '心血管内科', '');
INSERT INTO `department` VALUES ('18', '0', '外科', '门诊楼一楼南区外科中心');
INSERT INTO `department` VALUES ('19', '18', '神经外科', '');
INSERT INTO `department` VALUES ('20', '18', '肝胆外科', '');
INSERT INTO `department` VALUES ('21', '18', '肛肠科', '');
INSERT INTO `department` VALUES ('22', '18', '烧伤科', '');
INSERT INTO `department` VALUES ('23', '18', '泌尿外科', '');

-- ----------------------------

-- ----------------------------

INSERT INTO `sys_user` VALUES ('1','肥圆管理','$2a$10$Ww8SlODnhh.5iBSIWRJzhOyxcIj8nnfrpy5wXr8PcWpsfZl11w/9m','黄金宝',NULL,'15766543324','1997-09-12','1','1','2020-03-01 02:16:36','2020-03-03 15:02');
INSERT INTO `sys_user` VALUES ('2','圆润医生','$2a$10$Zgixbe609gvFwxVUGjEbZuva23bzlJFhNvSgibXIHXoh29vdsH4Xu','张翠花',NULL,'18977654440','1996-07-23','2','1','2020-03-01 02:17:03','2020-03-03 16:23');
INSERT INTO `doctor` VALUES ('1',2,1,'擅长项目...','从医数十年...',0,0,2);
INSERT INTO `sys_role_user` VALUES (1,1);
INSERT INTO `sys_role_user` VALUES (2,2);


