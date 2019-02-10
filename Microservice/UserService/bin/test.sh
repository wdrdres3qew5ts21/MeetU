docker image build -t userservice .

docker run -p 8080:3006 userservice

entrypoint ["java","ja"]