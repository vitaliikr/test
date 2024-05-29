# Getting Started
Enable docker. compose will start automatically.

# Create locally folder for storing videos and change path in application.properties
`
video.storage.local.folder:/Users/vkryzhe/Desktop/videos
`


# Upload video 
``
curl --location 'http://localhost:8080/videos/upload' \
--header 'Content-Type: multipart/form-data' \
--form 'video=@"/Users/vkryzhe/Desktop/d20ea01c-881c-4f53-b886-166cccb3458b.mp4"'
``

# Download video
``
curl --location 'http://localhost:8080/videos/d9bc809f-d09c-4a7c-a5b2-5e808f89538a/download'
``

# Create metadata for video
``
curl --location 'http://localhost:8080/videos/d9bc809f-d09c-4a7c-a5b2-5e808f89538a/metadatas' \
--header 'Content-Type: application/json' \
--data '{
"genres": ["COMEDY"],
"title": "Test",
"description": "movie about comedy",
"director": "Vitalii",
"releaseDate": "2024-01-01",
"runningTime": 180
}'
``

# Update metadata for video
``
curl --location --request PUT 'http://localhost:8080/videos/d9bc809f-d09c-4a7c-a5b2-5e808f89518a/metadatas/45a7d821-da11-4b7f-9415-79a7881a6482' \
--header 'Content-Type: application/json' \
--data '{
"id": "45a7d821-da11-4b7f-9415-79a7881a6482",
"videoId": "d9bc809f-d09c-4a7c-a5b2-5e808f89518a",
"genres": [
"COMEDY"
],
"title": "Test",
"description": "movie about comedy",
"director": "fa",
"releaseDate": "2024-01-01",
"runningTime": 180.0
}'
``

# List all videos metadata
``
curl --location 'http://localhost:8080/videos/metadatas'
``

# List all videos metadata by search criteria
``
curl --location 'http://localhost:8080/videos/metadatas/search' \
--header 'Content-Type: application/json' \
--data '{
"title": "Test",
"director": "V"
}'
``

# Delete video and related metadata by video id
``
curl --location --request DELETE 'http://localhost:8080/videos/d9bc809f-d09c-4a7c-a5b2-5e808f89538a'
``



