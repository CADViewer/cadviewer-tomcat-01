# cadviewer-tomcat-01
implementation  structure of cadviewer-script-library using Servlets




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

Use the server traces and browser development console for debugging, alternatively contact our [Support](/cadviewertechdocs/support/)  

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

Typically the variable ServerUrl in /cadviewer/html/mysample.html is not set to reflect the front-end server url or port.
