## Change log
----------------------

Version 4.5-SNAPSHOT
-------------



Version 4.4
-------------

ADDED:

- new gradle-plugin org.ajoberstar.grgit:grgit-gradle in version 4.1.1 for create git tags
- new gradle options for improve gradle build performance
- new methods for split properties keys to parts in List and to concatenate properties parts back again

CHANGED:

- update gradle to new version 7.3.3
- update of com.github.ben-manes.versions.gradle.plugin to new version 0.41.0
- update of dependency jobj-core version to 5.2
- update of dependency file-worker version to 8.1
- update of dependency snakeyaml version to 1.30
- update of test dependency test-objects version to 5.7
- update of test dependency jobj-contract-verifier version to 3.5
- update of test dependency testng version to 7.5

Version 4.3
-------------

CHANGED:

- downgrade to openjdk8

Version 4.2
-------------

ADDED:

- new class PropertiesKeyExtensions provides methods for split properties keys to parts and to concatenate properties parts back again
- new class NgxTranslateJsonFileToPropertiesFile that can convert a ngx-translate json file to a java properties file with the corresponding line number
- new dependency jsonExtensions in the version 1.2 
- new dependency gsonExtensions in the version 1.5

CHANGED:

- update of dependency file-worker version to 8

Version 4.1
-------------

ADDED:

- new dependency throw-able added in version 1.7

CHANGED:

- update of gradle to new version 7.2
- changed to new package io.github.astrapi69
- increase code coverage
- update of dependency lombok version to 1.18.22
- update of dependency snakeyaml version to 1.29
- update of dependency file-worker version to 5.10
- update of dependency commons-io version to 2.11.0
- update of dependency silly-collections version to 18
- update of dependency silly-io version to 1.6
- update of dependency silly-beans version to 1.2
- update of dependency jobj-core version to 3.9
- update of test dependency test-objects version to 5.5
- update of test dependency meanbean-factories version to 1.3
- update of test dependency testng version to 7.4.0

Version 4
-------------

ADDED:
 
- new method for get Locale object from language code and country code
- added new constant for a regular expression pattern that can find comments in properties file
- new idea run configurations for gradle builds created

CHANGED:

- update jdk from version 8 to 11
- update of gradle to new version 6.7.1
  
- update of dependency lombok version to 1.18.16
- update of dependency snakeyaml version to 1.27
- update of dependency file-worker version to 5.7
- update of dependency commons-io version to 2.8.0
- update of dependency silly-collections version to 8.4
- update of dependency silly-io version to 1.3
- update of dependency silly-beans version to 1.1
- update of dependency jobj-core version to 3.6
- update of test dependency test-objects version to 5.3
- update of test dependency meanbean-factories version to 2
- update of test dependency testng version to 7.3.0
- remove of test dependency junit version 
- remove of test dependency mockito-core 
- tagged method getLocale with country code as deprecated
- deleted obsolete eclipse launch scripts
- extracted project properties to gradle.properties
- extracted project gradle plugin versions to buildscript.ext area in gradle.properties

Version 3.2
-------------

ADDED:
 
- added gradle nature to project

CHANGED:

- changed from mvn module project to simple project
- removed maven nature

Version 3.1
-------------

ADDED:
 
- new dependency jobj-core added in version 3.1

CHANGED:

- update of test-objects version to 5.2
- update of jobject-clone version to 3.1.2
- update of jobj-contract-verifier version to 3.1.2

Version 3.0.3
-------------

ADDED:
 
- new dependency silly-beans added in version 1

CHANGED:

- removed dependency jcommons-lang

Version 3.0.2
-------------

ADDED:
 
- new dependency silly-io added in version 1.1

CHANGED:

- update of parent version to 4.8
- update of jcommons-lang to 5.2.1
- update of jobject-extensions to 3.1.1
- update of file-worker version to 5.2
- update of nv-i18n version to 1.25

Version 3.0.1
-------------

CHANGED:

- update of parent version to 4.4
- update of jcommons-lang to 5.1
- update of silly-collections version to 5
- update of jobject-extensions to 2.5
- update of file-worker version to 5.0.1
- update of meanbean-factories to 1.1.1
- removed logging dependencies

Version 3
-------------

CHANGED:

- update of parent version to 4.1
- update of jcommons-lang to 4.35
- update of silly-collections version to 4.33
- update of jobject-extensions to 1.12
- update of file-worker version to 4.23

Version 2.22
-------------

CHANGED:

- update of parent version to 4
- update of jcommons-lang to 4.34
- update of test-objects version to 4.28
- update of silly-collections version to 4.31
- update of jobject-extensions to 1.11
- update of meanbean-factories to 1.1

Version 2.21
-------------

CHANGED:

- update of parent version to 3.11
- removed unneeded .0 at the end of version
- update of file-worker version to 4.20
- update of silly-collections version to 4.28.0

Version 2.20.0
-------------

CHANGED:

- update of parent version to 3.10.0
- overload method getCountryName with new parameter defaultCountryName
- update of silly-collections version to 4.27.0
- update of test-objects version to 4.24.0
- update of jcommons-lang to 4.30.0
- update of jobject-extensions to 1.10.0

Version 2.19.0
-------------

ADDED:
 
- new method for format a pattern string with arguments in ResourceBundleExtensions
- new method for get the country name from a given country code
- new method for get the language name from a given language code

CHANGED:

- update of parent version to 3.9.0
- removed deprecated methods from class PropertiesFileExtensions
- update of silly-collections version to 4.25.0
- update of test-objects version to 4.23.0

Version 2.18.0
-------------

ADDED:
 
- new unit tests 

CHANGED:

- update of parent version and of dependencies versions
- javadoc extended and improved
- unit tests extended 
- improve of code coverage

Version 2.17.0
-------------

ADDED:
 
- this changelog file
- provide package.html for the javadoc of new packages
- Donation buttons extended for paypal and bitcoin
- added new meanbean dependency for better unit testing of beans

CHANGED:

- update of parent version and of dependencies versions
- javadoc extended and improved

Notable links:
[keep a changelog](http://keepachangelog.com/en/1.0.0/) Don’t let your friends dump git logs into changelogs
