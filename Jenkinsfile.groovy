final String publicRepo = 'https://github.com/kemitix/'
final String mvn = "mvn --batch-mode --update-snapshots --errors"

pipeline {
    agent any
    stages {
        stage('Build Java 8') {
            steps {
                withMaven(maven: 'maven', jdk: 'JDK 8') {
                    sh "${mvn} clean verify -Djava.version=8"
                }
            }
        }
        stage('Build Java 11') {
            steps {
                withMaven(maven: 'maven', jdk: 'JDK 11') {
                    sh "${mvn} clean verify -Djava.version=11"
                }
            }
        }
        stage('Build Java 12') {
            steps {
                withMaven(maven: 'maven', jdk: 'JDK 12') {
                    sh "${mvn} clean verify -Djava.version=12"
                }
	    }
        }
        stage('Build Java 13') {
            steps {
                withMaven(maven: 'maven', jdk: 'JDK 13') {
                    sh "${mvn} clean verify -Djava.version=13"
                }
            }
        }
    }
}

private boolean isReleaseBranch() {
    return branchStartsWith('release/')
}

private boolean branchStartsWith(final String branchName) {
    startsWith(env.GIT_BRANCH, branchName)
}

private boolean isPublished(final String repo) {
    startsWith(env.GIT_URL, repo)
}

private static boolean startsWith(final String value, final String match) {
    value != null && value.startsWith(match)
}

private boolean notSnapshot() {
    return !(readMavenPom(file: 'pom.xml').version).contains("SNAPSHOT")
}
