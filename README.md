# cadviewer-tomcat-01
implementation  structure of cadviewer-script-library using Servlets

The repository contains a full setup of CADViewer with CAD Converters and script controllers for Servlets.  It must be installed under tomcat /webapps/cadviewer.   For war versions, please visit our [download site](https://cadviewer.com/download/).

## This package contains

1: CADViewer Servlet script library  - in its preferred folder structure

2: AutoXchange AX2022 Converter and DWG Merge 2022 Converter - in their preferred folder structure

3: All structures for file-conversion, sample drawings, redlines, etc. 

4: A number of HTML files with CADViewer samples.

5: The folder structure for Servlet script handlers for communication between CADViewer and the back-end AutoXchange 2022.


## This package does not contain

6: The converter folder structure contains a larger set of fonts, installed in /cadviewer/converters/ax2022/windows/fonts/, but a fuller set of fonts can be installed. 

Read the sections on installing and handling [Fonts](https://tailormade.com/ax2020techdocs/installation/fonts/) in [AutoXchange 2020 TechDocs](https://tailormade.com/ax2020techdocs/) and [TroubleShooting](https://tailormade.com/ax2020techdocs/troubleshooting/).


## Install

### Windows:  

Use as is.

### Linux:  

In cadviewer/WEB-INF/web.xml, change all \<!-- --> settings strings for AX2022 executable, DwgMerge executable, and all cadviewer paths so they match the tomcat/webapps/ setting in Linux install environment.  

Ensure ax2022_L64_xx_yy_zz has chmod 777 permissions.  

Ensure /cadviewer/converters/files/ folder and all subfolders to this folder have full read and write permissions. 




## How to Use

Once installed, open the HTML samples under /cadviewer/html/ can be run from a web-browser. Use http://localhost:xxxx/cadviewer/html/CADViewer_fileloader_670.html as a starting point (assuming that your have installed under http://localhost:xxxx).






## General Documentation 

-   [CADViewer Techdocs and Installation Guide](https://cadviewer.com/cadviewertechdocs/download)



## Updating CAD Converters

This repository should contain the latest converters, but in case you need to update any of the back-end converters please follow: 

* [Download **AutoXchange**](/download/) (and other converters), install (unzip) AX2020 in **cadviewer/converters/ax2022/windows** or **cadviewer/converters/ax2022/linux** or in the designated folder structure.

* Read the sections on installing and handling [Fonts](https://tailormade.com/ax2020techdocs/installation/fonts/) in [AutoXchange 2020 TechDocs](https://tailormade.com/ax2020techdocs/) and [TroubleShooting](https://tailormade.com/ax2020techdocs/troubleshooting/).

* Try out the samples and build your own application!
 
 

## Specific Documentation - CADViewer on Tomcat

### Folder Structure
For a CADViewer Servlets installation under Tomcat on Windows, the base file-structure should be as below: 

**NOTE:** Under Linux replace **c:\xampp\tomcat\webapps** with **/var/lib/tomcat{X}**.

<pre style="line-height: 110%">
c:\xampp\tomcat\webapps
       └─── cadviewer
               ├── app
               │    ├── cv
               │    │    ├── cv-pro 
               │    │    │   ├── menu_config
               │    │    │   ├── language_table
               │    │    │   └── space
               │    │    │         ├── css 
               │    │    │         └── html
               │    │    ├── cv-core
               │    │    │   ├── menu_config
               │    │    │   └── language_table
               │    │    └── cv-custom_commands
               │    ├── fonts
               │    ├── images
               │    ├── js
               │    ├── css
               │    └── user_resources	
               ├── converters
               │    ├── ax2022
               │    │     ├── windows 
               │    │     │      └── fonts
               │    │     └── linux
               │    │            └── fonts
               │    ├── dwgmerge2022
               │    │         ├── windows 
               │    │         │      └── fonts
               │    │         └── linux
               │    │            └── fonts
               │    ├── linklist2022
               │    │         ├── windows 
               │    │         │     └── fonts
               │    │         └── linux
               │    │               └── fonts
               │    └── files
               ├── WEB-INF
               │      ├── classes
               │      └── lib
               ├── content
               ├── html
               └── temp_print
</pre>

**1:** [Download](https://cadviewer.com/download/) and install **CADViewer** under this structure.  <br>
**2:** [Download](https://cadviewer.com/download/) and install **AutoXchange 2022** under this structure. <br>
**3:** Optionally, [Download](https://cadviewer.com/download/) and install **DwgMerge 2022** under this structure.


### WEB-INF Configuration

**NOTE:** Under Linux replace **c:\xampp\tomcat\webapps** with **/var/lib/tomcat{X}**.


In folder:

<pre style="line-height: 110%">
c:\xampp\tomcat\webapps
	            └─── cadviewer
                        └─── WEB-INF
                               ├── classes
                               └── lib
</pre>


**4:** [Download](https://cadviewer.com/download/) install **WEB-INF** folder under this structure from the download Handlers section. 


In folder /cadviewer/WEB-INF open and edit the file **web.xml**.

Locate and change all CADViewer Servlets related  **&lt;param-name&gt; / &lt;param-value&gt;** pairs, so the paths and executable points properly to the values defined in your CADViewer installation structure. 
 
  

### HTML 

**NOTE:** Under Linux replace **c:\xampp\tomcat\webapps** with **/var/lib/tomcat{X}**.

In folder:

<pre style="line-height: 110%">
c:\xampp\tomcat\webapps
    └─── cadviewer
            └── html
</pre>


identify your sample mysample.html file, and ensure the **ServerUrl** and **ServerLocation** variables points to the proper location on your structure:

<pre style="line-height: 110%">

 // Location of installation folders
    var ServerBackEndUrl = "";
    var ServerUrl = "http://localhost:8080/cadviewer/";
    var ServerLocation = "c:/xampp/tomcat/webapps/cadviewer/";
</pre>




Open a web-browser pointing to your sample html file (any of the files in the /cadviewer/html/ folder): **http:/localhost:8080/cadviewer/html/mysample.html**

Use the server traces and browser development console for debugging, alternatively contact our [Support](https://cadviewer.com/cadviewertechdocs/support/)  

**NOTE:** Under Linux replace **c:\xampp\tomcat\webapps** with **/var/lib/tomcat{X}**.

For debugging, the folder:
<pre style="line-height: 110%">
c:\xampp\tomcat\
            └─── temp
		</pre>
contains a debug files corresponding to each of the Servlets defined in /cadviewer/WEB-INF/classes folder. The debug files are of type:  **getFileServlet-log-err2938081564144043508.txt** and **ConversionServlet-log_8998081420588034766.txt**.

Especially **ConversionServlet-log_xxxxxx.txt** lists the command line and traces in the communication with the back-end converter AutoXchange 2020. <br>
If drawing files does not display, this file will contain useful information to pinpoint the issue.


## Troubleshooting

One issue that often appears in installations is that interface icons do not display properly:

![Icons](https://cadviewer.com/cadviewertechdocs/images/missing_icons.png "Icons missing")

Typically the variables *ServerUrl*, *ServerLocation* or *ServerBackEndUrl* in the controlling **HTML**  document in ***/cadviewer/html/*** are not set to reflect the front-end server url or port.

<pre style="line-height: 110%">


    var ServerBackEndUrl = "";  // or what is appropriate for my server; used for NodeJS server only
    var ServerUrl = "http://localhost/cadviewer/";   // or what is appropriate for my server
    var ServerLocation = "c:/xampp/htdocs/cadviewer/"; // or what is appropriate for my server
</pre>
