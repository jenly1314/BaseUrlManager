# BaseUrlManager

[![MavenCentral](https://img.shields.io/maven-central/v/com.github.jenly1314/base-url-manager?logo=sonatype)](https://repo1.maven.org/maven2/com/github/jenly1314/BaseUrlManager)
[![JitPack](https://img.shields.io/jitpack/v/github/jenly1314/BaseUrlManager?logo=jitpack)](https://jitpack.io/#jenly1314/BaseUrlManager)
[![CI](https://img.shields.io/github/actions/workflow/status/jenly1314/BaseUrlManager/gradle.yml?logo=github)](https://github.com/jenly1314/BaseUrlManager/actions/workflows/gradle.yml)
[![Download](https://img.shields.io/badge/download-APK-brightgreen?logo=github)](https://raw.githubusercontent.com/jenly1314/BaseUrlManager/master/app/release/app-release.apk)
[![API](https://img.shields.io/badge/API-16%2B-brightgreen?logo=android)](https://developer.android.com/guide/topics/manifest/uses-sdk-element#ApiLevels)
[![License](https://img.shields.io/github/license/jenly1314/BaseUrlManager?logo=open-source-initiative)](https://opensource.org/licenses/mit)

BaseUrlManager for Android 的设计初衷主要用于开发时，有多个环境需要打包APK的场景，通过BaseUrlManager提供的BaseUrl动态设置入口，只需打一
次包，即可轻松随意的切换不同的开发环境或测试环境。在打生产环境包时，关闭BaseUrl动态设置入口即可。

> 妈妈再也不用担心因环境不同需要打多个包的问题，从此告别环境不同要写一堆配置的烦恼，真香。

> 配合[ **RetrofitHelper** ](https://github.com/jenly1314/RetrofitHelper)动态改变BaseUrl一起使用更香。

## 效果展示
![Image](GIF.gif)

> 你也可以直接下载 [演示App](https://raw.githubusercontent.com/jenly1314/BaseUrlManager/master/app/release/app-release.apk) 体验效果

## 引入

### Gradle:

1. 在Project的 **build.gradle** 或 **setting.gradle** 中添加远程仓库

    ```gradle
    repositories {
        //...
        mavenCentral()
    }
    ```

2. 在Module的 **build.gradle** 中添加依赖项

    ```gradle
    //AndroidX 版本
    implementation 'com.github.jenly1314:base-url-manager:1.2.0'
    ```
## 使用

集成步骤代码示例 （示例出自于[app](app)中）

Step.1 在您项目中的AndroidManifest.xml中通过配置meta-data来自定义全局配置
```xml
    <!-- 在你项目中添加注册如下配置 -->
    <activity android:name="com.king.base.baseurlmanager.BaseUrlManagerActivity"
        android:screenOrientation="portrait"
        android:theme="@style/BaseUrlManagerTheme"/>
```

Step.2 在您项目Application的onCreate方法中初始化BaseUrlManager

```java
    //获取BaseUrlManager实例（适用于v1.1.x版本）
    mBaseUrlManager = BaseUrlManager.getInstance();

    //获取BaseUrlManager实例（适用于v1.0.x旧版本）
    mBaseUrlManager = new BaseUrlManager(this);

    //获取baseUrl
    String baseUrl = mBaseUrlManager.getBaseUrl();

```

Step.3 提供动态配置BaseUrl的入口（通过Intent跳转到BaseUrlManagerActivity界面）

v.1.1.x 新版本写法
```JAVA
   BaseUrlManager.getInstance().startBaseUrlManager(this,SET_BASE_URL_REQUEST_CODE);

```

v1.0.x 以前版本写法
```JAVA
    Intent intent = new Intent(this, BaseUrlManagerActivity.class);
    //BaseUrlManager界面的标题
    //intent.putExtra(BaseUrlManagerActivity.KEY_TITLE,"BaseUrl配置");
    //跳转到BaseUrlManagerActivity界面
    startActivityForResult(intent,SET_BASE_URL_REQUEST_CODE);
```

Step.4 当配置改变了baseUrl时，在Activity或Fragment的onActivityResult方法中重新获取baseUrl即可
```java

    //方式1：通过BaseUrlManager获取baseUrl
    String baseUrl = BaseUrlManager.getInstance().getBaseUrl();
    //方式2：通过data直接获取baseUrl
    UrlInfo urlInfo = BaseUrlManager.parseActivityResult(data);
    String baseUrl = urlInfo.getBaseUrl();

```

更多使用详情，请查看[app](app)中的源码使用示例或直接查看 [API帮助文档](https://jitpack.io/com/github/jenly1314/BaseUrlManager/latest/javadoc/)

## 相关推荐

- [RetrofitHelper](https://github.com/jenly1314/RetrofitHelper) 一个为 Retrofit 提供便捷配置多个BaseUrl相关的扩展帮助类。
- [LogX](http://github.com/jenly1314/LogX) 一个轻量而强大的日志框架；好用不解释。
- [KVCache](http://github.com/jenly1314/KVCache) 一个便于统一管理的键值缓存库；支持无缝切换缓存实现。
- [AndroidKTX](http://github.com/AndroidKTX/AndroidKTX) 一个简化 Android 开发的 Kotlin 工具类集合。
- [AndroidUtil](http://github.com/AndroidUtil/AndroidUtil) 一个整理了Android常用工具类集合，平时在开发的过程中可能会经常用到。
- [AppUpdater](http://github.com/jenly1314/AppUpdater) 一个专注于App更新，一键傻瓜式集成App版本升级的轻量开源库。
- [MVVMFrame](https://github.com/jenly1314/MVVMFrame) 一个基于Google官方推出的JetPack构建的MVVM快速开发框架。
- [AppTemplate](https://github.com/jenly1314/AppTemplate) 一款基于 MVVMFrame 构建的App模板。

## 版本日志

#### v1.2.0：2022-1-25 (从v1.2.0开始发布至 MavenCentral)
*  优化细节

#### v1.1.1：2021-1-28
*  新增支持长按复制相关功能

#### v1.1.0：2020-12-4
*  输入的url支持正则校验
*  后续版本只支持androidx，版本名称不再带有androidx标识

#### v1.0.1：2019-7-5
*  移除strings.xml资源中的app_name
*  支持不依赖刷新数据，直接通过onActivityResult获取baseUrl信息

#### v1.0.0：2019-6-11   [支持AndroidX版本](https://github.com/jenly1314/BaseUrlManager/tree/androidx)
*  BaseUrlManager初始版本

---

![footer](https://jenly1314.github.io/page/footer.svg)
