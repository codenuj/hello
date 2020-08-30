# Hello Birthday

Build a simple Java Spring REST api and deploy it in form of a docker container, which will have two api as follows:
* Get Api: /hello/\{username} that must response in two ways:
    * If username birthdays in N days:
      {"message": "Hello, \<username>! Your bithday is in N day(s)"}
    * If username birthday is today:
      {"message": "Hello, \<username>! Happy birthday!"}
* Put Api: /hello/{username}/{date} and there are two conditions for this:
    * username must only contains letters.
    * YYYY-MM-DD must be date before the today's date.

# Clone project to your local directory

`git clone https://github.com/codenuj/hello.git `

# Build your project :  

`mvn clean install`

# Build docker container:  

Assuming you have docker installed on your system (I used docker toolbox for windows)  

`docker build -t <containerName>:<tag>`

# Run the container:  

`docker run -p 8080:8080 <containerName>:<tag>`

# Check if the API came up

`192.168.99.100:8080/hello`   

This IP is provided by docker when it runs. Most cases its this same one.  

# Docker stop Container

List all running containers:
  
`docker ps`  

Stop the container that you want
  
`docker stop <containerName>:<tag>` 

# Clean and Build your project

`mvn clean package`

# Create a S3 bucket 

Create a S3 bucket to put the server-less deploy able code. S3 are unique, so choose your own bucket name. 

`aws s3 mb s3://spring-hello-java-aws`

# Copy code into the s3 bucket

`aws cloudformation package --template-file sam.yaml --output-template-file target/output-sam.yaml --s3-bucket spring-hello-java-aws`

# Deploy the code in the bucket into a Cloud-formation

Deploy the code in the bucket into a Cloud-formation, which deploys the Lambda, makes a API Gateway endpoint and cloud-watch log for you.

`aws cloudformation deploy --template-file target/output-sam.yaml --stack-name spring-hello-java-aws --capabilities CAPABILITY_IAM`

> **_NOTE:_**  We are applying the IAM capabilities through the command. Make sure your IAM role has the capabilities else you will get an error.

# For Checking 

Visit the Cloud-formation stack in your AWS account and find the link too the API in the output tab , or describe the stack through your CLI to get he output API link

`aws cloudformation describe-stacks --stack-name spring-hello-java-aws`