GlassFish Server
=================
https://javaee.github.io/glassfish

GlassFish is the reference implementation of Java EE.

Building
--------

Prerequisites:

* JDK8+
* Maven 3.0.3+

Run the full build:

`mvn install`

Locate the Zip distributions:
- appserver/distributions/glassfish/target/glassfish.zip
- appserver/distributions/web/target/web.zip

Locate staged distributions:
- appserver/distributions/glassfish/target/stage
- appserver/distributions/web/target/stage

Starting GlassFish
------------------

`glassfish5/bin/asadmin start-domain`

Stopping GlassFish
------------------

`glassfish5/bin/asadmin stop-domain`
=======
# About

GlassFish is the Open Source Java EE Reference Implementation; as such, we welcome external contributions. Make sure to read our [Pull Request acceptance workflow](pr_workflow).

# Latest News

## Sept 28, 2017 - Introducing Eclipse Enterprise for Java

See [here](https://blogs.oracle.com/theaquarium/ee4j-eclipse-enterprise-for-java).


## Sept 21, 2017 - Java EE 8 and GlassFish 5.0 Released!

See the annoucement [here](https://blogs.oracle.com/theaquarium/java-ee-8-is-final-and-glassfish-50-is-released).

## Sept 12, 2017 - Opening Up Java EE update

See [here](https://blogs.oracle.com/theaquarium/opening-up-ee-update) for an important update.

## Aug 11, 2017 - Java.Net Mailing Lists Archive ##

The Java.Net Mailing Lists Archive can now be consulted [here](http://download.oracle.com/javaee-archive/).

## July 4, 2017 - GlassFish Docker Images Update

Additional details on [how to use GF 4.1.2 and GF 5 nightly Docker images](https://blogs.oracle.com/theaquarium/glassfish-docker-images-–-update).
