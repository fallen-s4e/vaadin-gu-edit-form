mv /home/fallen/opt/jboss-eap-6.3-beta/jboss-eap-6.3/standalone/deployments/vaadin-hello-world-1.0.war.{deployed,undeploy}
mv /home/fallen/opt/jboss-eap-6.3-beta/jboss-eap-6.3/standalone/deployments/vaadin-hello-world-1.0.war.{failed,undeploy}
sleep 2
rm /home/fallen/opt/jboss-eap-6.3-beta/jboss-eap-6.3/standalone/deployments/vaadin-hello-world-1.0.war*
cp /home/fallen/projects/sandbox/vaadin-hello-world-1/vaadin-hello-world/target/vaadin-hello-world-1.0.war /home/fallen/opt/jboss-eap-6.3-beta/jboss-eap-6.3/standalone/deployments/


