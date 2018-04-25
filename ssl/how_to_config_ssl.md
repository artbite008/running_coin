## 1, copy the guxiangfly.cn.jks to resources folder

## 2, add below items to application.properties

````
server.port=8443
security.require-ssl=true
server.ssl.key-store=classpath:guxiangfly.cn.jks
server.ssl.key-store-password=45lgrs8gokejt0
server.ssl.key-store-type=JKS
server.ssl.key-alias=guxiangfly.cn
````
