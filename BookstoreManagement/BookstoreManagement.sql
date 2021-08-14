create table t_user
(
    `id`       int primary key auto_increment,
    `username` varchar(20) not null unique,
    `password` varchar(32) not null,
    `email`    varchar(200)
);

insert into t_user(`username`, `password`, `email`)
values ('aidan', '123', 'aidan@123.com');

# 书籍表
create table t_book
(
    `id`       int primary key auto_increment,
    `name`     varchar(100),
    `price`    decimal(11, 2),
    `author`   varchar(100),
    `sales`    int,
    `stock`    int,
    `img_path` varchar(200)
);


# 插入初始化测试数据
insert into t_book(`id`, `name`, `author`, `price`, `sales`, `stock`, `img_path`)
values (null, '命运：文在寅自传', '文在寅', 80, 9999, 9, 'static/img/default.jpg'),
       (null, '潜规则：中国历史中的真实游戏', '吴思', 46, 44444, 334, 'static/img/default.jpg'),
       (null, '血酬定律：中国历史中的生成游戏', '吴思', 46, 44444, 334, 'static/img/default.jpg'),
       (null, '君主论', '马基雅维利', 46, 44444, 334, 'static/img/default.jpg'),
       (null, '吃鸟的女孩', '萨曼塔·施维伯林', 46, 44444, 334, 'static/img/default.jpg');

# 订单表
create table t_order
(
    `order_id`    varchar(50) primary key,
    `create_time` datetime,
    `price`       decimal(11, 2),
    `status`      int,
    `user_id`     int,
    foreign key (`user_id`) references t_user (`id`)
);

# 订单项
create table t_order_item
(
    `id`          int primary key auto_increment,
    `name`        varchar(100),
    `count`       int,
    `price`       decimal(11, 2),
    `total_price` decimal(11, 2),
    `order_id`    varchar(50),
    foreign key (`order_id`) references t_order (`order_id`)
);