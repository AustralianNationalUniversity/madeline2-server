# Installing Madeline 

[Reference: http://madeline.med.umich.edu/madeline/install.php ](http://madeline.med.umich.edu/madeline/install.php)

 
```
apt install cmake 
apt install build-essential 
apt install libxml2-dev 
apt install libcurlpp-dev 
apt install openssl 
apt install libssl-dev 
apt install zlib1g-dev 
apt install gettext 
```

Download source code for Madeline 
```
git clone https://github.com/piratical/Madeline_2.0_PDE.git 
cd Madeline_2.0_PDE 
./configure --with-include-gettext 
cmake clean . 
make -i 
make install 
```

If you have issues not finding a library delete the CMakeCache.txt file 

Test on the command-line that madeline2 is available 
```
madeline2 
```
# Install Madeline2-server 

Madeline2-server is a custom web app that wraps the madeline2 command-line as an http service 

```
cd /usr/local 
wget https://dlcdn.apache.org/tomcat/tomcat-9/v9.0.90/bin/apache-tomcat-9.0.90.zip 
apt install unzip 
unzip apache-tomcat-9.0.90.zip 
ln -s apache-tomcat-9.0.90 tomcat 
cd tomcat 
rm -rf ROOT/ docs/ examples/ host-manager/ manager/ 
useradd –s /sbin/nologin tomcat 
```

Modify conf/server.xml with the following changes: 
```
    <Connector port="9090" protocol="HTTP/1.1" 

               connectionTimeout="20000" 

               redirectPort="9443" 

               maxParameterCount="1000" 

            />     
 
<Connector port="9443" protocol="AJP/1.3" enableLookups="true" disableUploadTimeout="true" connectionTimeout="20000" 
               maxThreads="150" SSLEnabled="false" scheme="https" secure="false" 
                proxyPort="443" proxyName="pedigree.apf.edu.au” 
               clientAuth="false" packetSize="65536" secretRequired="false" allowedRequestAttributesPattern=".*" />

    <Connector protocol="AJP/1.3" 
               port="9009" 
               redirectPort="9443" secretRequired="false" allowedRequestAttributesPattern=".*" /> 
```
 

Copy madeline2-server.war to tomcat/webapps folder  

Run as a service create a file /etc/systemd/system/tomcat.service 

```
[Unit] 
Description=Apache Tomcat Web Application Container 
After=network.target

[Service] 
User=tomcat 
Group=tomcat 
Type=forking 
PIDFILE=/tmp/tomcat/tomcat.pid 
Environment=CATALINA_PID/tmp/tomcat/tomcat.pid 
Environment=JAVA_HOME=/etc/alternatives/jre_1.8.0_openjdk 
Environment=CATALINA_HOME=/usr/local/tomcat 
Environment=CATALINA_BASE=/usr/local/tomcat 
ExecStart=/usr/local/tomcat/bin/startup.sh 
ExecStop=/usr/local/tomcat/bin/shutdown -pidfile /var/run/tomcat.pid -stop org.apache.catalina.startup.Bootstrap

[Install] 
WantedBy=multi-user.target 
```
 

Add JAVA_OPTS to /usr/local/tomcat/bin/catalina.sh 

```
JAVA_OPTS="-Xmx18432m -XX:+UseConcMarkSweepGC -XX:+CMSClassUnloadingEnabled -XX:+CMSClassUnloadingEnabled -XX:MaxPermSize=1024m -DconfigLocation=/usr/local/tomcat/app-config -Dhttps.protocols=TLSv1.2 -Djdk.tls.client.protocols=TLSv1.2" 
```

Permissions to execute scripts: 

```
chmod a+x /usr/local/tomcat/bin/startup.sh 
chmod a+x /usr/local/tomcat/bin/catalina.sh 
chown –R tomcat:tomcat /usr/local/tomcat 
chown –R tomcat:tomcat /usr/local/apache-tomcat-9.0.90 
```
 

To start tomcat: 

```
service tomcat start 
service tomcat status 
```

Reboot on startup: 

```
systemctl enable tomcat
```

 
