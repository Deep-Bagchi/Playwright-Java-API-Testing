1. Playwright, Java, Maven, TestNg has been used to create this Framework.
2. This framework can be run through GitHub,Jenkins pipeline setup as well. Jenkinsfile script has been added.
3. Response JSON structure has been validated against static JSON Schema file, with the help of Schema validation.
4. Default Test Reports are vailable at <.//test-output> folder.
5. To Run the framework through Maven:
	->mvn clean test 
6. To Run the framework through TestNg: 
	->Right click on testng.xml->Run as->TestNg Suite
7. All required dependencies have been added to the POM.xml file
8. Since the framework is built on Playwright, it does allow parellel run automatically.

Due to time constraints, as i am a travelling, owing to a long weekend, i was not able to add some of the "Good to have" features, as listed below:
1. Conversion of JSON to JSON Schema has not been done through code. I have used an online converter to convert the JSON to JSON Schema, and used the same as a reference to carry out the Schema Validation process.
2. BDD/Cucumber has not been integrated to the framework.
3. External Reports such as Extent Reports and Allure Reports, has not been integrated to the framework.
 