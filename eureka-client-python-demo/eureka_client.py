import py_eureka_client.eureka_client as eureka_client

# The flowing code will register your server to eureka server and also start
# to send heartbeat every 30 seconds
eureka_client.init(eureka_server="http://spring-server-registry:8083/eureka",
                   app_name="crawler-service",
                   instance_port=8000,
                   instance_host="python-crawler-service")
