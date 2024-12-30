sudo apt update -y
sudo DEBIAN_FRONTEND=noninteractive apt upgrade -y

sudo apt install -y git
sudo apt install -y maven
sudo DEBIAN_FRONTEND=noninteractive apt-get install openjdk-21-jdk -y

export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH

export DB_HOST="jdbc:mysql://3.10.214.253:3306/tacker_db"
export DB_USER="java-app"
export DB_PASS="password"

mkdir -p /home/ubuntu/app
cd /home/ubuntu/app

git clone https://github.com/KianDoyle/calorie-tracker.git

cd calorie-tracker

mvn spring-boot:start