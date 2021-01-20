# bottom

[![Build Status][GHAction-image]][GHAction-link]
[![LICENSE][LICENSE-image]][LICENSE-link]
[![JitPack][JitPack-image]][JitPack-link]
[![docs][javadocs-image]][javadocs-link]

A Gradle library for [bottom](https://github.com/kaylynn234/bottom), following the [bottom spec](https://github.com/kaylynn234/bottom-spec).

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
     implementation 'com.github.sebbylaw:bottom-java:1.0.1'
}
```

## Quick Example

```java
import com.github.sebbylaw.bottom.Bottom;

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

- [bottom](https://github.com/kaylynn234/bottom) (Rust/CLI)
- [bottom-py](https://github.com/uYert/bottom-py) (Python Rust bindings)
- [bottom-c](https://github.com/LyricLy/bottom-c) (C)
- [bottom-hs](https://github.com/LyricLy/bottom-hs) (Haskell)
- [bottom-web](https://github.com/kaylynn234/bottom-web/) (Website at https://kaylynn234.github.io/bottom-web/)
- [bottom_ex](https://github.com/oliver-ni/bottom_ex) (Elixir)
- [bottom-kotlin](https://github.com/XuaTheGrate/bottom-kotlin) (Kotlin)

## Why?

Learning project for Gradle, JUnit4, Github Actions, and JitPack.

[GHAction-image]: https://github.com/SebbyLaw/bottom-java/workflows/CI/badge.svg?branch=master&event=push
[GHAction-link]: https://github.com/SebbyLaw/bottom-java/actions?query=event%3Apush+branch%3Amaster
[LICENSE-image]: https://img.shields.io/github/license/SebbyLaw/bottom-java
[LICENSE-link]: https://github.com/SebbyLaw/bottom-java/blob/master/LICENSE
[JitPack-image]: https://jitpack.io/v/com.github.sebbylaw/bottom-java.svg
[JitPack-link]: https://jitpack.io/#com.github.sebbylaw/bottom-java
[javadocs-image]: https://github.com/SebbyLaw/bottom-java/workflows/docs/badge.svg?branch=master&event=push
[javadocs-link]: https://sebbylaw.github.io/bottom-java
