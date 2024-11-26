# OrangeHRM_TestNG_Automation

### This is a complete project where a OrangeHRM site is automated by writing test suites using selenium-webdriver and TestNg as testing framework.
The following key modules are automated:

    * Login
    * Apply Leave
                

For failed test cases it will take a screenshot aswell at the point of failure.

### Technology:

* Tool: Selenium Webdriver
* IDE: Intellij
* Build tool: Gradle
* Language: Java
* Test_Runner: TestNG

### Prerequisite:
* Need to install jdk 11, gradle and allure
* Configure Environment variable for jdk 11, gradle and allure
* Clone this project and unzip it
* Open the project folder
* Double click on "build.gradle" and open it through IntellIJ IDEA
* Let the project build successfully
* Click on "Terminal" and run the automation scripts

### Run the Automation Script by the following command:

```bash
  gradle clean test 
```
* Selenium will open the browser and start automating.
* After automation to view allure report , give the following commands:

```bash
allure generate allure-results --clean -o allure-report
allure serve allure-results
```
### Testing Report for RegressionSuites
#### Here is the Allure report Behaviors:
![Screenshot 2024-11-27 030052](https://github.com/user-attachments/assets/13c3b26d-aa47-4ad3-b8c5-3bd2630745c5)

#### Here is the Allure report overview:
![Screenshot 2024-11-27 030127](https://github.com/user-attachments/assets/1323e4f8-106b-4acc-a713-d19093dffc9c)

#### Here are the allure suites of this project:
![Screenshot 2024-11-27 030113](https://github.com/user-attachments/assets/e9fbde3b-af8b-431f-b23a-3194018d24a7)

