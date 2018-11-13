# RedirectGlide :zap:
[![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-RedirectGlide-brightgreen.svg?style=flat )]( https://android-arsenal.com/details/1/7178)
[![API](https://img.shields.io/badge/API-14%2B-ffaa00.svg?style=flat)](https://android-arsenal.com/api?level=14)
[![Download](https://api.bintray.com/packages/aminography/maven/RedirectGlide/images/download.svg) ](https://bintray.com/aminography/maven/RedirectGlide/_latestVersion)
  
**RedirectGlide** empowers `Glide` to load images with indirect `URL`s.

Sometimes the images you want to show with `Glide` have indirect link and you should redirect the `URL` to reach real direct link. **RedirectGlide** is an extension over [Glide-OkHttp3-Integration][1]


Download
--------
Add the following lines to your `build.gradle` file:

```gradle
repositories {
    jcenter()
}
  
dependencies {
    implementation 'com.aminography:redirectglide:1.0.0'
}
```

How to use RedirectGlide?
--------
  
You should create both ...

  [1]: https://github.com/bumptech/glide/tree/master/integration/okhttp3
