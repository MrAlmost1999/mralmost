## 差不多先生博客

## sql脚本:
```sql
CREATE TABLE USER
(
    ID int AUTO_INCREMENT PRIMARY KEY NOT NULL,
    ACCOUNT_ID VARCHAR(100),
    NAME VARCHAR(50),
    TOKEN VARCHAR(36),
    GMT_CREATE BIGINT,
    GMT_MODIFIED BIGINT
);

alter table USER add bio varchar(256) null;

create table question
(
	id int auto_increment,
	title varchar(50),
	description text,
	gmt_create bigint,
	gmt_modified bigint,
	creator int,
	comment_count int default 0,
	view_count int default 0,
	like_count int default 0,
	tag varchar(256),
	constraint question_pk
		primary key (id)
);


```
## 在Terminal中使用到的所有命令:
```text
1.使用SSH的方式克隆仓库:
    git clone git@github.com:MrAlmost1999/demo.git
2.把新修改的文件添加index区:
    git add 文件名(.表示当前目录)
3.查看当前状态:	
    git status
4.提交到GitHub中
    git commit -m "备注"
5.设置邮箱和用户名:
    $ git config --global user.name = MrAlmost1999
    $ git config --global user.email  2830114286@qq.com
6.查看操作日志:
    git log
7.查看上次提交的内容:
    git show d2c8ccf6d807084233799962f7181b343599ba0d
8.回滚上次提交的操作:
    git reset d2c8ccf6d807084233799962f7181b343599ba0d
9.把修改发送到远端中去:
    git push
10.创建分支:
    git branch 分支名
11.切换到当前分支:
    git checkout 分支名
12.合并以上两个命令:
    创建:git checkout -b 分支名
    删除:git branch -d 分支名
13.拉取远端最新的代码:
    git pull
14.将指定分区的内容合并到当前分区:
    git merge 指定分区
15.查看两个分区的差异:
    git diff <源分区> <目标分区>
16.操作失误时从HEAD从修改本地的提交:
    git checkout -- <filename>
17.在未push之前追吧内容追加到上一个commit中:
    git commit --amend(表示追加) --no-edit(不修改备注) 
18.使用flyway对数据库进行控制:
    mvn flyway:migrate
```