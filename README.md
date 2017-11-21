# osgi-demo
OSGi Basic Implementations and Concepts


Demo Application by Sonali Sahu for Liferay Interview
November 21, 2017


Table of Contents
Requirements	1
Introduction to OSGi Basics	2
OSGi Layered Architecture	2
OSGi Building Blocks	3
Introduction to the Applications Demo	3
Setting up Workspace and getting started.	4
Building the API Bundle	5
Build Provider Bundle	7
Build Web Client Module	9
Build Command Module	12
Reference:	15



Requirements

•	Java SDK 7 or 8 
Latest Java Version can be downloaded from here
Then set the JAVA_HOME for the system. (Method for setting the environment variable JAVA_HOME may vary according for the Operating System)
•	Latest Eclipse IDE for Java EE Developers from here
•	Bndtool plugins 
Can be installed from Eclipse IDE by clicking on Help > Eclipse Marketplace > Search and Install Bndtools. Click here for detailed instructions.
•	Apache Felix Integration (Already integrated with Bndtools and Eclipse installation)
Introduction to OSGi Basics

OSGI is Open Service Gateway Initiative provides a modular architecture distributed systems and mostly widely used. It is simply a modularity layer for Java Platforms. Modularity refers to the logical decomposition of a large system into smaller collaborating pieces.



The OSGi specification defines a bundle as the smallest unit of modularization, i.e., in OSGi a software component is a bundle.

OSGi Layered Architecture

OSGi framework consists of three conceptual layers as defined in the OSGi specification.


•	Module layer —Concerned with packaging and sharing code
•	Lifecycle layer —Concerned with providing execution-time module management and access to the underlying OSGi framework
•	Service layer —Concerned with interaction and communication among modules, specifically the components contained in them

OSGi Building Blocks

1.	Bundles: is one or more components/service are made as a jar file. Each bundle should also require MANIFEST.MF file that defines meta data of bundle such – bundle activator, policy, list of exported packages, services etc.
2.	Components: 
3.	Services
Introduction to the Applications Demo












	(com.test.api.StringConvertor)


	




		(org.apache.felix.shell.Command)

•	The API bundle exports a service interface, `StringConvertor`.
•	The Provider bundle imports the interface and publishes an instance of the service.
•	The Command bundle imports the interface and binds to the service instance, and publishes a `Command` service that is used by the Felix Shell bundle.
•	The Web bundle is the client bundle that consumes services.
Setting up Workspace and getting started.

All BND based projects requires bnd workspace to get started.
Bnd workspace is different from normal eclipse workspace and BND workspace contains all project dependencies and build configurations for OSGI projects.

To create Bnd workspace
•	click File -> New -> Bnd Workspace
•	Choose folder for workspace.
•	Select create in and give bnd workspace folder location 


•	Select bndtools/workspace
•	It will create the cnf directory


Now Bnd workspace creation in completed. This entire bnd-workspace can be used as your Source Code Repository in SVN / GIT.

Then we will build project with the following layered architecture with view, services, persistence layers.  In OSGI context, we will convey them as bundles:

1.	com.test.api –  this bundle will contain all interface exposed and can be imported in other bundles
2.	com.test.provider –   This bundle provides implementation of “com.test.api”
3.	com.test.web-  This is sample web project where it uses services layer
4.	com.test.command – This is a sample bundle that consumes services through commands in Gogo shell

Building the API Bundle

1.	Create BND Workspace if not created
2.	Create empty API project (File -> New -> Bnd OSGI Project) “com.test.api”  as shown in below and update version as “1.0.0” in bnd.bnd file. This is plain interface OSGI bundle and not required to add additional OSGI dependencies. Then in the final screen click on Finish.
        



3.	Create an new Java class under the scr folder StringConvertor.java 

4.	Now export package in bnd.bnd file  in “com.test.api” module


Build Provider Bundle

1.	Create “com.test.provider”  Project (File -> New -> Bnd OSGI Project)  as same way above by selecting Empty and  update version as “1.0.0” in content section in bnd.bnd settings file. 
2.	Add the dependencies as shown in below. By clicking on the ‘+’ sign in the corner of the Build tab of bnd.bnd file

3.	Create implementation class “StringConvertorImpl.java” in the src folder to implement com.test.api as:

@Component will register StringConvertorImpl to OSGI Service Registry framework and no additional configuration is required.
4.	Export package as shown in the Content tab of bnd.bnd file

Build Web Client Module

1.	Create Servlet OSGI module (File -> New -> Bnd OSGI Project) com.test.web as a client to consume the demo module services. In the Project Template section select “Servlet Component” as shown in below and leave the rest As-Is. 
 
2.	Open the bnd.bnd settings file and add the below dependencies under build tab like show in below:

3.	In Client Web Project, Rename the ExampleServlet to StringConvertorServlet  and change the path “example” to “/string” as shown in below and inject the StringConvertor.

4.	The Servlet template has created launch.bndrun file also that will used to run the OSGI bundles. Open that file and and add the provider and api module in Run Requirements and Run Bundles Sections.

5.	Select “Auto-Resolve on Save” to avoid errors and click on Run OSGI button
6.	 browser, enter URL localhost:8080/string with value and method parameters and you can see the below page: http://localhost:8080/string?value=sonali&method=upper



Build Command Module

1.	Create “com.test.command”  Project (File -> New -> Bnd OSGI Project)  as same way above by selecting Empty and  update version as “1.0.0” in content section in bnd.bnd settings file.  To consume the services from the Gogo command shell with custom command
2.	Add the following dependencies. We need to make the Felix shell API available to compile against. Open bnd.bnd and change to the Build tab. Add org.apache.felix.gogo.runtime with other api to the list of build dependencies, and save the file:

3.	Create a java class StringConvertorCommand.java to consume the services as follows:

4.	The use of the @Component annotation. This enables our bundle to use OSGi Declarative Services to declare the API implementation class. This means that instances of the class will be automatically created and registered with the OSGi service registry. The annotation is build-time only, and does not pollute our class with runtime dependencies -- in other words, this is a "Plain Old Java Object" or POJO.
5.	Add the package com.test.command to the Private Packages panel of bnd.bnd file.

6.	We'd now like to run OSGi. To achieve this we need to create a "Run Descriptor" that defines the collection of bundles to run, along with some other run-time settings.
Right-click on the project com.test.command and select New > Run Descriptor. In the resulting dialog, enter launch as the file name and click Next. The next page of the dialog asks us to select a template; choose Apache Felix 4 with Gogo Shell and click Finish.
 
7.	Add the following Run Requirements and Run Bundles on the Run tab of launch.bndrun file. Check on Auto-resolve.

8.	Click on Run OSGi.  Shortly, the Felix Shell prompt "g!" will appear in the Console view.
9.	All the commands mentioned in of StringConverorCommand class will be available for use. Type the command with the parameters to view results in the commands shell.



Reference:

•	http://mnlipp.github.io/osgi-getting-started/ServiceComponent.html
•	http://www.vogella.com/tutorials/OSGi/article.html
•	https://livebook.manning.com/#!/book/osgi-in-action/chapter-1/79
•	http://www.javasavvy.com/osgi-services-tutorial/
•	https://wiki.eclipse.org/OSGI_component_support_in_Service_Creation
•	http://bndtools.org/tutorial.html
•	https://www.javaworld.com/article/2077837/application-development/java-se-hello-osgi-part-1-bundles-for-beginners.html


