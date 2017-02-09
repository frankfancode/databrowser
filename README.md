# DataBrowser
> 包括两部分，查看 SharePreferences 和 Sqlite（TODO）

## DataBrowser-SharePreferences
使用 SharePreferences 做存储时，查看 是否正确的 保存、修改、清除 了数据有几种方式。
### 传统的方式
#### 打印日志
- 需要在多处添加日志代码
- 发版时还要删掉日志
- 日志太多会冲掉其他日志

#### Root 手机后使用文本应用查看

- 需要 root，有的手机不需要 root
- 需要进入  /data/data/YOUR_PACKAGE_NAME/shared_prefs/ 目录 ，麻烦
- 数据更新后 需要频繁的关闭和打开文件
- SharePreferences 进程不安全，使用 apply 方式存储时不知道何时存储完成这时使用文本应用打开文本 可能会引起数据读取不一致或者数据错乱的问题。
- 查找指定 key 数据麻烦， 所有数据都在文本中统一展示，没有分层级。

---

### databrowser-sharepreference 的方式
- 不需要打 log
- 不用 root 手机
- 不用找目录
- 不用频繁开关应用，数据更新之后，直接点击那一项就可以看到更新后的数据
- 排版相对优雅，按 key 分组
- 线程安全
- 使用 debugCompile 引入，不用担心发布上线。

#### 引入

```
debugCompile('com.frankfancode.databrowser:databrowser-sharepreference:0.0.5', {
            exclude group: 'com.android.support'
                })

```
#### 效果图
<br/>
![SharePreferences List](/screen/launchaer.png)
![SharePreferences List](/screen/splist.png)
![SharePreferences Value](/screen/spdetail.png)


## DataBrowser-Sqlite 
TODO

