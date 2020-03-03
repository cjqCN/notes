# nginx 编译安装

## 需要的工具
`sudo apt install gcc libpcre3 libpcre3-dev  zlib1g zlib1g.dev make` 

## 编译执行
- `./configure --prefix=/home/cjq/nginx`
- `make`

编译后，目标文件放在`objs`目录下

## 安装
`make install`

## 安装完毕
安装目录在`/home/cjq/ngnix`, 配置 `/home/cjq/ngnix/config`

## 命令(在安装目录下执行)
- 校验配置 `./sbin/nginx -t`
- 启动 `./sbin/nginx [-c $config_path]`
- 重新加载配置 `./sbin/nginx -s reload`
- 停止 `./sbin/nginx -s stop`
- 优雅退出 `./sbin/nginx -s quit`
- 日志截断 `./sbin/nginx -s reopen` (使用`mv`备份归档旧日志, 也对应 kill -USR1 $pid)
-------------------------
### 版本升级步骤
- 将老版本二进制文件备份(mv nginx nginx.old)
- 通知旧 master 优雅升级`kill -USR2  $old_pid`, 该步骤后拉起新的 master 和新的 worker
- 旧 master 关闭旧 worker`kill -WINCH  $old_pid`, 该步骤后旧的 worker 全部关闭
- 回滚到旧版本，`kill -HUP  $old_pid`, 该步骤后重新拉起旧的 worker, 对应 reload 命令




