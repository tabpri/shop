insert into `role` (`role_name`, `role_description`) values('SHOPPING_CART_OPERATOR_ADMIN','Admin who has access to the entire Admin Panel');
insert into `role` (`role_name`, `role_description`) values('SHOPPING_CART_OPERATOR','Shopping cart operator role');

insert into `user` (`name`, `email`, `password`, `created_by`, `updated_by`, `created_date`, `updated_date`, `removed`) values('default admin','admin@secual-inc.com','test1234','1','1',NOW(),NOW(),'0');
insert into `user_roles` (`user_fk`, `role_fk`) values('1','1');

insert into `static_data` (`ID`, `FROMADDRESS`, `SITENAME`, `BASEPATH`, `UNIX`, `MAPEVENTSPAN`, `NOIMAGE`, `CARRIAGE`, `CREDITCARDPROCESURL`, `CONTRACT_CODE`, `ADMIN_EMAILADDRESSES`) values('1','test@staging.shop.com','staging.shop','','0','0','','0','','','aandl.order@gmail.com,toukubo+africaandleo@gmail.com');