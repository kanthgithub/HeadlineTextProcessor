# Drawing Tool Console:

## Tech-Stack:
1. Algorithm is implemented in jdk-8 and runs on jre-8
3. Maven project
4. Spring-Boot for Dependency Injection and Application Run
5. Cucumber for Behavioral Driven Testing
6. Junit for unit testing

## Features:

1. Create a new canvas

2. Start drawing shapes on the canvas by issuing various commands

   1. Rectangle, Line and Fill with color/characters

3. Quit

## How to Run:

1. Extract the zip contents to drawingtoolconsole
2. Verify If Extracted folder contain directories:
    - src
    - pom.xml
    - README.md
    - Feature_Report.png
    - Feature_Report_2.png
    - Step_Report.png
    - Step_Report_2.png
    - Unit_Tests.png
    - drawing_tool_console.sh


### Maven based package and start:

1.  Essentials:  maven 3.3.9 + and jdk-8, jre-8

2.  Run command:

   ```sh
   ./drawing_tool_console.sh
   ```

   OR

   ```sh
   drawing_tool_console.sh
   ```

3. Command execution from step-4 should initiate maven clean, compile, test-compile, test, install phases

4. Post Successful build, Spring-boot will startup (on random port) and initiate a command line runner.

```
springframework.data.rest.webmvc.RootResourceInformation)
2018-12-23 11:53:56.602  INFO 64012 --- [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
2018-12-23 11:53:56.659  INFO 64012 --- [  restartedMain] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
2018-12-23 11:53:56.754  INFO 64012 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 61696 (http) with context path ''
2018-12-23 11:53:56.760  INFO 64012 --- [  restartedMain] i.d.DrawingToolConsoleApplication        : Started DrawingToolConsoleApplication in 5.138 seconds (JVM running for 5.722)
2018-12-23 11:53:56.765  INFO 64012 --- [  restartedMain] i.d.CommandLineAppStartupRunner          : Application started with command-line arguments: [] .
 To kill this application, press Ctrl + C.
enter command:
```

5. Now app is available for canvas creation and rendering shapes on canvas (using commands listed above)


```
Application will support the following commands:
```

<table>
<th><td>Command</td><td>Command Execution and Output<td></th>
<tr><td>C w h</td></td>Should create a new canvas of width w and height h.</td></tr>
<tr><td>L xCoordinate1 yCoordinate1 xCoordinate2 yCoordinate2</td><td><p>Should create a new line from (xCoordinate1,yCoordinate1) to (xCoordinate2,yCoordinate2).
<p>Currently only horizontal or vertical lines are supported.<p>Horizontal and vertical lines will be drawn using the 'xCoordinate' character.</td></tr>
<tr><td>R xCoordinate1 yCoordinate1 xCoordinate2 yCoordinate2 </td>
<td><p>Should create a new rectangle, whose upper left corner is (xCoordinate1,yCoordinate1) and lower right corner is (xCoordinate2,yCoordinate2).
<p>Horizontal and vertical lines will be drawn using the 'xCoordinate' character.</td></tr>
<tr><td>B xCoordinate yCoordinate c</td><td><p>Should fillCoordinatesWithCharacters the entire area connected to (xCoordinate,yCoordinate) with "colour" c.
<p>The behaviour of this is the same as that of the "bucket fill" tool in paint programs.</td></tr>
<tr><td>Q </td><td>Should quit the program.</td></tr>
</table>

### Sample I/O

 - Below is a sample run of the program. User input is prefixed with ’enter command:’.

   - enter command: C 20 4

        ```

        ----------------------
        |                    |
        |                    |
        |                    |
        |                    |
        ______________________

        ```

   - enter command: L 1 2 6 2

        ```
        ----------------------
        |                    |
        |xxxxxx              |
        |                    |
        |                    |
        ______________________

        ```

   - enter command: L 6 3 6 4

        ```
        ----------------------
        |                    |
        |xxxxxx              |
        |     x              |
        |     x              |
        ______________________

        ```

   - enter command: R 16 1 20 3

        ```
        ----------------------
        |               xxxxx|
        |xxxxxx         x   x|
        |     x         xxxxx|
        |     x              |
        ______________________

        ```

   - enter command: B 10 3 o

        ```
        ----------------------
        |oooooooooooooooxxxxx|
        |xxxxxxooooooooox   x|
        |     xoooooooooxxxxx|
        |     xoooooooooooooo|
        ______________________

        ```

   - enter command: Q


## Design:

- Entry Point:

  - SpringBootApplication Main file: DrawingToolConsoleApplication.java

  - Command Line Runner associated with Springboot Application: CommandLineAppStartupRunner.java

  - Key file which controls the execution of commands and command parse: DrawingToolCommandController.java

  - Service which executes a command scanned from test inputs: DrawingToolCommandExecutionService.java

  - Sequence of Flow:

    ```
    DrawingToolConsoleApplication -> CommandLineAppStartupRunner -> DrawingToolCommandController -> DrawingToolCommandExecutionService
    ```


### Data-Model:

#### Commands:

 - Command Interface is the base for all Commands supported

   ```java
   public interface Command
   {
     void execute(CanvasRenderer canvasRenderer);
   }
   ```

 - Commands intercepted by scanner should be of valid command Store

  ```java
  public enum DrawingToolConsoleCommand
  {
    CREATE("C"),
    LINE("L"),
    RECTANGLE("R"),
    FILL("B"),
    QUIT("Q"),
    ALIEN("AL");
  ```

 - Command Categories:

   - CreateCommand stores the essential canvas dimensions and creation of new canvas

     ```java
     public class CreateCommand implements Command
     {
       private int width;
       private int height;

       @Override
       public void execute(CanvasRenderer canvasRenderer)
       {
         canvasRenderer.createCanvas(width, height);
       }

     }
     ```

   - LineCommand stores the essential coordinates for Line and method to initiate drawing line on canvas

          ```java
          public class LineCommand implements Command
          {
            private int xCoordinate1;
            private int yCoordinate1;
            private int xCoordinate2;
            private int yCoordinate2;

            @Override
            public void execute(CanvasRenderer canvasRenderer)
            {
              canvasRenderer.addLine(xCoordinate1, yCoordinate1, xCoordinate2, yCoordinate2);
            }
          }
         ```

   - RectangleCommand stores the essential coordinates for Line and method to initiate drawing rectangle on canvas

          ```java
          public class RectangleCommand implements Command
          {
            private int xCoordinate1;
            private int yCoordinate1;
            private int xCoordinate2;
            private int yCoordinate2;

            @Override
            public void execute(CanvasRenderer canvasRenderer)
            {
              canvasRenderer.addRectangle(xCoordinate1, yCoordinate1, xCoordinate2, yCoordinate2);
            }

          }
          ```

   - FillCommand stores the essential coordinates and method to initiate filling canvas with characters/color

       ```java
       public class FillCommand implements Command
       {
         private  int xCoordinate;
         private int yCoordinate;
         private char color;

         @Override
         public void execute(CanvasRenderer canvasRenderer)
         {
           canvasRenderer.fillCoordinatesWithCharacters(xCoordinate, yCoordinate, color);
         }

       }
       ```

  - QuitCommand is meant to mark an end to command execution/scanner loop





#### Shapes & Canvas:

- Shape => Interface which is a abstract representation or base for all Shapes

  - Shape is a first class citizen where content rendered on canvas will be driven by Shape

  - io.drawingtoolcanvas.model.Shape.java

  ```java
  public interface Shape
  {
    void addShapeTo(DrawingCanvas drawingCanvas);
  }
  ```

- Line => Draws a line in the specified coordinates which should fit on the canvas

  - io.drawingtoolconsole.model.Line.java

  - Snippet of the Class:

    ```java
    public class Line implements Shape
    {
      private final static char FILL_CHARACTER = 'x';

      private Integer xCoordinate1;
      private Integer yCoordinate1;
      private Integer xCoordinate2;
      private Integer yCoordinate2;
    ```

- Rectangle => Draws a rectangle in the specified coordinates which should fit on the canvas

  - io.drawingtoolconsole.model.Rectangle.java

  - Snippet of the Class:


    ```java
    public class Rectangle implements Shape
    {
      private final static char FILL_CHARACTER = 'x';

      private Integer xCoordinate1;
      private Integer yCoordinate1;
      private Integer xCoordinate2;
      private Integer yCoordinate2;

    ```

- DrawingCanvas => Canvas on which Shapes will be rendererd. with predefined width and height limits

  - io.drawingtoolconsole.model.DrawingCanvas.java

  - Snippet of the Class:

    ```java
    public class DrawingCanvas
    {
       final static char BLANK = ' ';

      private int width;
      private int height;
      private char[][] blocks;

      public DrawingCanvas(int _width, int _height)
      {
        width = _width;
        height = _height;

        blocks = new char[_width][_height];

        for (int widthIndex = 0; widthIndex < width; widthIndex++) {
          for (int heightIndex = 0; heightIndex < height; heightIndex++)
            blocks[widthIndex][heightIndex] = BLANK;
        }
      }
    ```

### Core Executor:

#### Shape Rendering Trigger:

 - command execution is performed by: io.drawingtoolconsole.console.service.DrawingToolCommandExecutionService.java

 ```java
    public StringBuilder executeDrawingCommand(Scanner scanner,
                                                DrawingToolConsoleCommand drawingToolConsoleCommand){

         Command command = consoleCommandArgumentEnricherFactory
                                                 .lookupCommandEnricher(drawingToolConsoleCommand)
                                                 .enrichCommandWithScannerArguments(scanner);
         command.execute(canvasRenderer);

         return canvasRenderer.renderAsAString();
     }
   ```

#### Scanner Looper and Trigger for core execution:

  - Commands entered are intercepted from IO by java.util.scanner

  - Class: io.drawingtoolconsole.console.controller.DrawingToolCommandController.java

  ```java
      /**
       * 1. parse command from Scanner
       * 2. collect the commands executed to commandsExecutedStore
       * 3. execute command with associated command-data using drawingToolCommandExecutionService
       * 4. collect the command execution output to commandAndRenderedStringStore / error to commandAndErrorStringStore
       * 5. render the output on console
       *
       * Repeat 1-5 till Command Q (Quit) is parsed. Q is considered as a command to end of loop.
       *
       * @param scanner
       */
      public void executeCommandsFromScanner(Scanner scanner) {

          DrawingToolConsoleCommand drawingToolConsoleCommand = DrawingToolConsoleCommand.ALIEN;

          renderedShapesOnCanvas = new ArrayList<>();

          commandsExecutedStore = new ArrayList<>();

          errorStore = null;

          do {
              System.out.print("enter command:");
              try {
                  drawingToolConsoleCommand = DrawingToolConsoleCommand.parse(scanner.next());

                  commandsExecutedStore.add(drawingToolConsoleCommand);

                  StringBuilder sb = drawingToolCommandExecutionService.executeDrawingCommand(scanner, drawingToolConsoleCommand);

                  String renderedString = sb.toString();

                  System.out.println(renderedString);

                  renderedShapesOnCanvas.add(renderedString);
               }
              catch (Exception ex) {

                  if(errorStore== null)
                  {
                      errorStore = new ArrayList<>();
                  }

                  errorStore.add(ex.getMessage());
                  System.out.println(ex.getMessage());

              }
          } while (drawingToolConsoleCommand != DrawingToolConsoleCommand.QUIT);
      }

  ```


## Testing:

- Total Test Count: 166

- Unit Test Count: 134

- Integeration Test Count: 67 (Counts steps)


### Integration-Testing:

#### Cucumber Feature files:

1. drawingConsoleCommandErrorAssertion.feature
2. drawingConsoleCommandExecution.feature

#### Expected Data for Cucumber:

1. DrawingConsoleToolStepDef_Scenario_1_RenderedOutput.txt
2. DrawingConsoleToolStepDef_Scenario_2_Shape_1.txt
3. DrawingConsoleToolStepDef_Scenario_2_Shape_2.txt
4. DrawingConsoleToolStepDef_Scenario_3_Shape_1.txt
5. DrawingConsoleToolStepDef_Scenario_3_Shape_2.txt
6. DrawingConsoleToolStepDef_Scenario_4_Shape_1.txt
7. DrawingConsoleToolStepDef_Scenario_4_Shape_2.txt
8. DrawingConsoleToolStepDef_Scenario_4_Shape_3.txt
9. DrawingConsoleToolStepDef_Scenario_5_Shape_1.txt
10. DrawingConsoleToolStepDef_Scenario_5_Shape_2.txt
11. DrawingConsoleToolStepDef_Scenario_5_Shape_3.txt
12. DrawingConsoleToolStepDef_Scenario_5_Shape_4.txt
13. DrawingConsoleToolStepDef_Scenario_5_Shape_5.txt
14. DrawingConsoleToolStepDef_Scenario_6_Shape_1.txt
15. DrawingConsoleToolStepDef_Scenario_6_Shape_2.txt

Covers Successful and failed execution scenarios

#### Feature Reports:

![Feature_Report](Feature_Report.png)

![Feature_Report_2](Feature_Report_2.png)

#### Step Reports:

![Step_Report](Step_Report.png)

![Step_Report_2](Step_Report_2.png)


#### Unit-Testing:

Command-line Runner has to be excluded to unit-test components:

![Unit_Tests](Unit_Tests.png)


#### Procedure followed to exclude loading commandLineRunner for above mentioned tests:


- You can define a test configuration in the same package as your application that looks exactly the same
- except that it excludes beans implementing CommandLineRunner.
- The key here is @ComponentScan.excludeFilters:

```java
@Configuration
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = CommandLineRunner.class))
@EnableAutoConfiguration
public class TestApplicationConfiguration {
}
Then, just replace the configuration on your test:

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class ConsoleCommandArgumentEnricherFactoryTest {
    ...
}
```

No CommandLineRunner will be executed now, because they are not part of the configuration.

#### End-To-End Multi-Command Tests Using Scanner:

- End to End tests are simulated via topping-up scanner with commands to be executed (simulated environment)
- Loop defined in DrawingToolController will exit on intercepting Quit Command (Command String: Q)
- test Data is :  "Start \n C 20 4 \n L 1 2 6 2 \n L 6 3 6 4 \n R 16 1 20 3 \n B 10 3 o \n Q"
- Test Begins with Start which will eventually be skipped on very first scanner.next()
    1. First Command C 20 4 -> creates Canvas
    2. Second Command L 12 6 2 -> Draws a line on Canvas
    3. Third Command L 6 3 6 4 -> Draws a line on Canvas
    4. Fourth Command R 16 1 20 3 -> Draws a Rectangle on Canvas
    5. Fifth Command B 10 3 o -> Fills Canvas with color (Fill Chaaracters) on coordintaes on Canvas
    6. Sixth and Last Command Q -> Quits the Canvas and ends the execution loop

```java
    @Test
    public void assert_Multiple_Command_Execution_Via_CanvasCreation_Line_And_RectangleRender(){

        //given
        String canvasData = "Start \n C 20 4 \n L 1 2 6 2 \n L 6 3 6 4 \n R 16 1 20 3 \n B 10 3 o \n Q";

        scanner = generateScannerWithTestData(canvasData);

        //when
        drawingToolCommandController.executeCommandsFromScanner(scanner,true);

        //then
        List<String> renderedShapesOnCanvas = drawingToolCommandController.getRenderedShapesOnCanvas();

        assertNotNull(renderedShapesOnCanvas);
        assertEquals(6,renderedShapesOnCanvas.size());

        //expected Shape Data Assertion

        String shape_1 = "----------------------\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "______________________";

        assertNotNull(renderedShapesOnCanvas.get(0));
        assertEquals(shape_1,renderedShapesOnCanvas.get(0));


        String shape_2 = "----------------------\n" +
                        "|                    |\n" +
                        "|xxxxxx              |\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "______________________";

        assertNotNull(renderedShapesOnCanvas.get(1));
        assertEquals(shape_2,renderedShapesOnCanvas.get(1));


        String shape_3 = "----------------------\n" +
                        "|                    |\n" +
                        "|xxxxxx              |\n" +
                        "|     x              |\n" +
                        "|     x              |\n" +
                        "______________________";

        assertNotNull(renderedShapesOnCanvas.get(2));
        assertEquals(shape_3,renderedShapesOnCanvas.get(2));


        String shape_4 =    "----------------------\n" +
                            "|               xxxxx|\n" +
                            "|xxxxxx         x   x|\n" +
                            "|     x         xxxxx|\n" +
                            "|     x              |\n" +
                            "______________________";

        assertNotNull(renderedShapesOnCanvas.get(3));
        assertEquals(shape_4,renderedShapesOnCanvas.get(3));


        String shape_5 = "----------------------\n" +
                        "|oooooooooooooooxxxxx|\n" +
                        "|xxxxxxooooooooox   x|\n" +
                        "|     xoooooooooxxxxx|\n" +
                        "|     xoooooooooooooo|\n" +
                        "______________________";

        assertNotNull(renderedShapesOnCanvas.get(4));
        assertEquals(shape_5,renderedShapesOnCanvas.get(4));

        String shape_6 =
                        "----------------------\n" +
                        "|oooooooooooooooxxxxx|\n" +
                        "|xxxxxxooooooooox   x|\n" +
                        "|     xoooooooooxxxxx|\n" +
                        "|     xoooooooooooooo|\n" +
                        "______________________";

        assertNotNull(renderedShapesOnCanvas.get(5));
        assertEquals(shape_6,renderedShapesOnCanvas.get(5));
    }
    ```