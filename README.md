# Overview

<div align="center">

[![Build Status](https://travis-ci.org/astrapi69/resourcebundle-core.svg?branch=develop)](https://travis-ci.org/astrapi69/resourcebundle-core)
[![Coverage Status](https://codecov.io/gh/astrapi69/resourcebundle-core/branch/develop/graph/badge.svg)](https://codecov.io/gh/astrapi69/resourcebundle-core)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/de.alpharogroup/resourcebundle-core/badge.svg)](https://maven-badges.herokuapp.com/maven-central/de.alpharogroup/resourcebundle-core)
[![Javadocs](http://www.javadoc.io/badge/de.alpharogroup/resourcebundle-inspector.svg)](http://www.javadoc.io/doc/de.alpharogroup/resourcebundle-inspector)
[![MIT license](http://img.shields.io/badge/license-MIT-brightgreen.svg?style=flat)](http://opensource.org/licenses/MIT)

</div>

The resourcebundle-core project can inspect projects and find unused properties keys or duplicate keys.
		
## License

The source code comes under the liberal MIT License, making resourcebundle-inspector great for all types of applications.

## Maven dependency

Maven dependency is now on sonatype.
Check out [sonatype repository](https://oss.sonatype.org/#nexus-search;quick~resourcebundle-inspector) for latest snapshots and releases.

Add the following maven dependency to your project `pom.xml` if you want to import the core functionality of resourcebundle-core:

Than you can add the dependency to your dependencies:

		<!-- RESOURCEBUNDLE-CORE version -->
		<resourcebundle-core.version>4</resourcebundle-core.version>
		
		<dependencies>
			...
		    <!-- RESOURCEBUNDLE-CORE DEPENDENCY -->
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>resourcebundle-core</artifactId>
				<version>${resourcebundle-core.version}</version>
			</dependency>
			...
		</dependencies>	
			
## gradle dependency

You can first define the version in the ext section and add than the following gradle dependency to 
your project `build.gradle` if you want to import the core functionality of resourcebundle-core:

```
define version in file gradle.properties

resourcebundleCoreVersion=4
```

or in build.gradle ext area

```
ext {
			...
    resourcebundleCoreVersion = "4"
			...
}
```

and than add the dependency to the dependencies area

```
dependencies {
			...
    implementation("de.alpharogroup:resourcebundle-core:$resourcebundleCoreVersion")
			...
}
```

## Semantic Versioning

The versions of resourcebundle-core are maintained with the Semantic Versioning guidelines.

Release version numbers will be incremented in the following format:

`<major>.<minor>.<patch>`

For detailed information on versioning for this project you can visit this [wiki page](https://github.com/lightblueseas/mvn-parent-projects/wiki/Semantic-Versioning).

## Want to Help and improve it? ###

The source code for resourcebundle-core are on GitHub. Please feel free to fork and send pull requests!

Create your own fork of [astrapi69/resourcebundle-core/fork](https://github.com/astrapi69/resourcebundle-core/fork)

To share your changes, [submit a pull request](https://github.com/astrapi69/resourcebundle-core/pull/new/develop).

Don't forget to add new units tests on your changes.

## Contacting the Developers

Do not hesitate to contact the resourcebundle-core developers with your questions, concerns, comments, bug reports, or feature requests.
- Feature requests, questions and bug reports can be reported at the [issues page](https://github.com/astrapi69/resourcebundle-core/issues).

## Note

No animals were harmed in the making of this library.

# Donations

This project is kept as an open source product and relies on contributions to remain being developed. 
If you like this project, please consider a donation through paypal: <a href="https://www.paypal.com/cgi-bin/webscr?cmd=_donations&business=GUGLK2W5PX29G&lc=GB&item_name=Open%2dsource%20developer&no_note=0&cn=Notice&no_shipping=1&currency_code=EUR&bn=PP%2dDonationsBF%3abtn_donateCC_LG%2egif%3aNonHostedGuest" target="_blank">
<img src="https://www.paypalobjects.com/en_US/GB/i/btn/btn_donateCC_LG.gif" alt="PayPal this" title="PayPal – The safer, easier way to pay online!" border="0" />
</a>

or over bitcoin or bitcoin-cash with:

1Jzso5h7U82QCNmgxxSCya1yUK7UVcSXsW

or over ether with:

0xaB6EaE10F352268B0CA672Dd6e999C86344D49D8

or over flattr:
  
<a href="http://flattr.com/thing/4180911/astrapi69resourcebundle-inspector-on-GitHub" target="_blank">
<img src="http://api.flattr.com/button/flattr-badge-large.png" alt="Flattr this" title="Flattr this" border="0" />
</a>

# Similar projects

Here is a list of awesome projects for resource bundles:

* [ResourceCheck](http://rscbundlecheck.sourceforge.net/) Ant Task for checking Java Resourcebundles. [sourceforge.net](https://sourceforge.net/projects/rscbundlecheck/)
* [nv-i18n](https://github.com/TakahikoKawasaki/nv-i18n) Package to support internationalization, containing ISO 3166-1 country code enum, ISO 639-1 language code enum, etc.

## Credits

|Travis CI|
|:-:|
|![Travis CI](https://travis-ci.com/images/logos/TravisCI-Full-Color.png)|
|Many thanks to [Travis CI](https://travis-ci.org) for providing a free continuous integration service for open source projects.|

