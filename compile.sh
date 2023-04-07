jar -cvf framework.jar -C /home/kevin/Documents/GitHub/Framework/framework/build/classes .

cp /home/kevin/Documents/GitHub/framework.jar /home/kevin/Documents/GitHub/Framework/Testframework/build/web/WEB-INF/lib/

cp -R /home/kevin/Documents/GitHub/Framework/Testframework/build/web  /home/kevin/Documents/GitHub/Framework
cd web 
jar cf ../Testframework.war *

cd ..
cp Testframework.war /home/kevin/Documents/apache-tomcat/webapps/
