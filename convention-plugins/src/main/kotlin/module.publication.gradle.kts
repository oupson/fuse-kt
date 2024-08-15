plugins {
    `maven-publish`
    signing
}

publishing {
    // Configure all publications
    publications.withType<MavenPublication> {
        // Stub javadoc.jar artifact
        artifact(tasks.register("${name}JavadocJar", Jar::class) {
            archiveClassifier.set("javadoc")
            archiveAppendix.set(this@withType.name)
        })

        // Provide artifacts information required by Maven Central
        pom {
            name.set("fuse-kt")
            description.set("A library to write fuse client or servers")
            url.set("https://github.com/oupson/fuse-kt")

            licenses {
                license {
                    name.set("BSD-3-Clause")
                    url.set("https://opensource.org/license/bsd-3-clause")
                }
            }
            developers {
                developer {
                    id.set("oupson")
                    name.set("oupson")
                }
            }
            scm {
                url.set("https://github.com/oupson/fuse-kt")
            }
        }
    }
}

signing {
    if (project.hasProperty("signing.gnupg.keyName")) {
        useGpgCmd()
        sign(publishing.publications)
    }
}
