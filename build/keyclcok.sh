curl -X OPTIONS "http://localhost:8180/auth/realms/master/protocol/openid-connect/token" \
  -H "Access-Control-Request-Method: POST" \
  -H "Origin: http://localhost:4200/" \
  -H "Access-Control-Request-Headers: content-type"