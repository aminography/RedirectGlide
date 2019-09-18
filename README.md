# RedirectGlide :zap:
[![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-RedirectGlide-brightgreen.svg?style=flat )](https://android-arsenal.com/details/1/7431)
[![API](https://img.shields.io/badge/API-14%2B-ffaa00.svg?style=flat)](https://android-arsenal.com/api?level=14)
[![Download](https://api.bintray.com/packages/aminography/maven/RedirectGlide/images/download.svg) ](https://bintray.com/aminography/maven/RedirectGlide/_latestVersion)
  
## **RedirectGlide** empowers [Glide][1] to load images with indirect `URL`s.

Sometimes the images you want to show with `Glide` have indirect link and you should redirect the `URL` to reach the real direct link. **RedirectGlide** is an extension over [Glide's OkHttp3 Integration][2] which adds the redirection functionality to `Glide`.

• Currently it's implemented only for Glide-v4.

<br/>

Download
--------
Add the following lines to your `build.gradle` file:

```gradle
repositories {
    jcenter()
}
  
dependencies {
    implementation 'com.aminography:redirectglide:1.0.3'
    
    // The normal Glide dependencies
    implementation 'com.github.bumptech.glide:glide:4.9.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.9.0'
}
```

<br/>

How to use RedirectGlide?
--------
  
You can simply use **RedirectGlide** by [Glide's generated API][3]. Note that you **should clean and rebuild** your project to create the `GlideApp` class in compile time.

```java
String imageUrl = "https://bit.ly/2zeMrFB";

GlideApp.with(context)
        .load(imageUrl)
        .into(imageView);
```

![Example](screenshot.png)

<br/>

### • Customize the maximum redirection count

The default value for maximum redirection count is set to 5. However you can change it by wrapping the image `URL` with an instance of `RedirectGlideUrl`.

```java
GlideApp.with(context)
        .load(new RedirectGlideUrl(imageUrl, 10))
        .into(imageView);
```

<br/>

License
--------
```
Copyright 2019 Mohammad Amin Hassani.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

  [1]: https://github.com/bumptech/glide
  [2]: https://github.com/bumptech/glide/tree/master/integration/okhttp3
  [3]: https://bumptech.github.io/glide/doc/generatedapi.html
