cd ./categories
docker build -t vslab/categories .
cd ../products
docker build -t vslab/products .
cd ../catalogue
docker build -t vslab/catalogue .
cd ../eureka-server
docker build -t vslab/eureka_server .
cd ../edge_server
docker build -t vslab/zuul .
PAUSE