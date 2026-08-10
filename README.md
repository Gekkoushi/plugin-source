# plugin-source

This project provides a collection of utilities and some parsers for convenient access to any content available on the web. It can be used in
JVM and Android applications.

![Sources count](https://img.shields.io/badge/dynamic/yaml?url=https%3A%2F%2Fraw.githubusercontent.com%2FYakaTeam%2Fkotatsu-parsers%2Frefs%2Fheads%2Fmaster%2F.github%2Fsummary.yaml&query=total&label=manga%20sources&color=%23E9321C) [![](https://jitpack.io/v/YakaTeam/kotatsu-parsers.svg)](https://jitpack.io/#YakaTeam/kotatsu-parsers) [![Build](https://github.com/YakaTeam/kotatsu-parsers/actions/workflows/test-branch.yml/badge.svg?branch=master)](https://github.com/YakaTeam/kotatsu-parsers/actions/workflows/test-branch.yml) ![License](https://img.shields.io/github/license/YakaTeam/kotatsu-parsers)

## Requirements

- Android Studio or IntelliJ IDEA (Community Edition is enough)
- Android SDK 35 or later (if not using IDE)
- Java 11 or later is required

## Building

**Open Terminal with root folder:**

  On Linux & Unix system:
		```bash
		chmod +x gradlew && ./gradlew buildJar
	 	```

  On Windows system:
    ```cmd
    .\gradlew.bat buildJar
    ```

**More simply, just run `buildJar` task in Android Studio / IntelliJ IDEA and dex it after building.**

Versions are available on [JitPack](https://jitpack.io/#Gekkoushi/plugin-source)
	
When used in Android
projects, [core library desugaring](https://developer.android.com/studio/write/java8-support#library-desugaring) with
the [NIO specification](https://developer.android.com/studio/write/java11-nio-support-table) should be enabled to support Java 8+ features.

## Contribution

See [CONTRIBUTING.md](./CONTRIBUTING.md) for the guidelines.

### License

[![GNU GPLv3 Image](https://www.gnu.org/graphics/gplv3-127x51.png)](http://www.gnu.org/licenses/gpl-3.0.en.html)

<div align="left">

You may copy, distribute and modify the software as long as you track changes/dates in source files. Any modifications
to or software including (via compiler) GPL-licensed code must also be made available under the GPL along with build &
install instructions. See [LICENSE](./LICENSE) for more details.

</div>

### License

[![GNU GPLv3 Image](https://www.gnu.org/graphics/gplv3-127x51.png)](http://www.gnu.org/licenses/gpl-3.0.en.html)

### Disclaimer

**`¯\_(ツ)_/¯`**

This repository has been built by contributors / users, the content inside has been provided by **[Gemini](https://gemini.google.com/)**, but where is it, no one knows. No one knows how it works.
