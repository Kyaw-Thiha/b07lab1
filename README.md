# BO7 Lab1
My TA bullied me into creating this repo. HELPPPPPP!!!!

## Instructions
- Ensure `gradle` is installed on your machine.
- For each new lab, change the root project package in `settings.gradle`.

### Generate Gradle
Generate gradle wrappers.
```bash
gradle wrapper
```

### Build
```bash
./gradlew build
```

### Testing
For running test codes
```bash
./gradlew test
# single test class
./gradlew test --tests 'lab3.Lab3Tests'
# single test method
./gradlew test --tests 'lab3.Lab3Tests.someMethod'
```

### Doc Generation
```bash
./gradlew javadoc
```

