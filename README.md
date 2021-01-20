# bottom-java

[![Build Status][GHAction-image]][GHAction-link]
[![LICENSE][LICENSE-image]][LICENSE-link]
[![JitPack][JitPack-image]][JitPack-link]
[![docs][javadocs-image]][javadocs-link]

A Gradle library for bottom, following the [bottom spec](https://github.com/bottom-software-foundation/bottom-spec).

Javadocs [here][javadocs-link]

## Installing

You can install bottom [from JitPack][JitPack-link]

Add bottom to your Gradle project by adding these lines to your `build.gradle` file:
```groovy
repositories {
     // other repositories
     mavenCentral()
     maven { url 'https://jitpack.io' }
}

dependencies {
     // other dependencies
     implementation 'com.github.bottom-software-foundation:bottom-java:1.1.0'  // latest stable release
     // alternatively...
     implementation 'com.github.bottom-software-foundation:bottom-java:need_top-SNAPSHOT'  // latest commit on github
}
```

## Quick Example

```java
import com.github.bottomSoftwareFoundation.bottom.Bottom;

public class Main {
    public static void main(String[] args) {
        String string = "Hello world!";
        String bottom = Bottom.encode(string);
        String decoded = Bottom.decode(bottom);
        
        System.out.println(string);
        // Hello world!
        System.out.println(bottom);
        // 💖✨✨,,👉👈💖💖,👉👈💖💖🥺,,,👉👈💖💖🥺,,,👉👈💖💖✨,👉👈✨✨✨,,👉👈💖💖✨🥺,,,,👉👈💖💖✨,👉👈💖💖✨,,,,👉👈💖💖🥺,,,👉👈💖💖👉👈✨✨✨,,,👉👈
        System.out.println(decoded);
        // Hello world!
    }
}
```

## More bottom

https://github.com/bottom-software-foundation/awesome-bottom

## Why?

Learning project for Gradle, JUnit4, Github Actions, and JitPack.

[GHAction-image]: https://github.com/bottom-software-foundation/bottom-java/workflows/CI/badge.svg?branch=master&event=push
[GHAction-link]: https://github.com/bottom-software-foundation/bottom-java/actions?query=event%3Apush+branch%3Amaster
[LICENSE-image]: https://img.shields.io/github/license/bottom-software-foundation/bottom-java
[LICENSE-link]: https://github.com/bottom-software-foundation/bottom-java/blob/master/LICENSE
[JitPack-image]: https://jitpack.io/v/com.github.bottom-software-foundation/bottom-java.svg
[JitPack-link]: https://jitpack.io/#com.github.bottom-software-foundation/bottom-java
[javadocs-image]: https://github.com/bottom-software-foundation/bottom-java/workflows/docs/badge.svg?branch=master&event=push
[javadocs-link]: https://bottom-software-foundation.github.io/bottom-java
