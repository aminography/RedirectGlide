# RedirectGlide :zap:
[![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-RedirectGlide-brightgreen.svg?style=flat )]( https://android-arsenal.com/details/1/7178)
[![API](https://img.shields.io/badge/API-14%2B-ffaa00.svg?style=flat)](https://android-arsenal.com/api?level=14)
[![Download](https://api.bintray.com/packages/aminography/maven/RedirectGlide/images/download.svg) ](https://bintray.com/aminography/maven/RedirectGlide/_latestVersion)
  
## **RedirectGlide** empowers [Glide][1] to load images with indirect `URL`s.

Sometimes the images you want to show with `Glide` have indirect link and you should redirect the `URL` to reach real direct link. **RedirectGlide** is an extension over [Glide's OkHttp3 Integration][2] which adds the redirection functionality to `Glide`.

• Now it's implemented only for Glide-v4.

Download
--------
Add the following lines to your `build.gradle` file:

```gradle
repositories {
    jcenter()
}
  
dependencies {
    implementation 'com.aminography:redirectglide:1.0.0'
    
    // The normal Glide dependencies
    implementation 'com.github.bumptech.glide:glide:4.8.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.8.0'
}
```

How to use RedirectGlide?
--------
  
You can simply use **RedirectGlide** by [Glide's generated API][3]. Note that you **should clean and rebuild** your project to create the `GlideApp` class in compile time.

```java
String imageUrl = "https://bit.ly/2zeMrFB";

GlideApp.with(getApplicationContext())
        .load(imageUrl)
        .into(imageView);
```

![Example](screenshot.png)

### • Customize the maximum redirection count

The default value for maximum redirection count is set to 5. However you can change it by wrapping the image `URL` with an instance of `RedirectGlideUrl`.

```java
GlideApp.with(getApplicationContext())
        .load(new RedirectGlideUrl(imageUrl, 10))
        .into(imageView);
```

  [1]: https://github.com/bumptech/glide
  [2]: https://github.com/bumptech/glide/tree/master/integration/okhttp3
  [3]: https://bumptech.github.io/glide/doc/generatedapi.html
