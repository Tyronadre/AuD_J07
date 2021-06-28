import org.jagrkt.submitter.submit

plugins {
  java
  application
  id("org.jagrkt.submitter").version("0.3.1")
}

submit {
  assignmentId = "h07" // do not change assignmentId
  studentId = null
  firstName = null
  lastName = null
  // Optionally require tests for prepareSubmission task. Default is true
  requireTests = true
}

repositories {
  mavenCentral()
}

dependencies {
  // JUnit only available in "test" source set (./src/test)
  testImplementation("org.junit.jupiter:junit-jupiter:5.7.1")
}

java {
  sourceCompatibility = JavaVersion.VERSION_11
  targetCompatibility = JavaVersion.VERSION_11
}

application {
  mainClass.set("h07.experiment.RoadTrip")
}

tasks {
  test {
    useJUnitPlatform()
  }
}
