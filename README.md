# HeadlineTextProcessor

## Problem-Statement:

 Given the file headlines.zip which contains a CSV file of the headlines and the date it appeared for:

### Sample Input:

These are valid examples:

```
taiwan hit hard by sars outbreak
united states to play davis cup in bratislava
russia ponders space tourism deal
zabaleta advances in austria
```

### Task:

- Find headlines with mentions of countries and cities.

- These should be capitalised

    1. First word of the headline
    2. Countries
    3. Cities

### Expected Output:

Examples of capitalised headlines

```
Taiwan hit hard by sars outbreak.
United States to play davis cup in Bratislava.
Russia ponders space tourism deal.
Zabaleta advances in Austria
```

- Return a file of the results in CSV format, and the program used for generating it.

### Approach:

- Generate a dictionary of Countries and Cities from comprehensive
  list of Countries and Cities data is downloaded from web as json

- Implementation of text transformation uses Chain of Responsibility pattern

- Stages:

  - Capitalize first word
  - Search for mentions of Countries and capitalize the mentions
  - Search for mentions of Cities and capitalize the mentions

### Maintainability:

- New stages can be added to chain or existing nodes can be reordered

### Open Close Principle:

- Algorithm to search for mentions and transformation can be enhanced in sub-classes

### Algorithm:

 - String-Search Algorithm

    - QGramShiftOr -- A fast algorithm for multi-pattern searching

### Design:

 - Cities and Country data are stored in  json files ->
    __Cities.json__ , __Countries.json__
 - Json data is loaded in to a Map on startup of Application -> __JsonDataDictionaryService.java__
 - TransformerChain can be built with pre-programmed unit of chain:
   - FirstWordTransformer
   - CityTransformer
   - CountryTransformer

   - __Configurability__ & __Maintainability__:

        - These transformer units can be joined in any order , which offers flexibility
        - New Transformer units can be plugged-in as well (Open-Close Principle)
        - Existing units in chain can be removed easily (Change in configuration)

### Workflow:

 1. Parse json Data (Static)
 2. Load and parse input news-headlines-csv file (Filename provided in command line)
 3. Initiate transformation workflow
    1. Capitalize first word
    2. Capitalize mentions of cities
    3. Capitalize mentions of Countries
 4. render the transformed string to a new output file (same as in input directory)
    1.  Workflow and render are repeated for all input new lines

 5. Output is rendered as records in csv file in:
    - Directory: /tmp/transformed/
    - fileName formatting: abcnews-date-text"+"_"+System.currentTimeMillis()+".csv"
        - sample: __abcnews-date-text_1552931515616.csv__

### Usage:

 ### Maven based package and start:

 1.  Essentials:  maven 3.3.9 + and jdk-8, jre-8

 2.  Run command:

    ```sh
    ./headline_textProcessor_console.sh
    ```

    OR

    ```sh
    headline_textProcessor_console.sh
    ```

 3. Command execution from step-4 should initiate maven clean, compile, test-compile, test, install phases

 4. Post Successful build, Spring-boot will startup (on random port) and initiate a command line runner.

 5. Console should display a command line argument entry for datafile

    Please enter the file name in command prompt / console

```
/Library/Java/JavaVirtualMachines/jdk1.8.0_25.jdk/Contents/Home/bin/java -Dmaven.multiModuleProjectDirectory=/Users/lakshmikanth/Desktop/Propine/headlines -Dmaven.home=/usr/local/Cellar/maven/3.6.0/libexec -Dclassworlds.conf=/usr/local/Cellar/maven/3.6.0/libexec/bin/m2.conf "-javaagent:/Applications/IntelliJ IDEA CE.app/Contents/lib/idea_rt.jar=64665:/Applications/IntelliJ IDEA CE.app/Contents/bin" -Dfile.encoding=UTF-8 -classpath /usr/local/Cellar/maven/3.6.0/libexec/boot/plexus-classworlds-2.5.2.jar org.codehaus.classworlds.Launcher -Didea.version=2018.2.7 spring-boot:run
objc[45658]: Class JavaLaunchHelper is implemented in both /Library/Java/JavaVirtualMachines/jdk1.8.0_25.jdk/Contents/Home/bin/java (0x102d514c0) and /Library/Java/JavaVirtualMachines/jdk1.8.0_25.jdk/Contents/Home/jre/lib/libinstrument.dylib (0x102dc64e0). One of the two will be used. Which one is undefined.
[INFO] Scanning for projects...
[WARNING]
[WARNING] Some problems were encountered while building the effective model for io.headlines:headlines:jar:1.0
[WARNING] 'build.plugins.plugin.(groupId:artifactId)' must be unique but found duplicate declaration of plugin org.jacoco:jacoco-maven-plugin @ line 150, column 21
[WARNING]
[WARNING] It is highly recommended to fix these problems because they threaten the stability of your build.
[WARNING]
[WARNING] For this reason, future Maven versions might no longer support building such malformed projects.
[WARNING]
[INFO]
[INFO] -----------------------< io.headlines:headlines >-----------------------
[INFO] Building headlines 1.0
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] >>> spring-boot-maven-plugin:2.0.4.RELEASE:run (default-cli) > test-compile @ headlines >>>
[INFO]
[INFO] --- jacoco-maven-plugin:0.7.5.201505241946:prepare-agent (jacoco-initialize) @ headlines ---
[INFO] argLine set to -javaagent:/Users/lakshmikanth/.m2/repository/org/jacoco/org.jacoco.agent/0.7.5.201505241946/org.jacoco.agent-0.7.5.201505241946-runtime.jar=destfile=/Users/lakshmikanth/Desktop/Propine/headlines/target/coverage-reports/jacoco-unit.exec
[INFO]
[INFO] --- maven-resources-plugin:3.0.2:resources (default-resources) @ headlines ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 1 resource
[INFO] Copying 0 resource
[INFO]
[INFO] --- maven-compiler-plugin:3.6.1:compile (default-compile) @ headlines ---
[INFO] Nothing to compile - all classes are up to date
[INFO]
[INFO] --- maven-resources-plugin:3.0.2:testResources (default-testResources) @ headlines ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 2 resources
[INFO]
[INFO] --- maven-compiler-plugin:3.6.1:testCompile (default-testCompile) @ headlines ---
[INFO] Nothing to compile - all classes are up to date
[INFO]
[INFO] <<< spring-boot-maven-plugin:2.0.4.RELEASE:run (default-cli) < test-compile @ headlines <<<
[INFO]
[INFO]
[INFO] --- spring-boot-maven-plugin:2.0.4.RELEASE:run (default-cli) @ headlines ---
[INFO] Attaching agents: []
16:07:52.431 [main] DEBUG org.springframework.boot.devtools.settings.DevToolsSettings - Included patterns for restart : []
16:07:52.435 [main] DEBUG org.springframework.boot.devtools.settings.DevToolsSettings - Excluded patterns for restart : [/spring-boot-actuator/target/classes/, /spring-boot-devtools/target/classes/, /spring-boot/target/classes/, /spring-boot-starter-[\w-]+/, /spring-boot-autoconfigure/target/classes/, /spring-boot-starter/target/classes/]
16:07:52.435 [main] DEBUG org.springframework.boot.devtools.restart.ChangeableUrls - Matching URLs for reloading : [file:/Users/lakshmikanth/Desktop/Propine/headlines/target/classes/]

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.0.4.RELEASE)

2019-03-21 16:07:52.782  INFO 45659 --- [  restartedMain] i.h.HeadlineTextProcessingApplication    : Starting HeadlineTextProcessingApplication on lakshmikanthMBP with PID 45659 (/Users/lakshmikanth/Desktop/Propine/headlines/target/classes started by lakshmikanth in /Users/lakshmikanth/Desktop/Propine/headlines)
2019-03-21 16:07:52.784  INFO 45659 --- [  restartedMain] i.h.HeadlineTextProcessingApplication    : No active profile set, falling back to default profiles: default
2019-03-21 16:07:52.846  INFO 45659 --- [  restartedMain] s.c.a.AnnotationConfigApplicationContext : Refreshing org.springframework.context.annotation.AnnotationConfigApplicationContext@4f7de7: startup date [Thu Mar 21 16:07:52 IST 2019]; root of context hierarchy
2019-03-21 16:07:53.266  INFO 45659 --- [  restartedMain] o.s.b.f.s.DefaultListableBeanFactory     : Overriding bean definition for bean 'jsonDataDictionaryService' with a different definition: replacing [Generic bean: class [io.headlines.service.JsonDataDictionaryService]; scope=singleton; abstract=false; lazyInit=false; autowireMode=0; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=null; factoryMethodName=null; initMethodName=null; destroyMethodName=null; defined in file [/Users/lakshmikanth/Desktop/Propine/headlines/target/classes/io/headlines/service/JsonDataDictionaryService.class]] with [Root bean: class [null]; scope=; abstract=false; lazyInit=false; autowireMode=3; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=headlineTextProcessorConfiguration; factoryMethodName=jsonDataDictionaryService; initMethodName=null; destroyMethodName=(inferred); defined in class path resource [io/headlines/configuration/HeadlineTextProcessorConfiguration.class]]
2019-03-21 16:07:54.477  INFO 45659 --- [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
2019-03-21 16:07:54.513  INFO 45659 --- [  restartedMain] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
2019-03-21 16:07:54.540  INFO 45659 --- [  restartedMain] i.h.HeadlineTextProcessingApplication    : Started HeadlineTextProcessingApplication in 2.091 seconds (JVM running for 2.604)
2019-03-21 16:07:54.543  INFO 45659 --- [  restartedMain] i.headlines.CommandLineAppStartupRunner  : Application started with command-line arguments: [] .
 To kill this application, press Ctrl + C.
abcnews-date-text.csv
2019-03-21 16:07:56.620  INFO 45659 --- [  restartedMain] i.headlines.CommandLineAppStartupRunner  : parsing fileName - abcnews-date-text.csv
2019-03-21 16:11:40.967  INFO 45659 --- [  restartedMain] i.headlines.CommandLineAppStartupRunner  : Transformed-Headlines with count: 1103665
2019-03-21 16:11:43.911  INFO 45659 --- [  restartedMain] io.headlines.common.FileReaderUtil       : about to create new file: /tmp/transformed/abcnews-date-text_1553164903910.csv at directory: true
2019-03-21 16:11:43.913  INFO 45659 --- [  restartedMain] io.headlines.common.FileReaderUtil       : created new File Object: /tmp/transformed/abcnews-date-text_1553164903910.csv
2019-03-21 16:11:44.240  INFO 45659 --- [  restartedMain] i.h.service.HeadlineRendererServiceImpl  : HeadlineTextProcessing Completed Successfully
2019-03-21 16:11:44.242  INFO 45659 --- [       Thread-8] s.c.a.AnnotationConfigApplicationContext : Closing org.springframework.context.annotation.AnnotationConfigApplicationContext@4f7de7: startup date [Thu Mar 21 16:07:52 IST 2019]; root of context hierarchy
2019-03-21 16:11:44.246  INFO 45659 --- [       Thread-8] o.s.j.e.a.AnnotationMBeanExporter        : Unregistering JMX-exposed beans on shutdown
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  03:55 min
[INFO] Finished at: 2019-03-21T16:11:44+05:30
[INFO] ------------------------------------------------------------------------

Process finished with exit code 0

```

### Performance:

```
- Input Dataset: 1103665 records (1.1 million)
- Start-Time: 16:07:56.620
- End-Time:   16:11:44.240
- Duration: 03:48.420

- Average: 03:48 Minutes for 1 Million records
```

### References:

#### StringSearchAlgorithm
- http://stringsearchalgorithms.amygdalum.net/

#### High-performance search Library:
- https://johannburkard.de/software/stringsearch/
