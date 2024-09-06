#!/bin/zsh

response=$(curl -X PUT "http://localhost:8001/contentreader/0/create" \
     -H "Content-Type: application/json" \
     -d '{
        "originalUrl": "https://chatgpt.com/auth/ext_callback?next=",
        "timePeriod": "2024-10-15T10:00:00Z",
        "fullName": "John Doe"
     }')

# Extract id and shortestUrl from the response
id=$(echo $response | sed -n 's/.*"id":"\([^"]*\)".*/\1/p')
shortestUrl=$(echo $response | sed -n 's/.*"shortestUrl":"\([^"]*\)".*/\1/p')

# Print the id and shortestUrl
echo "ID: $id"
echo "Shortest URL: $shortestUrl"

# Extract the part after '8001/' from the shortestUrl
urlPart=$(echo $shortestUrl | sed 's|http://localhost:8001/||')

# Print the URL part after '8001/'
echo "URL part after 8001/: $urlPart"

# Step 2: Fetch the contentreader using the extracted URL part
curl -X GET "http://localhost:8001/get/$urlPart"

